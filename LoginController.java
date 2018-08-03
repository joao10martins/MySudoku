/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysudoku;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;




public class LoginController implements Initializable {
    
    
    
    @FXML private PasswordField Password;
    @FXML private TextField Username;
    @FXML private Label Message;
    @FXML private Button Login;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
     private void SignIn(javafx.event.ActionEvent event) throws IOException {
         
           ((Node)(event.getSource())).getScene().getWindow().hide();
           
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/signin.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Sign In");
            stage.show();
     }
     
    public static String nome;
    public static int id;
    
    @FXML
    private void Login(javafx.event.ActionEvent event) throws IOException {
       
        nome = Username.getText();
        if(SearchUser(Username.getText(),Password.getText())==1){
            Message.setText("Welcome to MySudoku: " + Username.getText());
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/main_menu.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("MySudoku Menu");
            stage.show();

        }else{
            Message.setText("The Username and/or Password do not match, or the user doesn't exist."); 
        }
    }
    
    public String getNome(){
        return nome;
    }
    public int getId(){
        return id;
    }
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost/sudoku";
    
    
    
    
    //Função para procurar um Utilizador na Base de Dados
    public static int SearchUser(String nome,String pass){
    Connection conn = null;
        try{
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM utilizadores";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                id = rs.getInt("id");
                String nome_bd = rs.getString("nome");
                String pass_bd = rs.getString("pass");

                if (nome_bd.equals(nome) && pass_bd.equals(pass))
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
