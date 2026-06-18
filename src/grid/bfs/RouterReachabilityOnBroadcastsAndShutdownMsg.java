package consistenthashing.grid.bfs;

import java.util.*;

/*
You are given a network of routers.

Each router has:
a unique router id
a 2D location (x, y)
a status indicating whether it is WORKING or DEFECTIVE
A special message called Broadcast and Shutdown works as follows:
When a WORKING router receives the message for the first time, it immediately broadcasts the same message to every other WORKING router that lies within the wireless range.
After broadcasting, that router shuts down and can no longer send or receive messages.
A DEFECTIVE router can neither send nor receive the message.
Given the list of routers, the wireless range, a source router id, and a destination router id, determine whether the Broadcast and Shutdown message, when initiated from the source router, will eventually be received by destination router.

Two routers can communicate directly if the Euclidean distance between their coordinates is less than or equal to range.

Return true if the destination router receives the message at any point. Otherwise, return false.
Router Format
To keep the input simple, each router is represented as a string in the following format:

routerId,x,y,status
To make parsing simple, routerId will never contain comma.

Example:
"A,0,0,WORKING"
"B,3,4,DEFECTIVE"
Important Notes
The source router must be WORKING in order to initiate the broadcast.
The destination router is considered reached as soon as it receives the message, even though it will also shut down immediately after rebroadcasting.
A router processes the message at most once.
DEFECTIVE routers must be ignored during message propagation.
No parameter value is null.
Constraints
1 ≤ routers.size() ≤ 10^5
0 ≤ range ≤ 10^6
Each router string has the format routerId,x,y,status
routerId is unique across all routers
-10^6 ≤ x, y ≤ 10^6
status is either WORKING or DEFECTIVE
source and destination are valid router ids present in routers
Method Signatures
Java
boolean canReachDestination(List<String> routers, int range, String source, String destination)
routers contains router descriptions in the format routerId,x,y,status
range is the maximum wireless distance for direct communication
source is the router id where the message starts
destination is the router id to check for reachability
 */

public class RouterReachabilityOnBroadcastsAndShutdownMsg {

    //https://codezym.com/question/149

    //important : this is grid bucketing problem, as we cant afford building graph O(n^2) due to the constraint

    /*
    this technique is often called:

    Spatial Hashing
    Grid Bucketing
    Sparse Grid

    rather than a normal grid
     */

    boolean canReachDestination(List<String> routers, int range, String source, String destination)
    {
        //build buckets
        Map<Cell, List<Router>> buckets = new HashMap<>();
        //Map<String, Router> routerById = new HashMap<>();
        Router sourceRouter = null;
        boolean destinationWorking = false;

        for(String router : routers){

            String[] entry = router.split(",");

            if("DEFECTIVE".equals(entry[3])){
                continue;
            }


            String id = entry[0];
            int x = Integer.parseInt(entry[1]);
            int y = Integer.parseInt(entry[2]);

            int cellX = Math.floorDiv(x, range); // = x / range;
            int cellY = Math.floorDiv(y, range); // = y / range

            Router routerObj = new Router(id, x, y);

            buckets.computeIfAbsent(new Cell(cellX, cellY), key -> new ArrayList<>()).add(routerObj);
            //routerById.put(id, routerObj);

            if(id.equals(source)) {
                sourceRouter = routerObj;
            }

            if(id.equals(destination)) {
                destinationWorking = true;
            }
        }

        /*if (!routerById.containsKey(source) || !routerById.containsKey(destination)){
            return false; //either source or destination is DEFECTIVE
        }*/

        if(sourceRouter == null || !destinationWorking) {
            return false;
        }

        //BFS
        Queue<Router> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(sourceRouter); //starting point

        Set<String> visitedRouters = new HashSet<>();
        visitedRouters.add(source); //starting point

        int[][] directions = { {0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1} };

        while(!bfsQueue.isEmpty()){

            Router curr = bfsQueue.poll();

            //exit condition
            if(curr.id.equals(destination)){
                return true;
            }

            //explore neighbours

            //get current block
            int currCellX = Math.floorDiv(curr.x, range); // = curr.x / range
            int currCellY = Math.floorDiv(curr.y, range); // = curr.y / range

            for(int[] dir : directions){
                int newCellX = currCellX + dir[0];
                int newCellY = currCellY + dir[1];

                Cell neighbourCell = new Cell(newCellX, newCellY);

                for(Router neighbour : buckets.getOrDefault(neighbourCell, Collections.emptyList())){

                    if(visitedRouters.contains(neighbour.id)){
                       continue;
                    }

                    // verify if negihour falls within the given range, via euclidean distance (pythagorean formula)
                    long dx = (long) curr.x - neighbour.x;
                    long dy = (long) curr.y - neighbour.y;

                    long distSquared = dx * dx + dy * dy;

                    if(distSquared <= (long) range * range){
                        visitedRouters.add(neighbour.id);
                        bfsQueue.offer(neighbour);
                    }
                }
            }
        }

        return false;
    }

    class Router{
        String id;
        int x;
        int y;

        public Router(String id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    class Cell{
        int x;
        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
