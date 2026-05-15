package google;

import java.util.*;

public class BestTeamWithNoConflicts {

    //https://leetcode.com/problems/best-team-with-no-conflicts/description/

    //Time  : O(n²)
    //Space : O(n)
    public int bestTeamScore(int[] scores, int[] ages) {

        /*
        APPROACH:-
        After sorting by age: all younger players already come before older players
        So to avoid conflicts: scores must be non-decreasing
        That becomes LIS-style DP.
         */

        int n = scores.length;

        //create list of players
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            players.add(new Player(scores[i], ages[i]));
        }

        // sort by age asc, if same age, then sort by score asc
        players.sort((a, b) -> {
            if(a.age == b.age){
                return a.score - b.score; //order by score in ascending order if same age, in order to maximize team score
            }

            return a.age - b.age; //order by age in ascending order
        });

        //LIS style dp
        int[] dp = new int[n]; //dp[i] = max team score ending at i
        for (int i = 0; i < n; i++) {
            dp[i] = players.get(i).score; //base case
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            Player curr = players.get(i);
            for (int j = 0; j < i; j++) {
                Player prev = players.get(j);
                // ensure non-decreasing scores, as prev player is younger than curr player
                if(prev.score <= curr.score){
                    dp[i] = Math.max(dp[i], curr.score + dp[j]);
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    class Player{
        int score;
        int age;

        public Player(int score, int age) {
            this.score = score;
            this.age = age;
        }
    }
}

