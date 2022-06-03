package bits;

/**
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common
 * letters. If no such two words exist, return 0.
 */

/*
  Use bit marking. i.e use an array of bits and mark for the 26 characters in the words and use that to check equality.
 */
public class MaxProductWordLengths {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0)
            return 0;
        int len = words.length;
        int[] value = new int[len];
        for (int i = 0; i < len; i++) {
            String tmp = words[i];
            value[i] = 0;
            for (int j = 0; j < tmp.length(); j++) {
                // char to number and use that to set bit position
                // i.e convert to integer and or it. left shift one by that many digits.
                value[i] |= 1 << (tmp.charAt(j) - 'a');
            }
        }
        int max = 0;
        for(int i=0;i<words.length-1;i++) {
            for(int j=i+1;j<words.length;j++) {
                if((value[i]&value[j])==0)
                    max = Math.max(max,words[j].length()*words[i].length());
            }
        }
        return max;
    }
}
