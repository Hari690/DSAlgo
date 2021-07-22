package bits;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length()-1, j = b.length()-1;
        int carry = 0;
        while(i >= 0 || j >= 0){
            int sum = carry;
            // auto boxing permits int operation on char else using Character.getNumericValue(
            if(i >= 0) sum += a.charAt(i--)%2;
            if(j >= 0) sum += b.charAt(j--)%2;
            carry = sum > 1 ? 1 : 0;
            result.append(sum%2);
        }
        if(carry != 0) result.append(carry);
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.addBinary("1000","10"));
        System.out.println('0'-'0');
    }
}