package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.
 *
 * Implement the MagicDictionary class:
 *
 * MagicDictionary() Initializes the object.
 * void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
 * bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.
 *
 *
 * Example 1:
 *
 * Input
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * Output
 * [null, null, false, true, false, false]
 *
 * Explanation
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // return False
 * magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
 * magicDictionary.search("hell"); // return False
 * magicDictionary.search("leetcoded"); // return False
 */
public class MagicDictionaryStringsOneCharApart {
    List<String> list = null;
    public MagicDictionaryStringsOneCharApart() {
        list = new ArrayList<>();
    }

    public void buildDict(String[] dictionary) {
        for(String word : dictionary)
            list.add(word);
    }

    public boolean search(String searchWord) {
        for(String word : list) {
            if(word.length()==searchWord.length()) {
                for(int i=0;i<word.length();i++) {
                    if(searchWord.charAt(i)==word.charAt(i))
                        continue;
                    String newWord1 = word.substring(0,i)+word.substring(i+1);
                    String newWord2 = searchWord.substring(0,i)+searchWord.substring(i+1);
                    if(newWord1.equals(newWord2))
                        return true;
                    break;
                }
            }
        }
        return false;
    }
}
