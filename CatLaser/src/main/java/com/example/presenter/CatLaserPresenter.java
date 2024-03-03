package com.example.presenter;

import com.example.model.ICatLaserModel;
import com.example.model.IPointerDeviceConnector;
import com.example.model.Node;
import com.example.view.ICatLaserView;
import com.google.inject.Inject;

public class CatLaserPresenter implements ICatLaserPresenter {
    private Node selectedNode;
    private final ICatLaserModel model;
    private ICatLaserView view;
    private IPointerDeviceConnector pointerDeviceConnector;

    @Inject
    public CatLaserPresenter(ICatLaserModel model, IPointerDeviceConnector pointerDeviceConnector) {
        this.pointerDeviceConnector = pointerDeviceConnector;
        this.model = model;
    }

    @Inject
    public void setView(ICatLaserView view) {
        this.view = view;
        view.setPresenter(this);
        view.resetNodes(model.getNodes());
    }

    public void onNodeSelected(Node node) {
        if (selectedNode != null)
            view.toggleNodeColor(selectedNode);
        selectedNode = node;
        view.toggleNodeColor(node);
        pointerDeviceConnector.movePointer(node.getXPos(), node.getYPos());
    }

    @Override
    public void onNodeDragged(Node node, int x, int y) {
        if (selectedNode == node) {
            model.moveNode(node, x, y);
            pointerDeviceConnector.movePointer((int) node.getXPos(), (int) node.getYPos());
            view.repositionNodes();
        } else {
            onNodeSelected(node);
        }
    }

    @Override
    public void onPanelClicked(int x, int y) {

    }

    @Override
    public void onNodeDoubleClicked(Node node) {
        model.insertNode(node, node.getX() + 10, node.getY() + 10);
        selectedNode = null;
        view.resetNodes(model.getNodes());
    }

    @Override
    public void onSpacePressed() {
        Node nextNode = model.getNodes().get((model.getNodes().indexOf(selectedNode) + 1) % model.getNodes().size());
        onNodeSelected(nextNode);
    }


}
