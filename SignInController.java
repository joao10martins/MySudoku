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
import java.sql.PreparedStatement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SignInController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML private Label Error;
    @FXML private TextField User;
    @FXML private PasswordField Pass_A;
    @FXML private PasswordField Pass_B;
    
    @FXML
    private void CreateAccount(javafx.event.ActionEvent event) throws IOException {

        if ((Pass_A.getText().equals("")) || (Pass_B.getText().equals("")) || (User.getText().equals(""))) {
            Error.setText("Error: You must fill every field!");
        } else if (!Pass_A.getText().equals(Pass_B.getText())) {
            Error.setText("Error: The Passwords don't match!");
        } else if (SearchUser(User.getText()) == 1) {
            Error.setText("Error: This username is already in use!");
        } else {
            InsertUser(User.getText(), Pass_A.getText());
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("MySudoku Login");
            stage.show();
        }
    }
    
    @FXML
     private void Cancel(javafx.event.ActionEvent event) throws IOException {
         
            ((Node)(event.getSource())).getScene().getWindow().hide();
           
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/login.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("MySudoku Login");
            stage.show();
     }
    
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost/sudoku";
    
    
    public static void InsertUser(String nome,String pass){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String insert = "INSERT INTO utilizadores (nome,pass)"+"VALUES (?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(insert);
            preparedStmt.setString (1, nome);
            preparedStmt.setString (2, pass);
            preparedStmt.execute();
            conn.close();
        }
        catch (SQLException e){
            System.err.println("Erro na conexão à BD: Verifique se a ligação com o servidor foi estabelecida.");
        }
            
    }
    
    public static int SearchUser(String nome){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM utilizadores";

            Statement st = conn.createStatement();
      
            ResultSet rs = st.executeQuery(query);
      
            while (rs.next())
            {
              String nome_bd = rs.getString("nome");
              if (nome_bd.equals(nome))
                  return 1;
            }
            st.close();
        }
        catch (SQLException e){
            System.err.println("Erro na conexão à BD: Verifique se a ligação com o servidor foi estabelecida.");
        }
        return 0; 
    }
    
}
