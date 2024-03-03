package com.example.presenter;

import com.example.model.Node;
import com.example.view.ICatLaserView;

public interface ICatLaserPresenter {
    void setView(ICatLaserView view);
    void onNodeSelected(Node node);
    void onNodeDragged(Node node, int x, int y);
    void onPanelClicked(int x, int y);

    void onNodeDoubleClicked(Node node);
    void onSpacePressed();
}
