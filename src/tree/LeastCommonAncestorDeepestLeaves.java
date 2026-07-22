package tree;

public class LeastCommonAncestorDeepestLeaves {

    /*
    Problem
    Given the root of a binary tree, return the lowest common ancestor (LCA) of its deepest leaves.
    A leaf is a node with no children.
    The deepest leaves are the leaves that are at the maximum depth in the tree.
    The lowest common ancestor is the lowest node in the tree that has all the deepest leaves in its subtree.

    Example 1
              3
            /   \
           5     1
          / \   / \
         6   2 0   8
            / \
           7   4

    Output:
    2

    because the deepest leaves are 7 and 4, and their LCA is 2.

    Example 2
        1

    Output:

    1
    Example 3
          1
         /
        2
       /
      3

    Output:

    3
     */

    public Node findLca(Node root)
    {
        return dfs(root).lca;
    }

    private Result dfs(Node node)
    {
        //base case
        if (node == null) {
            return new Result(0, null);
        }

        Result left = dfs(node.left);
        Result right = dfs(node.right);

        // Both subtrees have deepest leaves at the same depth
        if (left.depth == right.depth) {
            return new Result(left.depth + 1, node);
        }

        // Left subtree is deeper
        if (left.depth > right.depth) {
            return new Result(left.depth + 1, left.lca);
        }

        // Right subtree is deeper
        return new Result(right.depth + 1, right.lca);
    }

    static class Result {
        int depth;
        Node lca;

        Result(int depth, Node lca) {
            this.depth = depth;
            this.lca = lca;
        }
    }

    static class Node{
        Node left;
        Node right;
        int value;

        public Node(Node left, int value, Node right) {
            this.left = left;
            this.value = value;
            this.right = right;
        }
    }
}
