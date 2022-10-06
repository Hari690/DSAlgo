package general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Pair<T,S> {
    T key;
    S value;
    Pair(T val1, S val2) {
        key = val1;
        value = val2;
    }
    public T getKey() {
        return key;
    }
    public S getValue() {
        return value;
    }
}

/**
 * You are given a list of documents with id and text.
 * Eg :-
 * DocId, Text
 * 1, "Cloud computing is the on-demand availability of computer system resources."
 * 2, "One integrated service for metrics uptime cloud monitoring dashboards and alerts reduces time spent navigating between systems."
 * 3, "Monitor entire cloud infrastructure, whether in the cloud computing is or in virtualized data centers."
 *
 * Search a given phrase in all the documents in a efficient manner. Assume that you have more than 1 million docs.
 * Eg :-
 * search("cloud") >> This should output [1,2,3]
 * search("cloud monitoring") >> This should output [2]
 * search("Cloud computing is") >> This should output [1,3]
 */
public class ConfluentSearchPhraseDocument {
    public List<Integer> search(List<Pair<Integer, String>> documents, String phrase) {
        if (phrase == null || phrase.length() == 0) {
            return new ArrayList<>();
        }

        // Build inverted index with position
        Map<String, Set<Pair<Integer, Integer>>> invertedIndex = new HashMap<>();
        for (Pair<Integer, String> document : documents) {
            Integer docId = document.getKey();
            String text = document.getValue().replaceAll("[.,!]", "").toLowerCase();
            String[] words = text.split(" ");
            for (int j = 0; j < words.length; j++) {
                invertedIndex.computeIfAbsent(words[j], k -> new HashSet<>()).add(new Pair<>(docId, j));
            }
        }

        // Populate candidates from first word index lookup
        String[] phraseWords = phrase.toLowerCase().split(" ");
        Set<Pair<Integer, Integer>> candidates = invertedIndex.getOrDefault(phraseWords[0], new HashSet<>());

        for (int i = 1; i < phraseWords.length; i++) {
            Set<Pair<Integer, Integer>> phraseWordIdx = invertedIndex.getOrDefault(phraseWords[i], new HashSet<>());
            Set<Pair<Integer, Integer>> newCandidates = new HashSet<>();
            for (Pair<Integer, Integer> pair : candidates) {
                Pair<Integer, Integer> nextWordIdx = new Pair<>((pair.getKey()), pair.getValue() + 1);
                if (phraseWordIdx.contains(nextWordIdx)) {
                    newCandidates.add(nextWordIdx);
                }
            }
            candidates = newCandidates;
        }

        Set<Integer> matchingDocuments = new HashSet<>();
        for (Pair<Integer, Integer> wordIdx: candidates) {
            matchingDocuments.add(wordIdx.getKey());
        }

        return new ArrayList<>(matchingDocuments);
    }

    public static void main(String[] args) {
        ConfluentSearchPhraseDocument searchPhraseDocument = new ConfluentSearchPhraseDocument();
        Pair<Integer,String> pair1 = new Pair<>(1, "Cloud computing is the on-demand availability of computer system resources.");
        Pair<Integer,String> pair2 = new Pair<>(2, "One integrated service for metrics uptime cloud monitoring dashboards and alerts reduces time spent navigating between systems.");
        Pair<Integer,String> pair3 = new Pair<>(3, "Monitor entire cloud infrastructure, whether in the cloud computing is or in virtualized data centers.");
        List<Pair<Integer,String>> list = List.of(pair1, pair2, pair3);
        searchPhraseDocument.search(list, "cloud");
    }
}
