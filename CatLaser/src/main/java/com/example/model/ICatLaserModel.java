package com.example.model;

import java.util.List;

public interface ICatLaserModel {
    void moveNode(Node node, int x, int y);
    void insertNode(Node source, double x, double y);
    List<Node> getNodes();
}
