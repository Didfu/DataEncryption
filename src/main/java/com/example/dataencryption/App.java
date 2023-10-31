package com.example.dataencryption;

import com.example.dataencryption.models.User;
import com.example.dataencryption.utils.ScreenUtils;
import com.example.dataencryption.utils.StringUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    static User user = null;
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene;
        stage.setResizable(false);
        stage.setTitle(StringUtils.title);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("Images/logo.png"))));
        if(user == null){
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
            scene = new Scene(fxmlLoader.load(), ScreenUtils.width, ScreenUtils.height);
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home.fxml"));
            scene = new Scene(fxmlLoader.load(), ScreenUtils.width, ScreenUtils.height);
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
