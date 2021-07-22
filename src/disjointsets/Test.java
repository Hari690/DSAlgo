package disjointsets;

import java.util.HashMap;
import java.util.Map;

class LargestComponentSizeByCommonFactor {
    int find(int x, int[] parent){
        if(parent[x]==-1)
            return x;
        parent[x] = find(parent[x], parent);
        return parent[x];
    }

    void union(int x, int y, int[] parent){
        int xp = find(x, parent);
        int yp = find(y, parent);
        if(xp != yp)
            parent[yp] = xp;
    }
    public int largestComponentSize(int[] A) {
        int[] parent = new int[100001];
        for(int i = 0; i < 100001; ++i) parent[i] = -1;

        for(int x:A){
            for(int i = 2; i <= Math.sqrt(x); ++i){
                if(x % i == 0){
                    union(i, x, parent);
                    union(x, x/i, parent);
                }
            }
        }

        int count = 0;
        Map<Integer, Integer> cache = new HashMap();
        for(int x:A){
            int xp = find(x, parent);
            count = Math.max(count, 1 + cache.getOrDefault(xp, 0));
            cache.put(xp, 1 + cache.getOrDefault(xp, 0));
        }
        return count;
    }
}
