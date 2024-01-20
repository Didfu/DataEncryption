package com.example.dataencryption;
import com.example.dataencryption.database.FileDao;
import com.example.dataencryption.models.CFile;
import com.example.dataencryption.utils.Encryption;
import com.example.dataencryption.utils.LastVisitedDirectory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnfileController extends HomeController implements Initializable
{
    @FXML
    public void submituser(){
        System.out.println(sharing.getText());
        sharedtext();
        System.out.println(sharedtext);
    }
    public void encryptClick1(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file");
        addExtensionFilters(fileChooser.getExtensionFilters());
        if (LastVisitedDirectory.getInstance().wasVisited()) {
            fileChooser.setInitialDirectory(new File(LastVisitedDirectory.getInstance().getPath()));
        }
        try {
            File file = fileChooser.showOpenDialog(primaryStage);
            System.out.println("Cancelled filechooser");
            String newPath = file.getAbsolutePath();
            System.out.println("Cancelled abspath");
            String filename = file.getName();
            System.out.println("Cancelled filename");
            System.out.println(newPath);
            try {System.out.println("Cancelled before enc");

                Encryption.encrypt(newPath, 8123);
                System.out.println("Cancelled on enc");
                CFile cFile = new CFile(filename, "encrypted", sharedtext);
                System.out.println("Cancelled on cfile");
                FileDao.insertFile(cFile);
                System.out.println("Cancelled on insert");
                setTableValues();
            } catch (IOException e) {
                System.out.println("Cancelled en path");
                throw new RuntimeException(e);
            }
            pathTextField.setText(newPath);
            System.out.println("Cancelled aft path");

        } catch (NullPointerException e) {
            System.out.println("Cancelled"+e.getMessage());
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void sharedtext(){
        sharedtext = sharing.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("NewWindowController initialized");

    }
}
