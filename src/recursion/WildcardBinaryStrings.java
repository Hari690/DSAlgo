package recursion;

import java.util.ArrayList;
import java.util.List;

public class WildcardBinaryStrings {
    public List<String> generateWildcardStrings(String input) {
        List<String> output = new ArrayList<>();
        findAndAdd(input, output, 0);
        return output;
    }

    private void findAndAdd(String text, List<String> output, int i) {
        if(i==text.length()) {
            output.add(text);
            return;
        }
        if(text.charAt(i)=='?') {
            findAndAdd(text.substring(0,i)+'0'+text.substring(i+1),output,i+1);
            findAndAdd(text.substring(0,i)+'1'+text.substring(i+1),output,i+1);
        } else {
            findAndAdd(text ,output, i+1);
        }
    }

    public static void main(String[] args) {
        WildcardBinaryStrings wildcardBinaryStrings = new WildcardBinaryStrings();
        List<String> output = wildcardBinaryStrings.generateWildcardStrings("1??0?101");
        output.stream().forEach(System.out::println);
    }
}
