/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku;

/**
 *
 * @author Joao
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.MouseEvent;


public class MainMenuController implements Initializable {

    @FXML private Label Easy;
    @FXML private Label Intermediate;
    @FXML private Label Hard;
    @FXML private Label Error;
    //@FXML private final String logoutimg = new String("logout.png");

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginController lgn = new LoginController();
        String username = lgn.getNome();
        int user_id = lgn.getId();
    }
        

    @FXML private void Stats(javafx.event.ActionEvent event) throws IOException {
        Error.setText("We're terribly sorry but this feature isnt available yet.");
    }

    
    
    
    @FXML
    private void Logout(ActionEvent event) throws IOException{
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/login.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("MySudoku Login");
            stage.show();
    }
        


    @FXML
    private void Easy(javafx.event.ActionEvent event) throws IOException {

        ((Node) (event.getSource())).getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/mysudoku_easy.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("MySudoku - Easy");
        stage.show();
    }

    @FXML
    private void Intermediate(javafx.event.ActionEvent event) throws IOException {

        ((Node) (event.getSource())).getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/mysudoku_intermediate.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("MySudoku - Medium");
        stage.show();
    }

    @FXML
    private void Hard(javafx.event.ActionEvent event) throws IOException {

        ((Node) (event.getSource())).getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/mysudoku_hard.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("MySudoku - Hard");
        stage.show();
    }

    

    

    
}

