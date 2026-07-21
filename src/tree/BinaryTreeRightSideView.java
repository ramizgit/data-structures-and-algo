package tree;

import java.util.*;

public class BinaryTreeRightSideView {


    public List<Integer> rightSideView(TreeNode root)
    {
        List<Integer> result = new ArrayList<>();

        dfs(root, 0, new HashSet<>(), result);

        return result;
    }

    private void dfs(TreeNode node, int level, Set<Integer> seen, List<Integer> result)
    {
        if(node == null){
            return;
        }

        if(!seen.contains(level)){
            result.add(node.val);
            seen.add(level);
        }

        dfs(node.right, level+1, seen, result);
        dfs(node.left, level+1, seen, result);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
