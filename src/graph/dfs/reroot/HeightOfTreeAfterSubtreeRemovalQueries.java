package graph.dfs.reroot;

public class HeightOfTreeAfterSubtreeRemovalQueries {

    //https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/description/

    //todo : implement

    /*
    Approach:

    1. First DFS (Post-order)
       - Compute the height of every subtree.
       - height[node] = 1 + max(height[left], height[right])
       - Leaf height = 0.

    2. Second DFS (Pre-order / Rerooting)
       - For each node, compute the tree height if its subtree is removed.
       - Pass down:
           a) depth                -> current node's depth from the root.
           b) maxHeightOutside     -> tallest root-to-leaf path that does NOT
                                      pass through the current node's subtree.
       - answer[node] = maxHeightOutside.

    3. While moving from a parent to a child:
       - The tallest remaining path after removing the child's subtree can come from:
           a) Above the parent (maxHeightOutside), or
           b) The sibling subtree.
       - Height through sibling:
           depth(parent) + 1 + height[sibling]
           where:
             depth(parent) = root -> parent
             +1            = parent -> sibling
             height[sibling] = deepest path inside sibling subtree
       - Pass the maximum of these two values to the child.

    Time Complexity: O(n + q)
    Space Complexity: O(n)
    */

    public int[] treeQueries(TreeNode root, int[] queries)
    {

        return null;
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
