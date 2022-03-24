package heaps;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**

 Problem statement:
 Spotify is looking to build a new feature that lets users browse the “top-lists” for different countries in the world. We’d like to build a backend library that will provide the data for this feature. Another team has already computed the top 2500 tracks for all Spotify countries (called TrackStore). We want to build the service interface on top of it.

 You are given an interface called TrackStore, which provides lists of played tracks per country (there are around 250 countries). The API provided by TrackStore is as follows:

 ~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·

 Tasks for the candidate:

 1. Create a class that depends on TrackStore and implement a method that, given a country code, returns the n most played tracks in that country.

 2. Add a method that returns a list of the n most played tracks across all countries.

 3. Imagine that the ranking of tracks is calculated in a batch process and a new ranking is produced every 30 minutes and written to the storage that latest() reads from. Write the code to retrieve the latest TrackStore and update the class you implemented above.

 **/


public class SpotifyTracks {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Hello world");

        TopTracks topTracks = new TopTracks();
        // List<TrackInfo> topTracksList = topTracks.getTopNTracks(10,"SE");
        // topTracksList.forEach(System.out::println);

        List<String> topTracksUrns = topTracks.getTopNTracksAllCountries(10);
        topTracksUrns.forEach(System.out::println);

    /*
    // Example of how to use TrackStore
    TrackStore myStore = TrackStore.latest(5000);
    System.out.println(myStore.getCountries());
    */
    }
}

class TopTracks {

    private TrackStore trackStore = null;

    TopTracks(){
        try{
            trackStore = TrackStore.latest(5000);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<TrackInfo> getTopNTracks(int n, String country) {
        Queue<TrackInfo> minHeap = new PriorityQueue<>(Comparator.comparingInt(TrackInfo::count));

        List<TrackInfo> trackInfos = trackStore.getTracks(country);

        for(TrackInfo trackInfo : trackInfos) {
            minHeap.add(trackInfo);
            if(minHeap.size()>n)
                minHeap.remove();
        }

        List<TrackInfo> topTracks = new ArrayList<>();
        while(!minHeap.isEmpty()) {
            topTracks.add(minHeap.poll());
        }

        return topTracks;
    }

    public List<String> getTopNTracksAllCountries(int n) {

        Set<String> countries = trackStore.getCountries();

        List<TrackInfo> trackInfos = new ArrayList<>();

        for(String country : countries) {
            trackInfos.addAll(trackStore.getTracks(country));
        }

        Map<String,Long> urnCounts = new HashMap<>();

        for(TrackInfo trackInfo : trackInfos) {
            urnCounts.put(trackInfo.urn(),
                    urnCounts.getOrDefault(trackInfo.urn(),0l)+trackInfo.count());
        }

        Queue<Map.Entry<String,Long>> minHeap = new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue));

        for(Map.Entry<String,Long> urnCount : urnCounts.entrySet()) {
            minHeap.add(urnCount);
            if(minHeap.size()>n)
                minHeap.remove();
        }

        List<String> topTracks = new ArrayList<>();
        while(!minHeap.isEmpty()) {
            topTracks.add(minHeap.poll().getKey());
        }

        return topTracks;
    }

}





interface TrackStore {

    /**
     * Retrieves up to date track data from the backend and creates a new track store.
     *
     * @param timeout timeout in milliseconds for retrieving the new data
     *
     * @return a track store with up do date data
     *
     * @throws IOException      if a network error occurs
     * @throws TimeoutException if the timeout is exceeded
     */
    static TrackStore latest(long timeout) throws IOException, TimeoutException {
        return new InMemoryTrackStore(timeout);
    }

    /**
     * @return a list of all country codes contained in the store.
     */
    Set<String> getCountries();

    /**
     * Gets all tracks for a country.
     *
     * @param country two digit uppercase country code, like "SE"
     *
     * @return a list of TrackInfo objects, can be empty
     */
    List<TrackInfo> getTracks(String country);

    /**
     * Version of the data in store; a greater number means newer data.
     *
     * Two stores with the same version number contain exactly the same data.
     *
     * @return version number as a long
     */
    long getVersion();

    /**
     * @return total number of entries in the store
     */
    int getTotalTrackEntries();
}



final class TrackInfo {

    private final String country;
    private final int count;
    private final String genre;
    private final String urn;

    public TrackInfo(final String country, final String genre, final String urn, final int count) {
        this.country = country;
        this.count = count;
        this.genre = genre;
        this.urn = urn;
    }

    public String country() {
        return country;
    }

    /**
     * @return number of times the track was played in the particular country.
     */
    public int count() {
        return count;
    }

    public String genre() {
        return genre;
    }

    public String urn() {
        return urn;
    }

    @Override
    public String toString() {
        return "TrackInfo{" +
                "country='" + country + '\'' +
                ", count=" + count +
                ", genre='" + genre + '\'' +
                ", urn='" + urn + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TrackInfo trackInfo = (TrackInfo) o;
        return count == trackInfo.count &&
                Objects.equals(country, trackInfo.country) &&
                Objects.equals(genre, trackInfo.genre) &&
                Objects.equals(urn, trackInfo.urn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, count, genre, urn);
    }
}



/**
 * Dummy implementation of {@link TrackStore}.
 *
 * YOU DO NOT HAVE TO LOOK AT THIS CODE!!
 *
 * You're not really supposed to look here. Refer to the API documentation of TrackStore.
 */
final class InMemoryTrackStore implements TrackStore {

    private static final Random RANDOM = new Random(1234L);

    private final int totalTrackEntries;
    private final long version;
    private final Map<String, List<TrackInfo>> trackStore;

    InMemoryTrackStore(final long timeout) throws IOException, TimeoutException {
        this.version = System.currentTimeMillis();

        //List<String> countries = Arrays.asList(Locale.getISOCountries());
        List <String> countries = Arrays.asList("SE", "US", "FR", "GA", "GB", "GD", "GE", "GF", "GG", "GH", "GI");
        List<String> genres = generate("genre_", 50);
        List<String> tracks = generate("track:", 50);

        totalTrackEntries = countries.size() * genres.size() * tracks.size();

        List<List<String>> combinations = combine(new LinkedList<List<String>>() {{
            add(countries);
            add(genres);
            add(tracks);
        }});

        trackStore = generate(combinations);
    }


    @Override
    public Set<String> getCountries() {return trackStore.keySet(); }

    @Override
    public List<TrackInfo> getTracks(final String country) {
        return trackStore.getOrDefault(country, new LinkedList<>());
    }

    @Override
    public long getVersion() {
        return version;
    }

    @Override
    public int getTotalTrackEntries() {
        return totalTrackEntries;
    }

    private Map<String, List<TrackInfo>> generate(final List<List<String>> combinations) {

        final Iterator<Integer> rand = RANDOM.ints(10000, 10000000)
                .limit(combinations.size())
                .iterator();

        return combinations.stream().collect(HashMap::new,
                (HashMap<String, List<TrackInfo>> acc, List<String> element) -> {
                    int count = rand.next();
                    TrackInfo ti = new TrackInfo(
                            element.get(0),
                            element.get(1),
                            "spotify:" + element.get(2) + count,
                            count
                    );
                    acc.compute(ti.country(), (k, v) -> {
                        List<TrackInfo> l = v == null ? new LinkedList<>() : v;
                        l.add(ti);
                        return l;
                    });
                },
                (HashMap<String, List<TrackInfo>> acc1, HashMap<String, List<TrackInfo>> acc2) ->
                        acc2.keySet().forEach((k) -> {
                            acc1.merge(k, acc2.get(k), (v1, v2) -> {
                                v1.addAll(v2);
                                return v1;
                            });
                        })
        );
    }

    private List<String> generate(final String prefix, final int size) {
        return IntStream.range(1, size + 1)
                .collect(() -> new ArrayList<>(size),
                        (List<String> acc, int i) -> acc.add(prefix.concat(Integer.toString(i))),
                        List::addAll);
    }


    private List<List<String>> combine(final List<List<String>> lists) {

        if (lists.isEmpty()) {
            return new LinkedList<>();
        }

        List<String> head = lists.remove(0);

        List<List<String>> combined = combine(lists);

        return head.stream().collect(LinkedList::new,
                (List<List<String>> acc1, String currentVal) -> {
                    if (combined.isEmpty()) {
                        List<String> intermediate = new LinkedList<>();
                        intermediate.add(currentVal);
                        acc1.add(intermediate);
                    } else {
                        acc1.addAll(combined.stream().collect(LinkedList::new,
                                (List<List<String>> acc2, List<String> currentList) -> {
                                    List<String> intermediate = new LinkedList<>();
                                    intermediate.addAll(currentList);
                                    ((LinkedList<String>) intermediate).addFirst(currentVal);
                                    acc2.add(intermediate);
                                },
                                List::addAll));
                    }
                },
                List::addAll);
    }

}