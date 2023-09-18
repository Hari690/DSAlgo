public class PathWithMinimumEffortDijkstra {
  public int minimumEffortPath(int[][] arr) {
        int n=arr.length,m=arr[0].length;

        boolean [][]visited=new boolean[n][m];        
        Queue<int[]>q=new PriorityQueue<>((a,b)->a[2]-b[2]);
        q.add(new int[]{0,0,0});
        
        while(q.size()>0){
            int[] t=q.poll();
            int r=t[0],c=t[1];
            if(visited[r][c])
                continue;
            
            if(r==n-1&&c==m-1)
                return t[2];
            
            visited[r][c]=true;
            
            if(r>0)
                q.add(new int[]{r-1,c,Math.max(t[2],Math.abs(arr[r-1][c]-arr[r][c]))});
            
            if(r<n-1)
                q.add(new int[]{r+1,c,Math.max(t[2],Math.abs(arr[r+1][c]-arr[r][c]))});
            
            if(c>0)
                q.add(new int[]{r,c-1,Math.max(t[2],Math.abs(arr[r][c-1]-arr[r][c]))});
            
            if(c<m-1)
                q.add(new int[]{r,c+1,Math.max(t[2],Math.abs(arr[r][c+1]-arr[r][c]))});
        }
        return 0;
    }
}
