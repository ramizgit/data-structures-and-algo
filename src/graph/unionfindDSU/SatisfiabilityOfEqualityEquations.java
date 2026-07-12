package graph.unionfindDSU;

public class SatisfiabilityOfEqualityEquations {

    //https://leetcode.com/problems/satisfiability-of-equality-equations/description/

    /*
    Approach:
    1. Union all variables connected by equality (==) equations.
    2. After all equalities are processed, variables in the same DSU component
       represent the same value.
    3. Verify every inequality (!=). If both variables belong to the same
       component, the equations are unsatisfiable.
    */

    // Time  : O(n * α(26)) ≈ O(n)
    // Space : O(1)
    public boolean equationsPossible(String[] equations)
    {

        //input validation
        if(equations == null || equations.length == 0){
            return true;
        }

        UnionFind uf = new UnionFind(26); //for 26 lowercase letters

        //union == edges
        for(String equation : equations){
            if(equation.charAt(1) == '='){
                int ch1 = equation.charAt(0) - 'a';
                int ch2 = equation.charAt(3) - 'a';

                uf.union(ch1, ch2);
            }
        }

        //check no edge exits for !=
        for(String equation : equations){
            if(equation.charAt(1) == '!'){
                int ch1 = equation.charAt(0) - 'a';
                int ch2 = equation.charAt(3) - 'a';

                if(uf.find(ch1) == uf.find(ch2)){
                    return false;
                }
            }
        }

        return true;
    }
}
