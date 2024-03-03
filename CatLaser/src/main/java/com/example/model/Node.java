package com.example.model;

import lombok.Data;

import java.util.UUID;

import static com.example.Configuration.PREF_HEIGHT;
import static com.example.Configuration.PREF_WIDTH;

@Data
public class Node implements Comparable<Node> {
    private UUID id;
    private double x;
    private double y;

    public Node(double x, double y) {
        this.id = UUID.randomUUID();
        this.x = x;
        this.y = y;
    }

    public int getXPos() {
        return (int) (180-x/PREF_WIDTH*180d);
    }

    public int getYPos() {
        return (int) (y/PREF_HEIGHT*180d);
    }

    @Override
    public int compareTo(Node o) {
        return o.getId().compareTo(this.getId());
    }
}
