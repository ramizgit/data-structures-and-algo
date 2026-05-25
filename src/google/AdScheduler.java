package google;

import java.util.*;

/*
Given APIs:

insertAd(string content, int score)
getAd()
Requirements:

getAd() should always return the highest scored ad
After returning an ad, decrease its score
Consecutive same ads should not appear
Follow-up:
Introduce a cooldown/gap before the same ad can appear again while keeping operations close to O(1).
 */

public class AdScheduler {

    class AdSchedulerImpl{

        private PriorityQueue<Ad> availableAdsQueue; //maxheap
        private Queue<Ad> cooldownQueue;

        private int currentTime;
        private int cooldown;

        AdSchedulerImpl(int cooldown)
        {
            this.availableAdsQueue = new PriorityQueue<>( (a, b) -> b.score - a.score );
            /*
            Optional :-
            (a, b) -> {
                if (b.score == a.score) {
                    return a.content.compareTo(b.content);
                }
                return b.score - a.score;
            }
             */

            this.cooldownQueue = new ArrayDeque<>();

            this.currentTime = 0;
            this.cooldown = cooldown;
        }

        public void insertAd(String content, int score)
        {
            this.availableAdsQueue.offer(new Ad(content, score));
        }

        public String getAd()
        {
            currentTime++;

            //move ads from cooldown queue to available ads queue
            while(!this.cooldownQueue.isEmpty() && this.cooldownQueue.peek().nextAvailableTime <= currentTime){
                this.availableAdsQueue.offer(this.cooldownQueue.poll());
            }

            //handle empty available queue
            if(this.availableAdsQueue.isEmpty()){
                return null;
            }

            //poll max score ads
            Ad bestAd = this.availableAdsQueue.poll();
            bestAd.score--;

            //add it to cooldown queue
            if(bestAd.score > 0){
                bestAd.nextAvailableTime = currentTime + this.cooldown +1;
                this.cooldownQueue.offer(bestAd);
            }

            return bestAd.content;
        }
    }

    //bean/pojo
    class Ad{
        String content;
        int score;

        int nextAvailableTime;

        public Ad(String content, int score) {
            this.content = content;
            this.score = score;
        }
    }
}
