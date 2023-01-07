package backtracking;

/**
 * You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9]. You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.
 * You are restricted with the following rules:
 * The division operator '/' represents real division, not integer division.
 * For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
 * For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
 * You cannot concatenate numbers together
 * For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
 * Return true if you can get such expression that evaluates to 24, and false otherwise.
 */
public class OperationsToGet24 {
    static Double TWENTY_FOUR = Double.valueOf(24);
    public boolean judgePoint24(int[] cards) {
        double[] arr = new double[]{cards[0],cards[1],cards[2],cards[3]};
        return check(arr);
    }

    private boolean check(double[] arr) {
        if(arr.length==1) {
            //System.out.println(arr[0]);
            //return Math.abs(arr[0]-24)<0.001;
            return ((Double)arr[0]).equals(TWENTY_FOUR);
        }

        for(int i=0;i<arr.length;i++) {
            for(int j=i+1;j<arr.length;j++) {

                // array will shorten with each recursion to one less value for calculation between two nos.
                double[] res = new double[arr.length-1];
                int index=0;

                // i & j are selected for calculation and non i & j are added to array for next iteration.
                for(int k=0;k<arr.length;k++) {
                    if(k!=i && k!=j) {
                        res[index++]=arr[k];
                    }
                }

                for( double d: compute(arr[i],arr[j])) {
                    res[res.length-1] = d;
                    if(check(res))
                        return true;
                }
            }
        }

        return false;
    }

    private double[] compute(double x, double y) {
        return new double[]{x+y,x-y,x*y,y-x,x/y,y/x};
    }

    public static void main(String[] args) {
        OperationsToGet24 operationsToGet24 = new OperationsToGet24();
        double[] input = new double[]{3,3,8,8};
        System.out.println(operationsToGet24.check(input));
    }
}
