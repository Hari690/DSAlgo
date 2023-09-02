class WordBreakExtraCharactersInString {
    public int minExtraChar(String s, String[] dictionary) {
        int len=s.length();
        Map<Integer,Integer> cache = new HashMap<>();
        Set<String> nm=new HashSet<>();
        for(String k:dictionary)
        {
            nm.add(k);
        }
        return check(cache,s,len,0,nm);
    }
    public int task(Map<Integer,Integer> cache, String s, int len, int ind, Set<String> nm)
    {
        if(ind==len)
            return 0;
        
        if(cache.containsKey(ind))
            return cache.get(ind);
        
        StringBuilder k=new StringBuilder();
        int ans=len;
        for(int point=ind;point<len;point++)
        {
            k.append(s.charAt(point));
            int val=nm.contains(k.toString()) ? 0 : k.length();
            int val1=check(cache,s,len,point+1,nm);
            ans=Math.min(ans,val+val1);
        }
        cache.put(ind,ans);
        return ans;
    }
}
