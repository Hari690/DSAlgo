package linkedlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    static WordSet wordSet = new WordSet();

    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        wordSet.preprocessInput();
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        wordSet.findNext("TWO");
        while ((line = in.readLine()) != null) {
            wordSet.findNext("the");
        }
    }

    static class WordSet {
        HashMap<String, Map<String,Integer>> words = new HashMap<>();

        private void insertNextWord(String word, String nextWord) {
           Map<String, Integer> nextWords = words.get(word);
           if( nextWords==null) {
               nextWords = new HashMap<>();
           }
           nextWords.put(nextWord, nextWords.getOrDefault(nextWord,0)+1);
           words.put(word, nextWords);
        }

        private static void preprocessInput() {
            String input = "ONE TWO ONE TWO THREE TWO THREE";

            String[] words = input.replaceAll("[^A-Za-z0-9]", " ").split(" ");
            for ( int i=0; (i+1)<words.length;i++ ) {
                wordSet.insertNextWord(words[i], words[i+1]);
            }
        }

        private static void findNext( String wordToFind) {
            int total = wordSet.words.get(wordToFind).values().stream().reduce(0, Integer::sum);
            List<Map.Entry<String,Integer>> nextWordCounts = new ArrayList<>(wordSet.words.get(wordToFind).entrySet());

            Collections.sort( nextWordCounts, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    int cmp = o2.getValue().compareTo(o1.getValue());
                    if (cmp == 0)
                        return (o1.getKey().compareTo(o2.getKey()));//Ascending order
                    else {
                        return cmp;
                    }
                }
            });

            List<String> output = new ArrayList<>();
            for ( Map.Entry<String,Integer> word : nextWordCounts) {
                BigDecimal result = BigDecimal.valueOf((double)(word.getValue())/((double)(total)));
                output.add(word.getKey()+","+ result.setScale(3, RoundingMode.DOWN));
            }
            System.out.println(output.stream().collect(Collectors.joining(";")));
        }
    }



}
