package graph.dfs.reroot;

import java.util.*;

public class HeightOfBinaryTreeAfterSubtreeRemovalQueries {

    // https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/

    //todo : practice

    /*
    Approach:

    1. First DFS (Post-order)
       - Compute the height of every subtree.
       - height[node] = 1 + max(height[left], height[right])
       - Leaf height = 0.

    2. Second DFS (Pre-order / Rerooting)
       - For each node, compute the tree height if its subtree is removed.
       - Pass down:
           a) depth            -> current node's depth from the root.
           b) maxHeightOutside -> tallest root-to-leaf path that does NOT
                                  pass through the current node's subtree.
       - answer[node] = maxHeightOutside.

    3. While moving from a parent to a child:
       - The tallest remaining path after removing the child's subtree can come from:
           a) Above the parent (maxHeightOutside), or
           b) The sibling subtree.
       - Height through sibling:
           depth(parent) + 1 + height[sibling]
       - Pass the maximum of these two values to the child.

    Time: O(n + q)
    Space: O(n)
    */

    private Map<TreeNode, Integer> height = new HashMap<>();
    private Map<Integer, Integer> answer = new HashMap<>();

    public int[] treeQueries(TreeNode root, int[] queries) {

        //frist dfs to compute height of each node
        dfsComputeHeight(root);

        //second dfs to compute height of a node if its subtree is removed.
        dfs(root, 0, 0);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            result[i] = answer.get(queries[i]);
        }

        return result;
    }

    // Post-order DFS
    // Computes subtree height of every node.
    private int dfsComputeHeight(TreeNode node) {

        if (node == null) {
            return -1;
        }

        int leftHeight = dfsComputeHeight(node.left);
        int rightHeight = dfsComputeHeight(node.right);

        int currentHeight = 1 + Math.max(leftHeight, rightHeight);

        height.put(node, currentHeight);

        return currentHeight;
    }

    // Pre-order DFS (reroot)
    private void dfs(TreeNode node, int depth, int maxHeightOutside) {

        if (node == null) {
            return;
        }

        answer.put(node.val, maxHeightOutside);

        int leftHeight = node.left == null ? -1 : height.get(node.left);
        int rightHeight = node.right == null ? -1 : height.get(node.right);

        // Height outside left subtree
        int leftOutside = Math.max(
                maxHeightOutside,
                depth + 1 + rightHeight
        );

        // Height outside right subtree
        int rightOutside = Math.max(
                maxHeightOutside,
                depth + 1 + leftHeight
        );

        dfs(node.left, depth + 1, leftOutside);
        dfs(node.right, depth + 1, rightOutside);
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
