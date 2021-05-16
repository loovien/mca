package algorithm.graph;

import java.util.HashSet;
import java.util.Stack;

public class GraphDFS {
    public void dfs(Point point) {
        Stack<Point> points = new Stack<>();
        HashSet<Point> pointHashSet = new HashSet<>();
        points.add(point);
        pointHashSet.add(point);
        System.out.println(point.value);
        while (!points.isEmpty()) {
            Point pop = points.pop();
            for (Point point1 : point.next) {
                if (pointHashSet.contains(point1)) {
                    continue;
                }
                pointHashSet.add(point1);
                System.out.println(point1.value);
                points.add(pop);
                points.add(point1);
                break;
            }
        }
    }
}
