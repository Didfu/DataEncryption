package com.example.dataencryption;

import com.example.dataencryption.database.FileDao;
import com.example.dataencryption.models.CFile;
import com.example.dataencryption.utils.Encryption;
import com.example.dataencryption.utils.LastVisitedDirectory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AbhishekHomeController extends HomeController implements Initializable {





    void setuservalues(){

        System.out.println("before" );


        fileTable.setItems(FileDao.getFiles());


        System.out.println("after");

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
                CFile cFile = new CFile(filename, "encrypted", "abhishek");
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
            System.out.println("Cancelled en");
        }
    }
    protected void addExtensionFilters(ObservableList<FileChooser.ExtensionFilter> extensionFilters) {

    }
    public void decrpytClick1(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file");
        addExtensionFilters(fileChooser.getExtensionFilters());
        if (LastVisitedDirectory.getInstance().wasVisited()) {
            fileChooser.setInitialDirectory(new File(LastVisitedDirectory.getInstance().getPath()));
        }
        try {
            File file = fileChooser.showOpenDialog(primaryStage);
            String newPath = file.getAbsolutePath();
            String filename = file.getName();
            try {
                Encryption.decrypt(newPath, 8123);
                int id = FileDao.getFile(filename);
                FileDao.updateStatus(id, "decrypted");
                setTableValues();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            pathTextField.setText(newPath);
        } catch (NullPointerException e) {
            System.out.println("Canceled");
        }
    }
    void setTableValues(){
        System.out.println("before" );

        fileTable.setItems(FileDao.getAbhishekFiles());

        System.out.println("after");

    }
    void setTableColumnDefault(){
        System.out.println("before name" );
        nameColumn.setCellValueFactory(new PropertyValueFactory<File, String>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<File, String>("status"));
        System.out.println("here" );
        fileTable.setItems(FileDao.getAbhishekFiles());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("at user");
        setTableColumnDefault();
    }


}

