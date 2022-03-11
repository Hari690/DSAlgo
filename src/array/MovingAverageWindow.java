package array;

public class MovingAverageWindow {
        private final double[] window; // use this data structure

        private double sum = 0;

        private int lastElementIndex = 0;

        private int count = 0;

        // you can add other fields here

        /*
         * Always invoked with windowSize > 0
         */
        public MovingAverageWindow(final int windowSize) {
            window = new double[windowSize];
        }

        /*
         * Adds the new number to window if it is larger than the value of 2.0d.
         * If window is full then replace the oldest added number in the window with the number provided.
         */
        public void addNumberToWindow(final double number) {
            if(number>2.0d) {
                if(count <window.length) {
                    window[count++] = number;
                    sum+=number;
                } else {
                    if(lastElementIndex==window.length)
                        lastElementIndex=0;
                    sum-=window[lastElementIndex];
                    sum+=number;
                    window[lastElementIndex++] = number;
                }
            }
        }

        /*
         * Return the average of the numbers added that are currently present in the window.
         * Otherwise when no numbers in window return 0.0d
         */
        public double getAverage() {
            return sum/ count;
        }

        /*
         * Example of how a client would use this class
         */
        public static void main(String[] args) {
            final MovingAverageWindow movingAverage = new MovingAverageWindow(4);
            movingAverage.addNumberToWindow(1.5d);
            movingAverage.addNumberToWindow(2.5d);
            System.out.println(movingAverage.getAverage()); // prints 2.5
            movingAverage.addNumberToWindow(3.0d);
            movingAverage.addNumberToWindow(3.5d);
            System.out.println(movingAverage.getAverage()); // prints 3.0
            movingAverage.addNumberToWindow(2.0d);
            System.out.println(movingAverage.getAverage()); // prints 3.0
            movingAverage.addNumberToWindow(7.0d);
            System.out.println(movingAverage.getAverage()); // prints 4.0
            movingAverage.addNumberToWindow(6.5d);
            System.out.println(movingAverage.getAverage()); // prints 5.0
        }
}
