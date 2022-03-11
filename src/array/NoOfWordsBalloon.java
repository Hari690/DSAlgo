package array;

/**
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 *
 * Example 1:
 * Input: text = "nlaebolko"
 * Output: 1
 *
 * Example 2:
 * Input: text = "loonbalxballpoon"
 * Output: 2
 *
 * Example 3:
 * Input: text = "leetcode"
 * Output: 0
 */
public class NoOfWordsBalloon {
    public int maxNumberOfBalloons(String text) {
        int[] letters = new int[26];

        for(char c : text.toCharArray())
            letters[c-'a']++;

        int[] found = {letters['b'-'a'],letters['a'-'a'],letters['l'-'a'],letters['o'-'a'],letters['n'-'a']};

        int count=0;
        int[] needs = {1,1,2,2,1};
        int[] divs = new int[5];
        int min = Integer.MAX_VALUE;
        for(int i=0;i<5;i++) {
            divs[i] = found[i]/needs[i];
            min = Math.min(min, divs[i]);
        }

        return min;
    }
}
