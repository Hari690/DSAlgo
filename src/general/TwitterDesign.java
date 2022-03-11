package general;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most
 * recent tweets in the user's news feed.
 *
 * Implement the Twitter class:
 *
 * Twitter() Initializes your twitter object.
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be
 * made with a unique tweetId.
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must
 * be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
 * void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
 * void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
 */
public class TwitterDesign {

    /*
    Idea is to hold a list of all users tweets and set a timestamp for each.
    Next use a heap to check most recent 10 tweets of followers which includes the user and return it.
    DS -> userId -> List of Tweets
       -> userId -> followers.
    Also add the user as his own follower when first tweet is posted for that user.
     */
    private static class Tweet{
        int tweetId;
        int timePosted;
        public Tweet(int tId, int time){
            tweetId = tId;
            timePosted = time;
        }
    }

    static int timeStamp;
    int feedMaxNum;
    Map<Integer, Set<Integer>> followees;
    Map<Integer, List<Tweet>> tweets;

    /** Initialize your data structure here. */
    public TwitterDesign() {
        timeStamp = 0;
        feedMaxNum = 10;
        followees = new HashMap<>();
        tweets = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!tweets.containsKey(userId)) {
            tweets.put(userId, new LinkedList<>());
            follow(userId, userId);  //follow itself
        }
        tweets.get(userId).add(0, new Tweet(tweetId, timeStamp++)); //add new tweet on the first place
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        //min heap that the earliest tweet is on the top
        PriorityQueue<Tweet> feedHeap = new PriorityQueue<>(new Comparator<Tweet>(){
            public int compare(Tweet t1, Tweet t2){
                return t1.timePosted - t2.timePosted;
            }
        });

        //add tweets of the followees
        Set<Integer> myFollowees = followees.get(userId);
        if(myFollowees != null){
            for(int followeeId : myFollowees){
                List<Tweet> followeeTweets = tweets.get(followeeId);
                if(followeeTweets == null) continue;
                for(Tweet t : followeeTweets){
                    if(feedHeap.size() < feedMaxNum) feedHeap.add(t);
                    else{
                        if(t.timePosted <= feedHeap.peek().timePosted) break;
                        else{
                            feedHeap.add(t);
                            feedHeap.poll(); //remove the oldest tweet
                        }
                    }
                }
            }
        }
        List<Integer> myFeed = new LinkedList<>();
        while(!feedHeap.isEmpty()){
            myFeed.add(0, feedHeap.poll().tweetId);
        }
        return myFeed;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!followees.containsKey(followerId)) followees.put(followerId, new HashSet<>());
        followees.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!followees.containsKey(followerId) || followerId == followeeId) return; //cannot unfollow itself
        followees.get(followerId).remove(followeeId);
    }

    public static void main(String[] args) {
        /*
        ["Twitter","postTweet","getNewsFeed","follow","getNewsFeed","unfollow","getNewsFeed"]
[[],[1,1],[1],[2,1],[2],[2,1],[2]]
         */

        TwitterDesign twitterDesign = new TwitterDesign();
        twitterDesign.postTweet(1,1);
        twitterDesign.getNewsFeed(1);
        twitterDesign.follow(2,1);
        twitterDesign.getNewsFeed(2);
        twitterDesign.unfollow(2, 1);
        twitterDesign.getNewsFeed(2);
    }
}
