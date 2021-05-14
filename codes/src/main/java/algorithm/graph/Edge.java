package algorithm.graph;

/**
 * created 5/14/2021 10:57 AM
 *
 * @author luowen <loovien@163.com>
 */
public class Edge {
    public int weight;

    public Point from;

    public Point to;

    public Edge(int weight, Point from, Point to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
