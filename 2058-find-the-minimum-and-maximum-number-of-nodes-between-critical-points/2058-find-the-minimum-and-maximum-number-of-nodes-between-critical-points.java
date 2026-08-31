/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode nxt;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // Base case: a critical point requires at least 3 nodes
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1; // 0-indexed or 1-indexed works; using 1-indexed here

        while (curr.next != null) {
            ListNode nxt = curr.next;
            
            // Check if the current node is a local maxima or local minima
            boolean isLocalMaxima = curr.val > prev.val && curr.val > nxt.val;
            boolean isLocalMinima = curr.val < prev.val && curr.val < nxt.val;

            if (isLocalMaxima || isLocalMinima) {
                // If this is the very first critical point found
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    // Update the minimum distance between consecutive critical points
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                }
                // Track the most recent critical point index
                prevCriticalIndex = currentIndex;
            }

            // Move pointers forward
            prev = curr;
            curr = nxt;
            currentIndex++;
        }

        // If fewer than two critical points were found
        if (firstCriticalIndex == prevCriticalIndex) {
            return new int[]{-1, -1};
        }

        // Maximum distance is always between the last and the first critical point
        int maxDistance = prevCriticalIndex - firstCriticalIndex;

        return new int[]{minDistance, maxDistance};
    }
}
