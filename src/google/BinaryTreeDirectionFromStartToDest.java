package google;

import java.util.*;

public class BinaryTreeDirectionFromStartToDest {

    public String getDirections(TreeNode root, int startValue, int destValue)
    {
        StringBuilder startPath = new StringBuilder();
        dfs(root, startValue, startPath);

        StringBuilder destPath = new StringBuilder();
        dfs(root, destValue, destPath);

        // find common prefix length
        int i = 0;
        while (i < startPath.length() &&
                i < destPath.length() &&
                startPath.charAt(i) == destPath.charAt(i)) {

            i++;
        }

        StringBuilder result = new StringBuilder();
        // move up from start to LCA
        for (int j = i; j < startPath.length(); j++) {
            result.append('U');
        }

        // move from LCA to destination
        for (int j = i; j < destPath.length(); j++) {
            result.append(destPath.charAt(j));
        }

        return result.toString();
    }

    private boolean dfs(TreeNode node, int target, StringBuilder path)
    {
        if (node == null) {
            return false;
        }

        if (node.val == target) {
            return true;
        }

        // go left
        path.append('L');
        if (dfs(node.left, target, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1); //backtrack

        // go right
        path.append('R');
        if (dfs(node.right, target, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1); //backtrack

        return false;
    }

    class TreeNode {
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
