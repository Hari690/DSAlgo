package array;

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single
 * space separating the words. Do not include any extra spaces.
 */
public class ReverseString {
    public String reverseWords(String s) {
        StringBuilder output = new StringBuilder();
        s= s.trim().replaceAll("\\s{2,}", " ").trim();
        String[] parts = s.split(" ");

        for(int i=0;i<parts.length;i++) {
            for(int j=parts[i].length()-1;j>=0;j--) {
                output.append(parts[i].charAt(j));
            }
            if(i<parts.length-1)
                output.append(" ");
        }

        return output.reverse().toString();
    }

    public static void main(String[] args) {
        new ReverseString().reverseWords("a good   example");
    }
}