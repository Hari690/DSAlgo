package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class OptiverGraph {

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        String routes, destination;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            routes = br.readLine();
            destination = br.readLine();

            String[] routesStr = routes.split(" ");

            Map<Character,Map<Character,Integer>> graph = new HashMap<>();
            boolean isValid = parseAndValidateGraph(routesStr, graph);

            int maxCost = 0;
            char destFrom = 0, destTo=0;
            if(isValid) {
                try {
                    maxCost = validateDestinationAndGetCost(destination, graph);
                    destFrom = destination.charAt(0);
                    destTo = destination.charAt(3);
                } catch ( E1Exception e) {
                    System.out.println("E1");
                    isValid = false;
                } catch ( E2Exception e) {
                    System.out.println("E2");
                    isValid = false;
                }
            }
            if(isValid) {
                findShortestPath(graph, maxCost, destFrom, destTo);
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private static void findShortestPath(Map<Character, Map<Character, Integer>> graph, int maxCost, char destFrom, char destTo) {
        Integer minPathLength=Integer.MAX_VALUE;
        String finalPath = null;
        PriorityQueue<HeapEntry> minHeap = new PriorityQueue<>((a, b) -> a.current.getValue() - b.current.getValue());
        Map<Character, Integer> map = graph.get(destFrom);
        for(Map.Entry<Character,Integer> entry : map.entrySet()) {
            minHeap.add(new HeapEntry(entry, destFrom+"->"+entry.getKey(), entry.getValue()));
        }

        try {
            while (!minHeap.isEmpty()) {
                HeapEntry heapEntry = minHeap.poll();
                if (heapEntry.current.getKey() == destTo && heapEntry.cost <=maxCost) {
                    if(heapEntry.cost==minPathLength) {
                        throw new E2Exception("Two paths found with same cost");
                    }

                    if(heapEntry.cost<minPathLength) {
                        minPathLength = Math.min(minPathLength, heapEntry.cost);
                        finalPath = heapEntry.path;
                    }
                }
                if (heapEntry.cost <= maxCost) {
                    //add adjacent nodes.
                    for(Map.Entry<Character,Integer> entry : graph.get(heapEntry.current.getKey()).entrySet()) {
                        minHeap.add(new HeapEntry(entry, heapEntry.path+"->"+entry.getKey(), heapEntry.cost + entry.getValue()));
                    }
                }
            }
            if( finalPath!=null) {
                System.out.println(finalPath);
            } else {
                System.out.println("E3");
            }
        } catch (E2Exception e) {
            System.out.println("E2");
        }
    }

    private static boolean parseAndValidateGraph(String[] routesStr, Map<Character, Map<Character, Integer>> graph) {
        boolean isValid = false;
        for(String route : routesStr) {
            int cost;
            try {
                cost = validateAndGetCost(route);
            } catch ( Exception e) {
                System.out.println("E1");
                isValid = false;
                break;
            }
            try {
                char from = route.charAt(1),to = route.charAt(3);
                validateAndAddRelation(graph, cost, from, to);

                from = route.charAt(3);
                to = route.charAt(1);
                validateAndAddRelation(graph, cost, from, to);
            } catch ( E2Exception e) {
                System.out.println("E2");
                isValid = false;
                break;
            }
            isValid = true;
        }
        return isValid;
    }

    private static int validateAndGetCost(String route) throws E1Exception {
        if(route.charAt(0)!='[' || route.charAt(2)!=',' || route.charAt(4)!=',' ||
            route.charAt(route.length()-1)!=']') {
            throw new E1Exception("Invalid input");
        }
        int i=5;
        StringBuilder no = new StringBuilder();
        while(i<route.length()-1) {
            no.append(route.charAt(i++));
        }
        return Integer.parseInt(no.toString());
    }

    private static int validateDestinationAndGetCost(String route, Map<Character, Map<Character, Integer>> graph) throws E1Exception,
        E2Exception {
        if(route.charAt(1)!='-' || route.charAt(2)!='>' ||
            route.charAt(4)!=',') {
            throw new E1Exception("Invalid input");
        }
        if(!graph.containsKey(route.charAt(0)) || !graph.containsKey(route.charAt(3))) {
            throw new E2Exception("Invalid input");
        }
        int i=5;
        StringBuilder no = new StringBuilder();
        while(i<route.length()) {
            no.append(route.charAt(i++));
        }
        return Integer.parseInt(no.toString());
    }

    private static void validateAndAddRelation(Map<Character, Map<Character, Integer>> graph, int cost, char from,
                                               char to) throws E2Exception {
        Map<Character, Integer> map = graph.get(from);
        if (map == null)
            map = new HashMap<>();
        if (map.containsKey(to)) {
            throw new E2Exception("Duplicate entry");
        }
        map.put(to, cost);
        graph.put(from, map);
    }

    static class HeapEntry {
        private Map.Entry<Character,Integer> current;
        private String path;
        private int cost;

        public HeapEntry(Map.Entry<Character, Integer> current, String path, int cost) {
            this.current = current;
            this.path = path;
            this.cost = cost;
        }
    }

    static class E1Exception extends Exception {
        public E1Exception(String errorMessage) {
            super(errorMessage);
        }
    }

    static class E2Exception extends Exception {
        public E2Exception(String errorMessage) {
            super(errorMessage);
        }
    }
}


