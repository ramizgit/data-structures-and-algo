package graph.bfs;

import java.util.*;

public class MinimumGeneticMutation {

    //https://leetcode.com/problems/minimum-genetic-mutation/description/

    private static final char[] CHOICES = {'A','C','G','T'};

    public int minMutation(String startGene, String endGene, String[] bank)
    {
        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new State(startGene, 0)); //starting gene

        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        Set<String> validGenes = new HashSet<>();
        validGenes.addAll(Arrays.asList(bank));

        //edge case
        if (!validGenes.contains(endGene)) {
            return -1;
        }

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //exit condition
            if(curr.gene.equals(endGene)){
                return curr.steps;
            }

            //explore neighbours
            for(String neighbour : getNeighbours(curr.gene)){

                if(validGenes.contains(neighbour) && !visited.contains(neighbour)){
                    visited.add(neighbour);
                    bfsQueue.offer(new State(neighbour, curr.steps + 1));
                }
            }
        }

        return -1;
    }

    private List<String> getNeighbours(String input)
    {
        List<String> neighbours = new ArrayList<>();

        char[] arr = input.toCharArray();

        for(int i=0; i<arr.length; i++){

            char original = arr[i];

            for(char choice : CHOICES){
                if(choice == original){
                    continue;
                }

                arr[i] = choice; //mutate

                neighbours.add(new String(arr));
            }

            arr[i] = original; //restore original char before proceeding to next char
        }

        return neighbours;
    }

    static class State{
        String gene;
        int steps;

        public State(String gene, int steps) {
            this.gene = gene;
            this.steps = steps;
        }
    }
}
