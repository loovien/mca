package algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * created 5/14/2021 10:55 AM
 *
 * @author luowen <loovien@163.com>
 */
public class Point {

    public int value;

    public int in;

    public int out;

    public List<Point> next = new ArrayList<>();

    public List<Edge> edges = new ArrayList<>();

    public Point(int value, int in, int out) {
        this.value = value;
        this.in = in;
        this.out = out;
    }
}
