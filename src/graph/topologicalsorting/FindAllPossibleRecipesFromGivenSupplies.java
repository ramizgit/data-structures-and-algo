package graph.topologicalsorting;

import java.util.*;

public class FindAllPossibleRecipesFromGivenSupplies {

    /*
    recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]]
    supplies = ["yeast","flour","meat"]
     */

    public List<String> findAllRecipes(String[] recipes, String[][] ingredients, String[] supplies)
    {
        //initialize graph + indegree
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        //populate graph + indegree
        for(int i=0; i<recipes.length; i++){

            //populate edge from ingredient -> recipe
            for(String ingredient : ingredients[i]){
                graph.computeIfAbsent(ingredient, key -> new ArrayList<>()).add(recipes[i]);
            }

            //populate indegree for recipe
            indegree.put(recipes[i], ingredients[i].length);
        }

        //run BFS logic, start with supply
        Queue<String> queue = new ArrayDeque<>();
        for(String ingredient : supplies){
            queue.offer(ingredient);
        }

        List<String> result = new ArrayList<>();

        while(!queue.isEmpty()){
            String curr = queue.poll();

            //explore neighbours
            for(String recipe : graph.getOrDefault(curr, new ArrayList<>())){
                //reduce indegree
                indegree.put(recipe, indegree.getOrDefault(recipe, 0) - 1);

                if(indegree.get(recipe) == 0){
                    result.add(recipe); //add to result as all ingredients for this recipe is available
                    queue.offer(recipe); //also add back to queue to be used as ingredient for other recipes if needed
                }
            }
        }

        return result;
    }
}
