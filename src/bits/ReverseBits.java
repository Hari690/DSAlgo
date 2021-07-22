package bits;

import java.util.ArrayList;
import java.util.List;

public class ReverseBits {

    public static void main(String[] args) {
        System.out.println(new ReverseBits().reverseBits(9));
    }

    public int reverseBits(int n) {
        List<Integer> output = new ArrayList<>();
        int j = 0;
        while(n>0) {
            int rem = n&1;
            output.add(rem);
            n = n>>1;
            j++;
        }
        int no = 0;
        for(int i=0 ;i<output.size();i++) {
            no = no<<1;
            no= no + output.get(i);
        }
        return no;
    }
}
