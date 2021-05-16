package algorithm.graph;

import java.util.HashSet;
import java.util.LinkedList;

public class GraphBFS {
    public void bfs(Point start) {
        LinkedList<Point> points = new LinkedList<Point>();
        HashSet<Point> pointHashSet = new HashSet<>();
        points.add(start);
        while (!points.isEmpty()) {
            Point remove = points.poll();
            System.out.println(remove);
            for (Point point : remove.next) {
                if (pointHashSet.contains(point)) {
                    continue;
                }
                points.add(point);
                pointHashSet.add(point);
            }
        }
    }
}
