package consistenthashing.geometry;

import java.util.*;

public class RectangleArea {

    //https://leetcode.com/problems/rectangle-area/description/

    //todo : practice

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2)
    {
        // Area of first rectangle.
        int area1 = (ax2 - ax1) * (ay2 - ay1);

        // Area of second rectangle.
        int area2 = (bx2 - bx1) * (by2 - by1);

        // Find overlap on the x-axis. Negative means no overlap, hence make it zero
        int overlapWidth = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));

        // Find overlap on the y-axis. Negative means no overlap, hence make it zero
        int overlapHeight = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));

        // Overlap area.
        int overlapArea = overlapWidth * overlapHeight;

        return area1 + area2 - overlapArea;
    }
}
