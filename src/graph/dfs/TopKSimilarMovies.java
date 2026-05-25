package graph.dfs;

import java.util.*;

/*
Given:
Movies with ratings
Similarity graph between movies
Task:
Return the top K movies similar to a given movie.

Follow-up:
Optimize the solution to achieve close to O(log K) time complexity and O(K) space complexity.
 */

/*
Approach:-
First mention brute force : dfs traversal, then sort nodes by rating - O(nlogn)
then mention heap appraoch
 */

public class TopKSimilarMovies {

    public List<Movie> getTopKSimilarMovies(Movie movie, int k)
    {
        //input validation
        if(movie == null || k <= 0){
            return new ArrayList<>();
        }

        PriorityQueue<Movie> minheap = new PriorityQueue<>( (a, b) -> a.rating - b.rating );
        Set<Integer> visited = new HashSet<>();

        dfs(movie, k, minheap, visited);

        List<Movie> result = new ArrayList<>();
        while(!minheap.isEmpty()){
            result.add(minheap.poll());
        }

        Collections.reverse(result);

        return result;
    }

    private void dfs(Movie movie, int k, PriorityQueue<Movie> minheap, Set<Integer> visited)
    {
        //mark visited
        visited.add(movie.id);

        //explore neighbours
        for(Movie neighbour : movie.similarMovies){
            if(!visited.contains(neighbour.id)){
                //process movie
                minheap.offer(neighbour);
                if(minheap.size() > k){
                    minheap.poll();
                }

                dfs(neighbour, k, minheap, visited);
            }
        }
    }

    class Movie {

        int id;
        int rating;

        List<Movie> similarMovies;

        public Movie(int id, int rating) {
            this.id = id;
            this.rating = rating;
            this.similarMovies = new ArrayList<>();
        }
    }
}
