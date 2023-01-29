package trie;

class WordDictionary {
    private WordDictionary[] child;
    boolean end;

    public WordDictionary() {
        this.child = new WordDictionary[26];
        this.end = false;
    }

    public void addWord(String word) {
        WordDictionary temp = this;
        for(char c : word.toCharArray()) {
            if(temp.child[c-'a']==null)
                temp.child[c-'a']=new WordDictionary();
            temp = temp.child[c-'a'];
        }
        temp.end = true;
    }

    public boolean search(String word) {
        WordDictionary temp = this;
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if(c=='.') {
                for(WordDictionary ch : temp.child)
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
