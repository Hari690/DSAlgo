package general;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CustomExecutorService {

    private BlockingQueue taskQueue;
    private List<PoolThreadRunnable> runnables = new ArrayList<>();
    private boolean isStopped = false;

    public CustomExecutorService(int noOfThreads, int maxNoOfTasks){
        taskQueue = new ArrayBlockingQueue(maxNoOfTasks);
        for(int i=0; i<noOfThreads; i++) {
            runnables.add(new PoolThreadRunnable(taskQueue));
        }
        for(PoolThreadRunnable runnable : runnables){
            new Thread(runnable).start();
        }
    }

    public synchronized void  execute(Runnable task) throws Exception{
        if(this.isStopped)
            throw new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.offer(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(PoolThreadRunnable runnable : runnables){
            runnable.doStop();
        }
    }

    public synchronized void waitUntilAllTasksFinished() {
        while(this.taskQueue.size() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class PoolThreadRunnable implements Runnable {
        private Thread        thread    = null;
        private BlockingQueue taskQueue;
        private boolean       isStopped = false;

        public PoolThreadRunnable(BlockingQueue queue){
            taskQueue = queue;
        }

        public void run(){
            this.thread = Thread.currentThread();
            while(!isStopped()){
                try{
                    Runnable runnable = (Runnable) taskQueue.take();
                    runnable.run();
                } catch(Exception e){
                    //log or otherwise report exception,
                    //but keep pool thread alive.
                }
            }
        }

        public synchronized void doStop(){
            isStopped = true;
            //break pool thread out of dequeue() call.
            this.thread.interrupt();
        }

        public synchronized boolean isStopped(){
            return isStopped;
        }
    }
}
