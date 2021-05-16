package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Topological {
    public static void main(String[] args) {

    }

    public static List<Point> topo(Graph graph) {
        HashMap<Point, Integer> pointIntegerHashMap = new HashMap<>();
        LinkedList<Point> points = new LinkedList<>();

        for (Point value : graph.points.values()) {
            pointIntegerHashMap.put(value, value.in);
            if (value.in == 0) {
                points.add(value);
            }
        }
        ArrayList<Point> result = new ArrayList<>();
        while (!points.isEmpty()) {
            Point current = points.poll();
            result.add(current);
            for (Point point : current.next) {
                Integer integer = pointIntegerHashMap.get(point);
                pointIntegerHashMap.put(point, --integer);
                if (pointIntegerHashMap.get(point) == 0) {
                    points.add(point);
                }
            }
        }
        return result;
    }


    public Graph build() {

        return null;
    }
}
