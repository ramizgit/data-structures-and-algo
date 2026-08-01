package graph.bfs;

import java.util.*;

public class RaceCar {

    //https://leetcode.com/problems/race-car/description

    /*
    queue ← (0,1)

    visited ← {(0,1)}

    steps = 0

    while queue not empty

        process current level

            pop state

            if position == target
                return steps

            generate Accelerate

            if valid and not visited
                push

            generate Reverse

            if valid and not visited
                push

        steps++
     */

    public int racecar(int target)
    {
        Queue<State> bfsQueue = new ArrayDeque<>();
        State start = new State(0,1,0);
        bfsQueue.offer(start);

        Set<State> visited = new HashSet<>();
        visited.add(start);

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            if(curr.position == target){
                return curr.steps;
            }

            //accelerate
            int newPosition = curr.position + curr.speed;
            int newSpeed = curr.speed * 2;

            State newState = new State(newPosition, newSpeed, curr.steps + 1);

            if(Math.abs(newPosition) <= 2 * target && !visited.contains(newState)){ //pruning
                visited.add(newState);
                bfsQueue.offer(newState);
            }

            //reverse
            newPosition = curr.position; //position unchanged
            newSpeed = curr.speed > 0 ? -1 : 1;

            newState = new State(newPosition, newSpeed, curr.steps + 1);

            if(Math.abs(newPosition) <= 2 * target && !visited.contains(newState)){ //pruning
                visited.add(newState);
                bfsQueue.offer(newState);
            }
        }

        return -1; //should not reach here
    }

    static class State{
        int position;
        int speed;
        int steps;

        public State(int position, int speed, int steps) {
            this.position = position;
            this.speed = speed;
            this.steps = steps;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return position == state.position && speed == state.speed;
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, speed);
        }
    }
}
