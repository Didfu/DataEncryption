package com.example.dataencryption;

import com.example.dataencryption.database.FileDao;
import com.example.dataencryption.models.CFile;
import com.example.dataencryption.utils.Encryption;
import com.example.dataencryption.utils.LastVisitedDirectory;
import com.example.dataencryption.utils.ScreenUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Label nameLabel;
    public BorderPane bp;
    public AnchorPane ap;
    public final static int GENERAL_SPACING = 30;
    public final static int GENERAL_PADDING = 30;
    public TableColumn<File, String> nameColumn;
    public TableColumn<File, String> statusColumn;
    public TableColumn<File, String> sharingColumn;


    @FXML
    protected TextField pathTextField;

    protected Stage primaryStage;


    public TableView<CFile> fileTable;
    public TableView<CFile> userTable;


    public void decrpytClick(ActionEvent event){
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
    public void openNewWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("enfile.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void closeWindow(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void encryptClick(ActionEvent event){
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
                System.out.println(newPath);
                try {
                    Encryption.encrypt(newPath, 8123);
                    CFile cFile = new CFile(filename, "encrypted","all");
                    FileDao.insertFile(cFile);
                    setTableValues();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pathTextField.setText(newPath);
            } catch (NullPointerException e) {
                System.out.println("Cancelled en");
            }
    }
    protected void addExtensionFilters(ObservableList<FileChooser.ExtensionFilter> extensionFilters) {

        }


    public void logoutClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Logout");
        Optional <ButtonType> res =  alert.showAndWait();
        ButtonType button = res.orElse(ButtonType.CANCEL);
        if(button == ButtonType.OK){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), ScreenUtils.width, ScreenUtils.height);
            Stage root = (Stage)((Node) event.getSource()).getScene().getWindow();
            root.setScene(scene);
        }else{
            alert.close();
        }

    }


    void setTableValues(){
        fileTable.setItems(FileDao.getFiles());
    }

    void setTableColumnDefault(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<File, String>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<File, String>("status"));
        sharingColumn.setCellValueFactory(new PropertyValueFactory<File, String>("sharing"));
        System.out.println("here" );
        setTableValues();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("at init");
        setTableColumnDefault();

    }

}
