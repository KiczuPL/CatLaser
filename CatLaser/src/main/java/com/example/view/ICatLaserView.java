package com.example.view;

import com.example.model.Node;
import com.example.presenter.ICatLaserPresenter;

public interface ICatLaserView {
    void setPresenter(ICatLaserPresenter presenter);
    void toggleNodeColor(Node node);

    void addNode(Node node);
    void resetNodes(Iterable<Node> nodes);

    void repositionNodes();
}
