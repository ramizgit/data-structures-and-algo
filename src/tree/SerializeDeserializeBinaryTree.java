package tree;

import java.util.*;

public class SerializeDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root)
    {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        //bfs
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();

            //add to result
            if(curr == null){
                sb.append("null,");
                continue;
            }
            sb.append(curr.val).append(",");

            //explore children
            queue.add(curr.left);
            queue.add(curr.right);
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data)
    {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] nodes = data.split(",");
        //bfs
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        queue.offer(root);

        int idx = 1;

        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();

            if(!"null".equals(nodes[idx])){
                TreeNode left = new TreeNode(Integer.parseInt(nodes[idx]));
                curr.left = left;
                queue.offer(left);
            }
            idx++;

            if(!"null".equals(nodes[idx])){
                TreeNode right = new TreeNode(Integer.parseInt(nodes[idx]));
                curr.right = right;
                queue.offer(right);
            }
            idx++;
        }

        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
