package trie;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * Implement the WordDictionary class:
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 */
class WordDictionaryAddSearchWordDataStructure {
    private WordDictionaryAddSearchWordDataStructure[] child;
    boolean end;

    public WordDictionaryAddSearchWordDataStructure() {
        this.child = new WordDictionaryAddSearchWordDataStructure[26];
        this.end = false;
    }

    public void addWord(String word) {
        WordDictionaryAddSearchWordDataStructure temp = this;
        for(char c : word.toCharArray()) {
            if(temp.child[c-'a']==null)
                temp.child[c-'a']=new WordDictionaryAddSearchWordDataStructure();
            temp = temp.child[c-'a'];
        }
        temp.end = true;
    }

    public boolean search(String word) {
        WordDictionaryAddSearchWordDataStructure temp = this;
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if(c=='.') {
                for(WordDictionaryAddSearchWordDataStructure ch : temp.child)
                    if(ch!=null && ch.search(word.substring(i+1)))
                        return true;
                return false;
            }
            temp = temp.child[c-'a'];
            if(temp==null)
                return false;
        }
        return temp.end==true;
    }
}
