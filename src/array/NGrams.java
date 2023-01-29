package array;

import java.util.Arrays;
import java.util.Iterator;

public class NGrams {

    /*
    Secondly, create a Set of n-grams from document A. Then, for each n-gram from document B, check if it is in the set for pattern matching.
     */
    public String[] ngrams(String s, int len) {
        String[] parts = s.split(" ");
        String[] result = new String[parts.length - len + 1];
        for(int i = 0; i < parts.length - len + 1; i++) {
            StringBuilder sb = new StringBuilder();
            for(int k = 0; k < len; k++) {
                if(k > 0) sb.append(' ');
                sb.append(parts[i+k]);
            }
            result[i] = sb.toString();
        }
        return result;
    }

    class NgramIterator implements Iterator<String> {
        String[] words;
        int pos = 0, n;

        public NgramIterator(int n, String str) {
            this.n = n;
            words = str.split(" ");
        }

        public boolean hasNext() {
            return pos < words.length - n + 1;
        }

        public String next() {
            StringBuilder sb = new StringBuilder();
            for (int i = pos; i < pos + n; i++)
                sb.append((i > pos ? " " : "") + words[i]);
            pos++;
            return sb.toString();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        NGrams nGrams = new NGrams();
        Arrays.stream(nGrams.ngrams("this is my", 2)).forEach(System.out::println);
    }
}
