package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
/*
I don't think there are solutions different than "keep the latest N lines while reading forward the data" or "start from the end and go backwards until you read the Nth line".
The point is that you'd use one or the another based on the context.
The "go to the end and go backwards" is better when tail accesses a random access file, or when the data is small enough to be put on memory.
In this case the runtime is minimized, since you scan the data that has to be outputted (so, it's "optimal")
Your solution (keep the N latest lines) is better when tail is fed with a pipeline or when the data is huge.
In this case, the other solution wastes too much memory, so it is not practical and, in the case the source is slower than
tail (which is probable) scanning all the file doesn't matter that much.
 */
public class LinuxTailCommand implements Runnable {

    public LinuxTailCommand(String myFile, int myInterval) {
        file = new File(myFile);
        this.crunchifyRunEveryNSeconds = myInterval;
    }

    public LinuxTailCommand() {
    }

    private boolean read = true;
    private boolean debug = false;
    private int crunchifyRunEveryNSeconds;
    private long lastKnownPosition = 0;
    private boolean shouldIRun = true;
    private File file;
    private static int crunchifyCounter = 0;

    public void tailFile(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while (read) {
                line = reader.readLine();
                if (line == null) {
                    //wait until there is more of the file for us to read
                    Thread.sleep(1000);
                }
                else {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printLine(String message) {
        System.out.println(message);
    }
    public void stopRunning() {
        shouldIRun = false;
    }

    // using random file reader
    public void run() {
        try {
            while (shouldIRun) {
                Thread.sleep(crunchifyRunEveryNSeconds);

                final RandomAccessFile rf = new RandomAccessFile(file, "r");
                final long fileLength = rf.length();

                /**
                 * This case occur, when file is taken backup and new file
                 * created with the same name.
                 */
                if (fileLength < lastKnownPosition) {
                    lastKnownPosition = 0;
                }

                if (fileLength > lastKnownPosition) {
                    // Reading and writing file
                    try(RandomAccessFile readWriteFileAccess = new RandomAccessFile(file, "rw")) {
                        readWriteFileAccess.seek(lastKnownPosition);
                        String crunchifyLine;
                        // confirm encoding of file before reading
                        while ((crunchifyLine = readWriteFileAccess.readLine()) != null) {
                            this.printLine(crunchifyLine);
                            crunchifyCounter++;
                        }
                        lastKnownPosition = readWriteFileAccess.getFilePointer();
                    }
                } else {
                    if (debug)
                        this.printLine("Hmm.. Couldn't found new line after line # " + crunchifyCounter);
                }
            }
        } catch (Exception e) {
            stopRunning();
        }
        if (debug)
            this.printLine("Exit the program...");
    }

    /**
     * Use appendData method to add new line to file, so above tailer method can print the same in Eclipse Console
     *
     * @param filePath
     * @param shouldIRun
     * @param crunchifyRunEveryNSeconds
     */
    private static void appendData(String filePath, boolean shouldIRun, int crunchifyRunEveryNSeconds) {
        try {
            while (shouldIRun) {
                Thread.sleep(crunchifyRunEveryNSeconds);
                try (FileWriter fileWriter = new FileWriter(filePath, true);
                     BufferedWriter bufferWritter = new BufferedWriter(fileWriter)) {
                    String data = "\n Crunchify.log file content: " + Math.random();
                    bufferWritter.write(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readLastNLinesApproximation(String filePath, int length) {
        try(final RandomAccessFile rf = new RandomAccessFile(filePath, "r")) {
            final long fileLength = rf.length();

            rf.seek(fileLength-50*length);
            rf.readLine();
            String crunchifyLine;
            int noOfLines = 0;
            // confirm encoding of file before reading
            while ((crunchifyLine = rf.readLine()) != null) {
                this.printLine(crunchifyLine);
                noOfLines++;
            }
            System.out.println("No of lines read: "+noOfLines);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    RandomAccessFile is a good place to start, as described by the other answers. There is one important caveat though.
    If your file is not encoded with an one-byte-per-character encoding, the readLine() method is not going to work for you.
    And readUTF() won't work in any circumstances. (It reads a string preceded by a character count ...)
    Instead, you will need to make sure that you look for end-of-line markers in a way that respects the encoding's character boundaries.
    For fixed length encodings (e.g. flavors of UTF-16 or UTF-32) you need to extract characters starting from byte positions that are divisible by the character size in bytes. For variable length encodings (e.g. UTF-8), you need to search for a byte that must be the first byte of a character.
    In the case of UTF-8, the first byte of a character will be 0xxxxxxx or 110xxxxx or 1110xxxx or 11110xxx.
    Anything else is either a second / third byte, or an illegal UTF-8 sequence.
    See The Unicode Standard, Version 5.2, Chapter 3.9, Table 3-7. This means, as the comment discussion points out,
    that any 0x0A and 0x0D bytes in a properly encoded UTF-8 stream will represent a LF or CR character.
    Thus, simply counting the 0x0A and 0x0D bytes is a valid implementation strategy (for UTF-8) if we can assume that the other kinds of Unicode line separator (0x2028, 0x2029 and 0x0085) are not used. You can't assume that, then the code would be more complicated.
    Having identified a proper character boundary, you can then just call new String(...)
    passing the byte array, offset, count and encoding, and then repeatedly call String.lastIndexOf(...) to count end-of-lines.
     */
    public void readLastNLines2(String filePath, int length) throws IOException {
        try(final RandomAccessFile rf = new RandomAccessFile(filePath, "r")) {
            final long fileLength = rf.length();

            char crunchifyLine;
            LinkedList<String> lines = new LinkedList<>();
            StringBuilder line = new StringBuilder();
            int noOfLines = 0;
            for(long i=fileLength;i>=0;i--) {
                rf.seek(i);
                crunchifyLine = (char) rf.read();
                if(crunchifyLine=='\n') {
                    noOfLines++;
                    lines.addFirst(line.reverse().toString());
                    line = new StringBuilder();
                } else {
                    line.append(crunchifyLine);
                }
                if(noOfLines==length)
                    break;
            }
            lines.stream().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readLastNLinesCircularBuffer(String fileName, int length) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            LinkedList<String> lines = new LinkedList<>();
            for (String tmp; (tmp = br.readLine()) != null; )
                if (lines.add(tmp) && lines.size() > length)
                    lines.removeFirst();
            //lines.stream().forEach(System.out::println);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LinuxTailCommand linuxTailCommand = new LinuxTailCommand();
        String filePath = "/Users/harigor/Downloads/crunchify.log";
//        linuxTailCommand.tailFile(filePath);
//
//        Thread thread = new Thread(() -> linuxTailCommand.tailFile(filePath));
//        thread.start();
//
//        ExecutorService crunchifyExecutor = Executors.newFixedThreadPool(4);
//        // Replace username with your real value
//        // For windows provide different path like: c:\\temp\\crunchify.log
//        ConfluentLinuxTailCommand crunchify_tailF = new ConfluentLinuxTailCommand(filePath, 2000);
//        // Start running log file tailer on crunchify.log file
//        crunchifyExecutor.execute(crunchify_tailF);
//        // Start pumping data to file crunchify.log file
//        appendData(filePath, true, 5000);

        long startTime = System.currentTimeMillis();
        linuxTailCommand.readLastNLinesCircularBuffer(filePath, 2000);
        System.out.println("Time taken ="+(System.currentTimeMillis()-startTime)+"ms");


        startTime = System.currentTimeMillis();
        linuxTailCommand.readLastNLines2("/Users/harigor/Desktop/Practise/Leetcode/src/problems/ConfluentLinuxTailCommand.java",7);
        System.out.println("Time taken ="+(System.currentTimeMillis()-startTime)+"ms");

//        Thread t = new Thread(()->ConfluentLinuxTailCommand.appendData(filePath, true, 1));
//        t.start();
    }
}