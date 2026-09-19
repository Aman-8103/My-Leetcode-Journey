class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest point on the rectangle using built-in Math methods
        int closestX = Math.max(x1, Math.min(x2, xCenter));
        int closestY = Math.max(y1, Math.min(y2, yCenter));
        
        // Calculate the differences
        int dx = xCenter - closestX;
        int dy = yCenter - closestY;
        
        // Return whether the squared distance is within the squared radius range
        return (dx * dx) + (dy * dy) <= (radius * radius);
    }
}
