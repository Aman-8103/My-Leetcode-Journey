/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Global variable to track the number of matching nodes
    private int matchingNodesCount = 0;

    public int averageOfSubtree(TreeNode root) {
        // Reset the counter for each fresh run
        matchingNodesCount = 0;
        calculateSumAndCount(root);
        return matchingNodesCount;
    }

    private int[] calculateSumAndCount(TreeNode root) {
        // Base Case: An empty node contributes 0 to the sum and 0 to the node count
        if (root == null) {
            return new int[]{0, 0}; // {sum, count}
        }

        // Post-Order Traversal: Process left and right subtrees first
        int[] leftSubtree = calculateSumAndCount(root.left);
        int[] rightSubtree = calculateSumAndCount(root.right);

        // Combine findings from the children and add the current node
        int totalSum = leftSubtree[0] + rightSubtree[0] + root.val;
        int totalCount = leftSubtree[1] + rightSubtree[1] + 1;

        // Integer division automatically handles rounding down to the nearest integer
        int currentAverage = totalSum / totalCount;

        // If the average equals the node's own value, increment our answer
        if (root.val == currentAverage) {
            matchingNodesCount++;
        }

        // Return the combined metrics back up to the parent node
        return new int[]{totalSum, totalCount};
    }
}
