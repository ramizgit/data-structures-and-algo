package consistenthashing.graph.topologicalsorting;

import java.util.*;

public class CompilePkgWithDependenciesInMultiThdEnv {

    //https://codezym.com/question/145

    /*
    You are given a dependency graph of packages to compile in a multithreaded environment.

The graph is provided as List<String> packages. Each string represents one row of the graph as a comma-separated list with no whitespace.

For a row "a,b,c", package a depends on packages b and c. This means b and c must be compiled before a.

For a row containing only one package name such as "x", package x has no dependencies.

A package can appear as a dependency even if it does not have its own row. Such a package is still part of the graph and is treated as a package with no dependencies unless it appears as the first package of some other row.

The number of worker threads is configurable. In one compilation round, you may compile at most threadCount packages that were already ready at the start of that round.

A package is ready if all of its dependencies have already been compiled in earlier rounds. Packages that become ready during a round can only be compiled in a later round.

In each round, if more than threadCount packages are ready, choose the lexicographically smaller package names first so that the result is deterministic.

Return the order in which packages are compiled across all rounds.

If the dependency graph contains a cycle, return an empty list.
Method Signature
List<String> compilePackages(List<String> packages, int threadCount)
packages is the dependency graph, where each row is a comma-separated string with no whitespace.
In a row "a,b,c", a is the package and b, c are its direct dependencies.
In a row "a", a has no direct dependencies.
threadCount is the maximum number of packages that can be compiled in one round.
Return a List<String> containing the packages in the order they are compiled.
Return an empty List<String> if no valid compilation order exists.
Constraints
1 ≤ packages.size() ≤ 10^4
1 ≤ threadCount ≤ 10^3
The total number of distinct packages in the graph is at most 10^5.
The total number of dependency relations is at most 2 * 10^5.
Each package name consists only of lowercase English letters, digits, underscore, or hyphen.
Each row contains no whitespace.
No row contains duplicate dependency names.
Each package appears as the first value in at most one row.
     */

    List<String> compilePackages(String[] packages, int threadCount)
    {
        //build graph
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        //populate edges as per input
        for(String pkg : packages){

            String[] entry = pkg.split(",");
            String firstPkg = entry[0];

            graph.computeIfAbsent(firstPkg, key -> new ArrayList<>());

            //populate dependencies
            for(int i=1; i<entry.length; i++){
                graph.computeIfAbsent(entry[i], key -> new ArrayList<>()).add(firstPkg);
                indegree.putIfAbsent(entry[i], 0);
            }

            indegree.put(firstPkg, entry.length - 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>(); //process packages in lexi order

        for(String key : indegree.keySet()){
            if(indegree.get(key) == 0){
                pq.offer(key);
            }
        }

        List<String> topologicalOrder = new ArrayList<>();

        while(!pq.isEmpty()){

            //process nodes in batches
            List<String> currList = new ArrayList<>();
            for(int i=0; i<threadCount && !pq.isEmpty(); i++){
                currList.add(pq.poll());
            }

            topologicalOrder.addAll(currList);

            //explore neighbours
            for(String curr : currList){
                for(String neighbour : graph.getOrDefault(curr, Collections.emptyList())){

                    indegree.put(neighbour, indegree.get(neighbour) - 1);
                    if(indegree.get(neighbour) == 0){
                        pq.offer(neighbour);
                    }
                }
            }
        }

        return topologicalOrder.size() == indegree.size() ? topologicalOrder : Collections.emptyList();
    }
}
