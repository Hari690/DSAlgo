package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 * Example 1:
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Example 2:
 *
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified becase it contains only one word.
 * Example 3:
 *
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 */
public class TextJustification {
    /* General Approach:
    1.Calculate the maximum number of word each line could pack -> count
    2.Calculate the length of the space of each line = maxWidth - count
    3.Distribute the space as evenly as possible, if we could not distribute evenly, we add the reminder space from left to right
       i.At the last line - we should deal as special case
       ii.Not at the last line - be careful of the case when number of the space is not even
    Specific process:
    	1. How do we know what is the maximum number of word we could pack each line?
    	We compare maxWidth with total of( n-words length + (n-1)Space)
    	e.x ["This", "is", "an", "example", "of", "text", "justification."]  maxWidth = 16
    	The maximum words we could insert are "-->This is an" 4+1+2+1+2=10,could we add one more word "example", obviously impossible

   		2.To find the number of space in each line, we could just have maxWidth minus the length of all words in this line
   		In the above example would be 16-10 = 6 --> we will have 6 space at this line

   		3. When we have the numbers of space, we need to insert the space b.w the word. How to do so?
   			a. If this line is the last line, it should be left justified and no extra space is inserted between words.
  			b. If this line is not the last line, we have another two cases to consider:
  				i.space could be evenly contributed
  				ii. space could not be evenly contrinuted -> we insert the remain space from left to right
  			For example ["to","a"] maxWidth = 6
  			If this is the last line, we should have "to a  ";Otherwise, we have "to   a"

 		* It would be very easy to understand if we just run an simple following example with the code.
 		["This", "is", "an", "example", "of", "text", "justification."]  maxWidth = 16
 	*/

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if(words.length == 0 || words == null) return res;
        int index = 0;
        while(index < words.length){
            int last = index + 1;
            int count = words[index].length(); //count：the length of all words at this line
            while(last < words.length){
                if(words[last].length() + count + 1 > maxWidth) break; /*plus one for the space, if its a perfect fit it will fit*/
                count += words[last].length() + 1;
                last++;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(words[index]);
            // diff represents how many gap we could insert our space b.w each word.
            // "a b c" -> diff = 2, "a" -> diff = 0, "a b c d" = 3
            // As you see it is the number of word - 1
            int diff = last - index - 1;
            // last line -> insert only one space b.w each word, and append extra spaces to the end
            if(last == words.length || diff == 0){ //check condition for last line or only one word
                for(int i = index + 1; i < last; i++){ //e.x ["to","a"]
                    sb.append(" ");
                    sb.append(words[i]);
                }
                for(int i = sb.length(); i < maxWidth; i++){
                    sb.append(" ");
                }
            }
            else{
    	/* Not the last line case:
    	 * Total space = maxWidth - the length of total words at this line
    	 * The number of space after each word = (maxWidth - count) / gap = (maxWidth - count) / diff
    	 * e.x  ["This", "is", "an", "example", "of", "text", "justification."]  maxWidth = 16
			The number of space after each word = 3
		 * r -> remaining number of space if the spaces are not divided evenly
    	 * */

                int space = (maxWidth - count) / diff; // The number of space after each word
                int r = (maxWidth - count) % diff; //remaining number of space which we should insert from left to right
                for(int i = index + 1; i < last; i++){
                    for(int k = space; k > 0; k--){
                        sb.append(" ");
                    }
                    if(r > 0){
                        sb.append(" ");
                        r--;
                    }
                    sb.append(" ");
                    sb.append(words[i]);
                }
            }
            res.add(sb.toString());
            index = last;
        }
        return res;
    }
}
