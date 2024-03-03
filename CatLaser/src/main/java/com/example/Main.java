package com.example;

import com.example.guice.BasicModule;
import com.example.view.CatLaserView;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Injector injector = Guice.createInjector(new BasicModule());
        CatLaserView view = injector.getInstance(CatLaserView.class);
        Scene scene = new Scene(view.getView(), Configuration.PREF_WIDTH, Configuration.PREF_HEIGHT);

        primaryStage.setTitle("Przykładowa Aplikacja JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

//        SerialPort[] ports = SerialPort.getCommPorts();
//        System.out.println("Dostępne porty:");
//        for (SerialPort port : ports) {
//            System.out.println(port.getSystemPortName());
//        }

        launch(args);
    }

}

