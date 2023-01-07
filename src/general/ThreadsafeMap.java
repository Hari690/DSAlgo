package general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class ThreadsafeMap {

    Map<String, List<String>> concurrentMap = new HashMap<>();

    public static void main(String[] args) {
        ConcurrentHashMap<String, LongAdder> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("key", k -> new LongAdder()).increment();
    }

    public void record(String key, String value) {
        concurrentMap.compute(key, (k, v) -> {
            List<String> vals = v;
            if(vals == null)
                vals = new ArrayList<>();
            vals.add(value);
            return vals;
        });
    }

    public void delete(String key, String value) {
        concurrentMap.compute(key, (k, v) -> {
            List<String> vals = v;
            if(vals == null)
                return null; // No mapping, return null to keep the status quo

            vals.remove(value); // Or whatever you intend to do
            return vals.isEmpty() ? null : vals;
        });
    }
}
