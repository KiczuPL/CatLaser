package com.example.guice;


import com.example.model.CatLaserModel;
import com.example.model.ICatLaserModel;
import com.example.model.IPointerDeviceConnector;
import com.example.model.PointerDeviceConnector;
import com.example.presenter.CatLaserPresenter;
import com.example.presenter.ICatLaserPresenter;
import com.example.view.CatLaserView;
import com.example.view.ICatLaserView;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ICatLaserView.class).to(CatLaserView.class).in(Singleton.class);
        bind(ICatLaserPresenter.class).to(CatLaserPresenter.class).in(Singleton.class);
        bind(ICatLaserModel.class).to(CatLaserModel.class).in(Singleton.class);
        bind(IPointerDeviceConnector.class).to(PointerDeviceConnector.class).in(Singleton.class);
    }

}
