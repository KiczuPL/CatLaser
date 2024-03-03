package com.example.model;


import java.util.LinkedList;
import java.util.List;

import static com.example.Configuration.PREF_HEIGHT;
import static com.example.Configuration.PREF_WIDTH;

public class CatLaserModel implements ICatLaserModel {
    private final List<Node> nodes = new LinkedList<>();

    public CatLaserModel() {
        nodes.add(new Node((double) PREF_WIDTH / 2, (double) PREF_HEIGHT / 2+30));
        nodes.add(new Node((double) PREF_WIDTH / 2+30, (double) PREF_HEIGHT / 2));
        nodes.add(new Node((double) PREF_WIDTH / 2-30, (double) PREF_HEIGHT / 2));
    }

    @Override
    public void moveNode(Node node, int x, int y) {
        node.setX(x);
        node.setY(y);
    }

    @Override
    public void insertNode(Node source, double x, double y) {
        nodes.add(nodes.indexOf(source), new Node(x, y));
    }

    @Override
    public List<Node> getNodes() {
        return nodes;
    }
}
