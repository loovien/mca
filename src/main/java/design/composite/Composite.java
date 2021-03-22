package design.composite;

import java.util.ArrayList;
import java.util.List;

abstract class Node {
    public abstract void draw();
}

class LeafNode extends Node {

    String name;

    public LeafNode(String name) {
        this.name = name;
    }

    @Override
    public void draw() {
        System.out.println(name);
    }
}

class BranchNode extends Node {

    String name;

    List<Node> leafs = new ArrayList<>();

    public BranchNode(String name) {
        this.name = name;
    }

    @Override
    public void draw() {
        System.out.println(name);
    }

    public void add(Node node) {
        leafs.add(node);
    }
}


public class Composite {

    public static void tree(Node node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("==");
        }
        node.draw();

        if (node instanceof BranchNode) {
            BranchNode branchNodes = (BranchNode) node;
            depth++;
            for (Node node1 : branchNodes.leafs) {
                tree(node1, depth);
            }
        }
    }


    public static void main(String[] args) {
        BranchNode root = new BranchNode("root");
        BranchNode sectionA = new BranchNode("sectionA");
        sectionA.add(new LeafNode("sectionA-1"));
        sectionA.add(new LeafNode("sectionA-2"));
        root.add(sectionA);

        BranchNode sectionB = new BranchNode("sectionB");
        sectionB.add(new LeafNode("sectionB-1"));
        sectionB.add(new LeafNode("sectionB-2"));
        root.add(sectionB);
        tree(root, 0);
    }
}
