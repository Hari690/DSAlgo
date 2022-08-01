package dynamicprogramming;

import java.util.Arrays;

public class MinWorkSessionsFinishTask {
    int res;
    public int minSessions(int[] tasks, int sessionTime) {
        this.res = tasks.length;
        int[] sessions = new int[tasks.length];
        dfs(0, 0, sessions, tasks, sessionTime);
        return res;
    }

    private void dfs(int taskID, int sessionCount, int[] sessions, int[] tasks, int sessionTime) {
        if (sessionCount > res) return;
        if (taskID == tasks.length) {
            res = Math.min(res, sessionCount);
            return;
        }
        for (int i = 0; i < sessionCount; i++)
            if (sessions[i] + tasks[taskID] <= sessionTime) { //put task into old session bucket
                sessions[i] += tasks[taskID];
                dfs(taskID + 1, sessionCount, sessions, tasks, sessionTime);
                sessions[i] -= tasks[taskID];
            }
        sessions[sessionCount] += tasks[taskID]; //put task into new empty session bucket
        dfs(taskID + 1, sessionCount + 1, sessions, tasks, sessionTime);
        sessions[sessionCount] -= tasks[taskID];
    }

    public static void main(String[] args) {
        MinWorkSessionsFinishTask minWorkSessionsFinishTask = new MinWorkSessionsFinishTask();
        int[] arr = {1,2,3};
        System.out.println(minWorkSessionsFinishTask.minSessions(arr, 3));
    }
}
