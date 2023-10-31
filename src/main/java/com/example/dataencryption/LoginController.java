package com.example.dataencryption;

import com.example.dataencryption.database.UserDao;
import com.example.dataencryption.utils.ScreenUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usrField;
    @FXML
    private PasswordField passField;
    @FXML
    private Label errorLabel;
    @FXML
    private void onKeyPressed(KeyEvent event){
        errorLabel.setText("");
        if(event.getCode() == KeyCode.ENTER){
            System.out.println("Enter key pressed");
            if(event.getSource() == usrField){
                passField.requestFocus();
            }else if(event.getSource() == passField){
                login();
            }else{
                login();
            }
        }else if(event.getCode() == KeyCode.TAB && event.getSource() == usrField){
            passField.requestFocus();
        }
    }

    @FXML
    private void onMouseClick(){
        login();
    }

    private void login() {
        if(usrField.getText().isEmpty()){
            errorLabel.setText("Enter username!");
        }else if(passField.getText().isEmpty()){
            errorLabel.setText("Enter password!");
        }else if(usrField.getText().equals("admin") && passField.getText().equals("root")){
            // Login as admin
            System.out.println("Logged in as admin");
            errorLabel.setTextFill(Color.WHITE);

            try {
                usrField.getScene().getWindow().hide();
                goToHome((Stage) usrField.getScene().getWindow());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // Login as regular user
            App.user =  UserDao.loginUser(usrField.getText(), passField.getText());
            if(App.user != null){
                System.out.println(App.user.getUsername());
                errorLabel.setTextFill(Color.WHITE);

                try {
                    usrField.getScene().getWindow().hide();
                    goToUser((Stage) usrField.getScene().getWindow());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                errorLabel.setText("Invalid Credentials!");
            }
        }
    }

    private void goToHome(Stage root) throws IOException {
        Scene scene = null;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home.fxml"));
        scene = new Scene(fxmlLoader.load(), ScreenUtils.width, ScreenUtils.height);
        root.setScene(scene);
        root.show();
    }
    private void goToUser(Stage root) throws IOException {
        Scene scene = null;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("userhome.fxml"));
        scene = new Scene(fxmlLoader.load(), ScreenUtils.width, ScreenUtils.height);
        root.setScene(scene);
        root.show();
    }
}
