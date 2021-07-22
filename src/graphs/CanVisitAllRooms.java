package graphs;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class CanVisitAllRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Stack<Integer> dfs = new Stack<>();
        dfs.add(0);
        HashSet<Integer> seen = new HashSet<>();
        seen.add(0);
        while (!dfs.isEmpty()) {
            int i = dfs.pop();
            for (int j : rooms.get(i)) {
                if (!seen.contains(j)) {
                    dfs.add(j);
                    seen.add(j);
                    if (rooms.size() == seen.size()) {
                        return true;
                    }
                }
            }
        }
        return rooms.size() == seen.size();
    }
}
