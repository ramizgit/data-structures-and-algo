package misc;

import java.util.*;

public class DesignTwitter {

    //todo : implement

    //https://leetcode.com/problems/design-twitter/description/

    /*
    Data Structures

    1. HashMap<Integer, HashSet<Integer>> following
       - user -> users they follow
       - HashSet gives O(1) follow, unfollow, contains
       - Prevents duplicate follow relationships

    2. HashMap<Integer, List<Tweet>> tweets
       - user -> tweets posted by that user
       - Tweets are stored in chronological order
       - Appending a new tweet is O(1)

    3. Tweet class
       - int tweetId
       - int timestamp
       - Global timestamp increments for every new tweet

    4. PriorityQueue<FeedNode> (Max Heap)
       - Used only inside getNewsFeed()
       - Orders tweets by timestamp (newest first)

    5. FeedNode class
       - int tweetId
       - int timestamp
       - int userId
       - int index   // index of this tweet in user's tweet list
    */

    /*
    postTweet(userId, tweetId)

    1. Create a Tweet(tweetId, timestamp++)
    2. Append it to the user's tweet list
    Time: O(1)
    */

    /*
    follow(followerId, followeeId)

    1. Create HashSet if needed
    2. Add followeeId to follower's follow set
    Time: O(1)
    */


    /*
    unfollow(followerId, followeeId)

    1. If follower exists, remove followeeId from HashSet
    Time: O(1)
    */

    /*
    getNewsFeed(userId)

    1. Collect:
       - user's own tweets
       - tweets of every user they follow

    2. Push only the latest tweet from each user into a max heap.

    3. While heap is not empty and answer has fewer than 10 tweets:
       - Pop newest tweet
       - Add tweetId to answer
       - Push the previous tweet from the same user (if any)

    This is a k-way merge of sorted tweet lists.

    Time:
    O((F + 10) log F)

    F = number of users contributing tweets
    (user + followees)
    */

    class Twitter {

        public Twitter() {

        }

        public void postTweet(int userId, int tweetId) {

        }

        public List<Integer> getNewsFeed(int userId) {

            return null;
        }

        public void follow(int followerId, int followeeId) {

        }

        public void unfollow(int followerId, int followeeId) {

        }
    }
}
