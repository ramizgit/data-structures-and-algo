package meta;

import tree.Node;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeAllPaths {

    public static void main(String[] args)
    {
        //note : nodes are guaranteed to be found in the tree

        Node node12 = new Node(12, null, null);
        Node node10 = new Node(10, null, null);
        Node node11 = new Node(11, node12, null);
        Node node7 = new Node(7, node10, node11);
        Node node6 = new Node(6, null, null);
        Node node5 = new Node(5, null, null);
        Node node4 = new Node(4, null, null);
        Node node3 = new Node(3, node6, node7);
        Node node2 = new Node(2, node4, node5);
        Node node = new Node(1, node2, node3);

        /*
              1
           2     3
        4    5  6    7
                   10   11
                      12
         */

        System.out.println(allPaths(node));

        System.out.println(pathToTargetNode(node, 10));

        System.out.println(sumRootToLeafNums(node));

    }

    private static List<List<Integer>> allPaths(Node node)
    {
        List<List<Integer>> paths = new ArrayList<>();
        dfs(node, new ArrayList<>(), paths);
        return paths;
    }

    private static void dfs(Node node, List<Integer> currentPath, List<List<Integer>> paths)
    {
        if(node == null){
            return;
        }

        currentPath.add(node.value);

        //collect if we reach a left node
        if(node.left == null && node.right == null){
            paths.add(new ArrayList<>(currentPath));
        }else {
            dfs(node.left, currentPath, paths);
            dfs(node.right, currentPath, paths);
        }

        currentPath.remove(currentPath.size()-1); //backtrack
    }

    private static List<Integer> pathToTargetNode(Node node, int target)
    {
        List<Integer> answer = new ArrayList<>();
        dfs(node, target, new ArrayList<>(), answer);
        return answer;
    }

    private static void dfs(Node node, int target, List<Integer> currentPath, List<Integer> answer)
    {
        if(node == null){
            return;
        }

        currentPath.add(node.value);

        if(target == node.value){
            answer.addAll(new ArrayList<>(currentPath));
            return;
        }else {
            dfs(node.left, target, currentPath, answer);
            dfs(node.right, target, currentPath, answer);
        }

        currentPath.remove(currentPath.size()-1); //backtrack
    }

    private static int sumRootToLeafNums(Node node)
    {
        return dfs(node, 0);
    }

    private static int dfs(Node node, int curr)
    {
        if(node == null){
            return 0;
        }

        curr = curr * 10 + node.value;

        if(node.left == null && node.right == null){
            return curr;
        }

        return dfs(node.left, curr) + dfs(node.right, curr);
    }
}
