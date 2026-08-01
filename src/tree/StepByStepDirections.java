package tree;

public class StepByStepDirections {

    //https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/description/

    public String getDirections(TreeNode root, int startValue, int destValue)
    {
        // Find the path from root to start.
        // Find the path from root to destination.
        // Discard the longest common prefix (path to the LCA).
        // Replace the remaining start path with 'U's.
        // Append the remaining destination path ('L'/'R').

        String[] result = new String[1];

        //root to start path
        dfs(root, new StringBuilder(), result, startValue);
        String rootToStart = result[0];

        //root to dest path
        dfs(root, new StringBuilder(), result, destValue);
        String rootToDest = result[0];

        //discard common prefix
        int i = 0; //pointer on start path
        int j = 0; //pointer on dest path

        while(i < rootToStart.length() && j < rootToDest.length()){
            if(rootToStart.charAt(i) == rootToDest.charAt(j)){
                i++;
                j++;
            }else{
                break;
            }
        }

        rootToStart = rootToStart.substring(i);
        rootToDest = rootToDest.substring(j);

        StringBuilder sb = new StringBuilder();

        sb.append("U".repeat(rootToStart.length()));

        sb.append(rootToDest);

        return sb.toString();
    }

    private void dfs(TreeNode node, StringBuilder currPath, String[] result, int target)
    {
        if(node == null){
            return; //hit bottom, return
        }

        if(node.val == target){
            result[0] = currPath.toString();
            return; //target found, return
        }

        currPath.append('L');
        dfs(node.left, currPath, result, target);
        currPath.deleteCharAt(currPath.length() - 1); //backtrack

        currPath.append('R');
        dfs(node.right, currPath, result, target);
        currPath.deleteCharAt(currPath.length() - 1); //backtrack
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
