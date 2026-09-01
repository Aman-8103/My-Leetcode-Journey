class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] litterId = new int[m][n];
        int startX = 0, startY = 0, totalLitter = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterId[i][j] = totalLitter++;
                }
            }
        }

        if (totalLitter == 0) return 0;

        // Queue stores state: {x, y, current_energy, collected_mask, moves}
        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << totalLitter];

        queue.offer(new int[]{startX, startY, energy, 0, 0});
        visited[startX][startY][energy][0] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], e = curr[2], mask = curr[3], moves = curr[4];

            if (mask == (1 << totalLitter) - 1) {
                return moves;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                char cell = classroom[nx].charAt(ny);
                if (cell == 'X') continue;

                int ne = e - 1;
                if (ne < 0) continue; // Cannot move if energy is exhausted

                int nmask = mask;
                if (cell == 'L') {
                    nmask |= (1 << litterId[nx][ny]);
                }

                int nrg = (cell == 'R') ? energy : ne;

                if (!visited[nx][ny][nrg][nmask]) {
                    visited[nx][ny][nrg][nmask] = true;
                    queue.offer(new int[]{nx, ny, nrg, nmask, moves + 1});
                }
            }
        }
        return -1;
    }
}
