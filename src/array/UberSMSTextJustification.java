package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a character limit and a message, split the message up into annotated chunks without cutting words as,
 * for example when sending the SMS "Hi Sivasrinivas, your Uber is arriving now!" with char limit 25, you should get
 * ["Hi Sivasrinivas,(1/3)", "your Uber is arriving(2/3)", "now!(3/3)"]
 */
public class UberSMSTextJustification {
    static List<String> splitText(String message, int charLimit) {
        return splitTextAuxUsingSplit(message, charLimit);
    }

    static List<String> splitTextAuxUsingSplit(String message, int charLimitOriginal) {
        //Decrease the char limit to accomodate chunk number at the end i.e. (1/3). For now assuming, the message chunks won't be more than 9
        int charLimit = charLimitOriginal - 5;
        //New arraylist to store message chunks
        List<String> result = new ArrayList<>();
        String[] splitted = message.split(" ");
        String temp;

        for (int i = 0; i < splitted.length - 1; i++) {
            temp = splitted[i];
            //while length of temp and the next element combined is less than charLimit, temp = temp + next element
            //Last element to be taken care of after this loop
            while ((temp + 1 + splitted[i + 1]).length() <= charLimit && i + 1 < splitted.length - 1) {  //+1 for space
                temp = temp + " " + splitted[i + 1];
                i++;
            }
            result.add(temp);

        }
        //Take care of the last element
        //Add the last element from splitted to the last element of result if their combined length is less than charLimit
        String lastElement = result.get(result.size() - 1);
        if (lastElement.length() + 1 + splitted[splitted.length - 1].length() < charLimit) {  //+1 for space

            result.set(result.size() - 1, lastElement + " " + splitted[splitted.length - 1]);
        } else {
            result.add(splitted[splitted.length - 1]);
        }

        //append message chunk number for ex (1/3)
        int resultSize = result.size();
        for(int i = 0; i < resultSize; i++) {
            result.set(i, result.get(i) +"("+ (i+1) + "/" + resultSize + ")" );
        }

        return result;
    }
    public static void main(String[] args) {
        String message;
        int charLmit;
        message = "Hi Sivasrinivas, your Uber is arriving now! And this is a bigger text";
        charLmit = 25;
        List<String> result = splitText(message, charLmit);
        for(String item : result) {
            System.out.println("Length = " + item.length() + " : " +item  );
        }
        System.out.println(result);
    }
}
