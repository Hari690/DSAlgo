package hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 *
 * An underground railway system is keeping track of customer travel times between different stations. They are using this data to calculate the average time it takes to travel from one station to another.
 *
 * Implement the UndergroundSystem class:
 *
 * void checkIn(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks in at the station stationName at time t.
 * A customer can only be checked into one place at a time.
 * void checkOut(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks out from the station stationName at time t.
 * double getAverageTime(string startStation, string endStation)
 * Returns the average time it takes to travel from startStation to endStation.
 * The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
 * The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
 * There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
 * You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.
 */
class UndergroundSystem {
    class Pair {
        String key;
        Integer value;
        Pair(String k, int val) {
            this.key = k;
            this.value = val;
        }
    }
    private Map<Integer, Pair> start;
    private Map<String, List<Integer>> trips;
    public UndergroundSystem() {
        start = new HashMap<>();
        trips = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        Pair pair = start.get(id);
        if(pair==null) {
            pair = new Pair(stationName,t);
            start.put(id, pair);
        }
    }

    public void checkOut(int id, String stationName, int t) {
        Pair pair = start.get(id);
        List<Integer> endTimes = trips.get(stationName+"#"+pair.key);
        if(endTimes==null) {
            endTimes = new ArrayList<>();
            trips.put(stationName+"#"+pair.key, endTimes);
        }
        endTimes.add(t-pair.value);
        start.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        List<Integer> times = trips.get(endStation+"#"+startStation);
        long sum=0;
        for(int i=0;i<times.size();i++) {
            sum+=times.get(i);
        }
        return (double)sum/times.size();
    }
}