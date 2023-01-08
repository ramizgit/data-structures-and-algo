package tree;

import java.util.*;

public class PrintAllNodesAtDistanceKLeetcode {
    public static void main(String[] args) {

        Node node0 = new Node(0, null, null);
        Node node8 = new Node(8, null, null);
        Node node7 = new Node(7, null, null);
        Node node6 = new Node(6, null, null);
        Node node4 = new Node(4, null, null);
        Node node2 = new Node(2, node7, node4);
        Node node5 = new Node(5, node6, node2);
        Node node1 = new Node(1, node0, node8);
        Node node3 = new Node(3, node5, node1);

        System.out.println(printAllNodesAtDistanceK(node3, 5, 2));
    }

    public static List<Integer> printAllNodesAtDistanceK(Node root, int start, int k) {
        //get each node to parent pointer in a map, if not already provided
        Map<Integer, Node> nodeToParentMapping = nodeToParentMapping(root);

        //get node address
        Node startingNode = getNodeAddress(root, start);

        //bfs
        List<Integer> result = bfs(root, startingNode, nodeToParentMapping, k);

        return result;
    }

    public static List<Integer> bfs(Node root, Node startingNode, Map<Integer, Node> nodeToParentMapping, int k) {
        Queue<Node> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(startingNode);
        visited.add(startingNode.value);

        while (!queue.isEmpty() && k > 0) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node node = queue.poll();

                //add left child
                if (node.left != null && !visited.contains(node.left.value)) {
                    queue.add(node.left);
                    visited.add(node.left.value);
                }

                //add right child
                if (node.right != null && !visited.contains(node.right.value)) {
                    queue.add(node.right);
                    visited.add(node.right.value);
                }

                //add parent
                Node parent = nodeToParentMapping.get(node.value);
                if (parent != null && !visited.contains(parent.value)) {
                    queue.add(parent);
                    visited.add(parent.value);
                }
            }
            k--;
        }

        //iterate queue and create list
        for (Node n : queue) {
            result.add(n.value);
        }

        return result;
    }

    public static Node getNodeAddress(Node root, int node) {
        if (root == null) {
            return null;
        }

        if (root.value == node) {
            return root;
        }
        Node left = getNodeAddress(root.left, node);
        if (left != null) {
            return left;
        }
        return getNodeAddress(root.right, node);
    }

    public static Map<Integer, Node> nodeToParentMapping(Node root) {
        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            Node parent = queue.poll();
            if (parent.left != null) {
                map.put(parent.left.value, parent);
                queue.add(parent.left);
            }
            if (parent.right != null) {
                map.put(parent.right.value, parent);
                queue.add(parent.right);
            }
        }
        return map;
    }
}
