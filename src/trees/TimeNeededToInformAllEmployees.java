package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a tree structure.
 * The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).
 * Return the number of minutes needed to inform all the employees about the urgent news.
 *
 * Example 1:
 * Input: n = 1, headID = 0, manager = [-1], informTime = [0]
 * Output: 0
 * Explanation: The head of the company is the only employee in the company.
 */
public class TimeNeededToInformAllEmployees {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int managerTime = 0;
        for(int i=0;i<manager.length;i++) {
            if(manager[i]!=-1) {
                List<Integer> list = map.get(manager[i]);
                if(list==null)
                    list = new ArrayList<>();
                list.add(i);
                map.put(manager[i], list);
            } else {
                managerTime+=informTime[i];
            }
        }

        int maxTime = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{headID,managerTime});
        while(queue.size()>0) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] node = queue.poll();
                List<Integer> emps = map.get(node[0]);
                int time = node[1];
                if(emps!=null) {
                    for(Integer emp : emps) {
                        int totalTime = time+informTime[emp];
                        maxTime=Math.max(maxTime,totalTime);
                        queue.add(new int[]{emp,totalTime});
                    }
                }
            }
        }
        return maxTime;
    }
}
