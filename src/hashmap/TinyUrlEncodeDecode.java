package hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and
 * it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
 *
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 * Implement the Solution class:
 *
 * Solution() Initializes the object of the system.
 * String encode(String longUrl) Returns a tiny URL for the given longUrl.
 * String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.
 */
public class TinyUrlEncodeDecode {
    private static final String BASE="http://tinyurl.com/";
    private Long count=0l;
    Map<String,String> tinyToStr = new HashMap<>();
    Map<String,String> strToTiny = new HashMap<>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(strToTiny.containsKey(longUrl)) {
            return strToTiny.get(longUrl);
        }
        String tinyUrl=BASE+ count;
        tinyToStr.put(tinyUrl, longUrl);
        strToTiny.put(longUrl, tinyUrl);
        return tinyUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return tinyToStr.get(shortUrl);
    }
}
