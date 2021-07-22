package recursion;

/**
 * Leetcode 394
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note
 * that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there won't be input like 3a or 2[4].
 *
 * Use 2 stacks or below.
 */
public class DecodeString {

    public static void main(String[] args) {

    }

    int i = 0;
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        while(i < s.length() && s.charAt(i) != ']'){
            if(Character.isDigit(s.charAt(i))){
                int k = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i)))
                    k = k*10 + s.charAt(i++) - '0';
                i++;
                String r = decodeString(s);
                while(k-- > 0)
                    result.append(r);
                i++; // for closing parenthesis
            } else
                result.append(s.charAt(i++));
        }
        return result.toString();
    }
}
