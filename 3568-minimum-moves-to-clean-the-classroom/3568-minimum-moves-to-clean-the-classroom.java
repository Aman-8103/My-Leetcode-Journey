import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] litterId = new int[m][n];
        int startX = 0, startY = 0, totalLitter = 0;

        // Step 1: Pre-process positions
        for (int i = 0; i < m; i++) {
            String row = classroom[i];
            for (int j = 0; j < n; j++) {
                char cell = row.charAt(j);
                if (cell == 'S') {
                    startX = i;
                    startY = j;
                } else if (cell == 'L') {
                    litterId[i][j] = totalLitter;
                    totalLitter++;
                }
            }
        }

        if (totalLitter == 0) {
            return 0;
        }

        int totalMasks = 1 << totalLitter;
        int totalCells = m * n;

        // Step 2: 2D primitive array tracking max energy seen for each (cell_index, mask)
        // This replaces the heavy 4D boolean array and reduces size to (400 * 1024) entries.
        int[][] maxEnergyAtState = new int[totalCells][totalMasks];
        for (int i = 0; i < totalCells; i++) {
            Arrays.fill(maxEnergyAtState[i], -1);
        }

        // Use a flat primitive 1D array in the queue to minimize object allocations
        // Queue stores: [flattened_coordinates (r * n + c), remaining_energy, bitmask]
        Queue<int[]> q = new LinkedList<>();
        
        int startCell = startX * n + startY;
        q.add(new int[] {startCell, energy, 0});
        maxEnergyAtState[startCell][0] = energy;

        int[] dirs = {-1, 0, 1, 0, -1};
        int totalMoves = 0;

        // Step 3: Fast BFS loop
        while (!q.isEmpty()) {
            int layerSize = q.size();
            for (int k = 0; k < layerSize; k++) {
                int[] curr = q.poll();
                int cellIdx = curr[0];
                int e = curr[1];
                int mask = curr[2];

                if (mask == totalMasks - 1) {
                    return totalMoves;
                }

                // If current energy is worse than what we recorded for this state, skip it
                if (e < maxEnergyAtState[cellIdx][mask]) {
                    continue;
                }
                if (e == 0) {
                    continue;
                }

                int r = cellIdx / n;
                int c = cellIdx % n;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dirs[i];
                    int nc = c + dirs[i + 1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                        char nextCell = classroom[nr].charAt(nc);
                        if (nextCell == 'X') continue;

                        int nextEnergy = e - 1;
                        int nextMask = mask;

                        if (nextCell == 'R') {
                            nextEnergy = energy;
                        } else if (nextCell == 'L') {
                            nextMask |= (1 << litterId[nr][nc]);
                        }

                        int nextCellIdx = nr * n + nc;

                        // Pruning Rule: Only queue the state if we found a path arriving with strictly MORE energy
                        if (nextEnergy > maxEnergyAtState[nextCellIdx][nextMask]) {
                            maxEnergyAtState[nextCellIdx][nextMask] = nextEnergy;
                            q.add(new int[] {nextCellIdx, nextEnergy, nextMask});
                        }
                    }
                }
            }
            totalMoves++;
        }

        return -1;
    }
}
