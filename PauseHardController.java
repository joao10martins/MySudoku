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


public class PauseHardController implements Initializable {
    
    
    @FXML private Label timer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MySudoku_hardController es = new MySudoku_hardController();
        int User = es.getUser();

        LoginController as = new LoginController();
        String name = as.getNome();

        int secs = SearchCurrentTime(name, User);
        

        if (secs < 60) {
            timer.setText((secs / 3600) + "0:" + ((secs / 60) % 60) + "0:" + (secs % 60));
        } else if (secs < 600) {
            timer.setText((secs / 3600) + "0:0" + ((secs / 60) % 60) + ":" + (secs % 60));
        } else if (secs < 3600) {
            timer.setText((secs / 3600) + "0:" + ((secs / 60) % 60) + ":" + (secs % 60));
        } else {
            timer.setText((secs / 3600) + ":" + ((secs / 60) % 60) + ":" + (secs % 60));
        }
    }
    
    
    @FXML
    private void NewGame(javafx.event.ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/mysudoku_hard.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("MySudoku - Hard");
        stage.show();
    }
    
    
    @FXML
    private void Resume(javafx.event.ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/mysudoku_hard.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("MySudoku - Hard");
        stage.show();
        
        
    }
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost/sudoku";
    
    public static int SearchCurrentTime(String name, int User) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT time FROM hard WHERE id_user = " + String.valueOf(User) + " ORDER BY id DESC";

            ResultSet rs;
            try (
                Statement st = conn.createStatement()) {
                rs = st.executeQuery(query);
                while (rs.next()) {

                    int bd_time = rs.getInt("time");
                    return bd_time;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro na conexão à BD: Verifique se a ligação com o servidor foi estabelecida.");
        }
        return 0;
    }
}

