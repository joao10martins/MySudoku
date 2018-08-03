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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class MySudoku_hardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //@FXML private GridPane gridpane;
    //@FXML private Button Finish;
    //@FXML private Button NewGame;
    @FXML private Label Error;
    
    @FXML private TextField txtField00;
    @FXML private TextField txtField01;
    @FXML private TextField txtField02;
    @FXML private TextField txtField03;
    @FXML private TextField txtField04;
    @FXML private TextField txtField05;
    @FXML private TextField txtField06;
    @FXML private TextField txtField07;
    @FXML private TextField txtField08;
    
    @FXML private TextField txtField10;
    @FXML private TextField txtField11;
    @FXML private TextField txtField12;
    @FXML private TextField txtField13;
    @FXML private TextField txtField14;
    @FXML private TextField txtField15;
    @FXML private TextField txtField16;
    @FXML private TextField txtField17;
    @FXML private TextField txtField18;
    
    @FXML private TextField txtField20;
    @FXML private TextField txtField21;
    @FXML private TextField txtField22;
    @FXML private TextField txtField23;
    @FXML private TextField txtField24;
    @FXML private TextField txtField25;
    @FXML private TextField txtField26;
    @FXML private TextField txtField27;
    @FXML private TextField txtField28;
    
    @FXML private TextField txtField30;
    @FXML private TextField txtField31;
    @FXML private TextField txtField32;
    @FXML private TextField txtField33;
    @FXML private TextField txtField34;
    @FXML private TextField txtField35;
    @FXML private TextField txtField36;
    @FXML private TextField txtField37;
    @FXML private TextField txtField38;

    @FXML private TextField txtField40;
    @FXML private TextField txtField41;
    @FXML private TextField txtField42;
    @FXML private TextField txtField43;
    @FXML private TextField txtField44;
    @FXML private TextField txtField45;
    @FXML private TextField txtField46;
    @FXML private TextField txtField47;
    @FXML private TextField txtField48;
    
    @FXML private TextField txtField50;
    @FXML private TextField txtField51;
    @FXML private TextField txtField52;
    @FXML private TextField txtField53;
    @FXML private TextField txtField54;
    @FXML private TextField txtField55;
    @FXML private TextField txtField56;
    @FXML private TextField txtField57;
    @FXML private TextField txtField58;
    
    @FXML private TextField txtField60;
    @FXML private TextField txtField61;
    @FXML private TextField txtField62;
    @FXML private TextField txtField63;
    @FXML private TextField txtField64;
    @FXML private TextField txtField65;
    @FXML private TextField txtField66;
    @FXML private TextField txtField67;
    @FXML private TextField txtField68;
    
    @FXML private TextField txtField70;
    @FXML private TextField txtField71;
    @FXML private TextField txtField72;
    @FXML private TextField txtField73;
    @FXML private TextField txtField74;
    @FXML private TextField txtField75;
    @FXML private TextField txtField76;
    @FXML private TextField txtField77;
    @FXML private TextField txtField78;
    
    @FXML private TextField txtField80;
    @FXML private TextField txtField81;
    @FXML private TextField txtField82;
    @FXML private TextField txtField83;
    @FXML private TextField txtField84;
    @FXML private TextField txtField85;
    @FXML private TextField txtField86;
    @FXML private TextField txtField87;
    @FXML private TextField txtField88;
    
    @FXML private TextField Current_time;
    @FXML private TextField Record;

    public int n = 0;
    public static int User;
    private int segundos;
    /*public TextField[][] board = {
            {txtField00, txtField01, txtField02, txtField03, txtField04, txtField05, txtField06, txtField07, txtField08},
            {txtField10, txtField11, txtField12, txtField13, txtField14, txtField15, txtField16, txtField17, txtField18},
            {txtField20, txtField21, txtField22, txtField23, txtField24, txtField25, txtField26, txtField27, txtField28},
            {txtField30, txtField31, txtField32, txtField33, txtField34, txtField35, txtField36, txtField37, txtField38},
            {txtField40, txtField41, txtField42, txtField43, txtField44, txtField45, txtField46, txtField47, txtField48},
            {txtField50, txtField51, txtField52, txtField53, txtField54, txtField55, txtField56, txtField57, txtField58},
            {txtField60, txtField61, txtField62, txtField63, txtField64, txtField65, txtField66, txtField67, txtField68},
            {txtField70, txtField71, txtField72, txtField73, txtField74, txtField75, txtField76, txtField77, txtField78},
            {txtField80, txtField81, txtField82, txtField83, txtField84, txtField85, txtField86, txtField87, txtField88},};*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginController es = new LoginController();
        User = SearchUser(es.getNome());
        int secs = SearchRecord(es.getNome(), User);
        
        
        //Função para tornar os segundos no formato hora:minuto:segundo
        //3600 segundos = 1 hora
        if (secs < 60) {
            Record.setText((secs / 3600)+"0:"+((secs / 60) % 60)+"0:"+(secs % 60));
        }
        else if (secs < 600) {
            Record.setText((secs / 3600)+"0:0"+((secs / 60) % 60)+":"+(secs % 60));
        }
        else if (secs < 3600) {
            Record.setText((secs / 3600)+"0:"+((secs / 60) % 60)+":"+(secs % 60));
        } else {
            Record.setText((secs / 3600)+"0:0"+((secs / 60) % 60)+":"+(secs % 60));
        }

        
        Random rand = new Random();
        n = rand.nextInt(5) + 1;
        createBoard(n);
           
        
        
    }

    public TextField[][] createBoard(int n) {
        int SIZE = 9;
        int length = SIZE, width = SIZE;
        

        TextField[][] board = {
            {txtField00, txtField01, txtField02, txtField03, txtField04, txtField05, txtField06, txtField07, txtField08},
            {txtField10, txtField11, txtField12, txtField13, txtField14, txtField15, txtField16, txtField17, txtField18},
            {txtField20, txtField21, txtField22, txtField23, txtField24, txtField25, txtField26, txtField27, txtField28},
            {txtField30, txtField31, txtField32, txtField33, txtField34, txtField35, txtField36, txtField37, txtField38},
            {txtField40, txtField41, txtField42, txtField43, txtField44, txtField45, txtField46, txtField47, txtField48},
            {txtField50, txtField51, txtField52, txtField53, txtField54, txtField55, txtField56, txtField57, txtField58},
            {txtField60, txtField61, txtField62, txtField63, txtField64, txtField65, txtField66, txtField67, txtField68},
            {txtField70, txtField71, txtField72, txtField73, txtField74, txtField75, txtField76, txtField77, txtField78},
            {txtField80, txtField81, txtField82, txtField83, txtField84, txtField85, txtField86, txtField87, txtField88},};

        startChrono();

        
        int[][] startBoard = hard_board(n);

        //Percorre a Grid
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                if ((y < 3 && x < 3) || (y > 5 && x < 3) || (y < 3 && x > 5) || (y > 2 && y < 6 && x > 2 && x < 6) || (y > 5 && x > 5)) { //mudar cor dos quadrantes
                    board[y][x].setStyle("-fx-background-color:  #E7D1AF");
                }
                if (startBoard[y][x] != 0) {   //Nos valores em que a board não está a 0: inicializa-os com os valores das tabelas predifinidas e o Disable não deixa escrever nessas posições. 
                    board[y][x].setText(valueOf(startBoard[y][x]));
                    board[y][x].setDisable(true);
                    board[y][x].setStyle("-fx-opacity: 1.0");
                    if ((y < 3 && x < 3) || (y > 5 && x < 3) || (y < 3 && x > 5) || (y > 2 && y < 6 && x > 2 && x < 6) || (y > 5 && x > 5)) { //mudar cor dos quadrantes
                        board[y][x].setStyle("-fx-background-color: #E7D1AF");
                    }
                } else {
                    board[y][x].setText("");
                }
            }
        }
        return board;
    }

    public BooleanProperty stop = new SimpleBooleanProperty(false);

    public int second = 0;
    public int minute = 0;
    public int hour = 0;

    void startChrono() {
        segundos = 0; //Segundos para a BD
        Task t = new Task() {

            
            //Função para contar o tempo(Cronómetro)
            @Override
            protected Object call() throws Exception {
                while (!stop.get()) {
                    second++;
                    segundos++;

                    if (second == 60) {
                        minute++;
                        second = 0;
                    }

                    if (minute == 60) {
                        hour++;
                        minute = 0;
                    }
                    String hr = hour <= 9 ? "0" + hour : hour + ""; //Se a hora é um número de um só dígito -> adiciona um 0 ao numer = e.g. 09 horas | senão fica com o valor do mesmo.
                    String min = minute <= 9 ? "0" + minute : minute + "";//Se os minutos são um número de um só dígito -> adiciona um 0 ao numero = e.g. 09 horas | senão fica com o valor do mesmo.
                    String sec = second <= 9 ? "0" + second : second + "";//Se os segundos são um número de um só dígito -> adiciona um 0 ao numero = e.g. 09 horas | senão fica com o valor do mesmo.

                    Platform.runLater(() -> {
                        Current_time.setText(hr + ":" + min + ":" + sec);
                    });
                    Thread.sleep(1000);

                }
                return null;
            }
        };
        new Thread(t).start();

    }

    private int[][] hard_board(int option) {
        switch (option) {
            case 1: {
                int[][] board = {
                    {0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 2, 8, 0, 0, 5, 0, 0},
                    {0, 9, 0, 3, 0, 4, 0, 0, 0},
                    {1, 6, 0, 0, 0, 0, 0, 3, 0},
                    {0, 0, 8, 0, 0, 0, 9, 0, 7},
                    {0, 0, 7, 2, 0, 9, 0, 5, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {9, 0, 0, 0, 0, 3, 0, 7, 0},
                    {0, 0, 0, 4, 0, 1, 0, 0, 2},};
                return board;
            }
            case 2: {
                int[][] board = {
                    {3, 5, 0, 0, 0, 0, 4, 0, 7},
                    {0, 0, 0, 5, 0, 0, 6, 0, 0},
                    {0, 0, 0, 0, 0, 9, 1, 0, 0},
                    {9, 0, 5, 7, 2, 0, 0, 0, 0},
                    {0, 0, 0, 0, 9, 0, 0, 8, 0},
                    {0, 0, 0, 0, 8, 0, 0, 0, 3},
                    {0, 0, 1, 0, 0, 4, 2, 0, 0},
                    {0, 2, 9, 0, 0, 0, 0, 0, 0},
                    {0, 8, 0, 0, 1, 0, 0, 5, 0},};
                return board;
            }
            case 3: {
                int[][] board = {
                    {8, 0, 9, 0, 0, 1, 0, 0, 0},
                    {7, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 8, 0, 0, 0, 2, 0},
                    {0, 7, 0, 0, 3, 0, 0, 8, 2},
                    {6, 0, 5, 4, 0, 0, 7, 0, 0},
                    {0, 0, 0, 5, 9, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 9, 0, 7, 6},
                    {0, 0, 1, 0, 7, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 1},};
                return board;
            }
            case 4: {
                int[][] board = {
                    {8, 5, 0, 0, 0, 2, 3, 0, 0},
                    {0, 0, 0, 0, 0, 3, 0, 0, 0},
                    {0, 7, 0, 0, 0, 0, 5, 0, 0},
                    {0, 0, 0, 6, 0, 0, 1, 0, 5},
                    {0, 6, 1, 4, 0, 0, 0, 0, 0},
                    {9, 0, 0, 0, 2, 3, 0, 7, 0},
                    {0, 0, 8, 0, 0, 0, 0, 0, 7},
                    {0, 0, 6, 0, 4, 0, 0, 9, 8},
                    {0, 0, 0, 0, 0, 9, 0, 0, 0},};
                return board;
            }
            default: {
                int[][] board = {
                    {4, 8, 7, 6, 0, 0, 0, 0, 0},
                    {0, 0, 0, 5, 0, 0, 4, 0, 9},
                    {0, 0, 0, 0, 0, 0, 6, 0, 0},
                    {0, 1, 0, 0, 0, 7, 3, 0, 0},
                    {5, 0, 0, 0, 4, 0, 0, 7, 0},
                    {0, 0, 0, 0, 0, 3, 0, 4, 0},
                    {7, 5, 8, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 6, 0, 0, 0, 0},
                    {0, 0, 0, 9, 1, 0, 0, 0, 8},};
                return board;
            }
        }
    }

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost/sudoku";
    
    
    @FXML
    private void NewGame(javafx.event.ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/mysudoku_hard.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("MySudoku - Hard");
        stage.show();
    }
    
    
    @FXML
    private void MainMenu(javafx.event.ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/main_menu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("MySudoku Menu");
        stage.show();
    }


    @FXML
    private void Pause(javafx.event.ActionEvent event) throws IOException {
        
        /*
        Boolean IsEmpty = false;
        Boolean IsNumber = true;
        Boolean IsOutOfBounds = false;
        
        for (int y=0; y<9; y++) {
            for (int x=0; x<9; x++) {
                if (board[y][x].getText().equals("")) { //Verificar se o campo esta vazio.
                    IsEmpty = true;
                }
                try {
                    Integer.parseInt(board[y][x].getText());
                    if ((Integer.parseInt(board[y][x].getText()) < 1) || (Integer.parseInt(board[y][x].getText()) > 9)) {  //Verificar se os numeros estao entre 0 e 9.
                        IsOutOfBounds = true;
                    }
                } catch (NumberFormatException e) {
                    IsNumber = false;
                }

            }
        }
     
        if (IsNumber == false) {
            Error.setText("Oops! Maybe it ain't a good idea to type anything other than numbers!");
        } else if (IsOutOfBounds == true) {
            Error.setText("Oh my days! The numbers you're trying to write aren't allowed in Sudoku! Try numbers between 1 and 9, including!");
        }
        */

        LoginController es = new LoginController();
        User = SearchUser(es.getNome());
        InsertUser(User, segundos, n);
        
        
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/pause_hard.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Paused");
        stage.show();
    }
    
    
    @FXML
    private void Finish(javafx.event.ActionEvent event) throws IOException {
        TextField[][] board = {
            {txtField00, txtField01, txtField02, txtField03, txtField04, txtField05, txtField06, txtField07, txtField08},
            {txtField10, txtField11, txtField12, txtField13, txtField14, txtField15, txtField16, txtField17, txtField18},
            {txtField20, txtField21, txtField22, txtField23, txtField24, txtField25, txtField26, txtField27, txtField28},
            {txtField30, txtField31, txtField32, txtField33, txtField34, txtField35, txtField36, txtField37, txtField38},
            {txtField40, txtField41, txtField42, txtField43, txtField44, txtField45, txtField46, txtField47, txtField48},
            {txtField50, txtField51, txtField52, txtField53, txtField54, txtField55, txtField56, txtField57, txtField58},
            {txtField60, txtField61, txtField62, txtField63, txtField64, txtField65, txtField66, txtField67, txtField68},
            {txtField70, txtField71, txtField72, txtField73, txtField74, txtField75, txtField76, txtField77, txtField78},
            {txtField80, txtField81, txtField82, txtField83, txtField84, txtField85, txtField86, txtField87, txtField88},};
    
  
        
        Boolean IsEmpty = false;
        Boolean IsNumber = true;
        Boolean IsOutOfBounds = false;
        ArrayList<Integer> matrix = new ArrayList<>();
        for (int y=0; y<9; y++) {
            for (int x=0; x<9; x++) {
                if (board[y][x].getText().equals("")) { //Verificar se o campo esta vazio.
                    IsEmpty = true;
                }
                try {
                    Integer.parseInt(board[y][x].getText());
                    if ((Integer.parseInt(board[y][x].getText()) < 1) || (Integer.parseInt(board[y][x].getText()) > 9)) {  //Verificar se os numeros estao entre 0 e 9.
                        IsOutOfBounds = true;
                    }
                } catch (NumberFormatException e) {
                    IsNumber = false;
                }

            }
        }
        if (IsEmpty == true) {
            Error.setText("You might want to finish your Puzzle! Some values are missing in action!!");
        } else if (IsNumber == false) {
            Error.setText("Oops! Maybe it ain't a good idea to type anything other than numbers!");
        } else if (IsOutOfBounds == true) {
            Error.setText("Oh my days! The numbers you're trying to write aren't allowed in Sudoku! Try numbers between 1 and 9, including!");
        } else if ((IsEmpty == false) && (IsNumber == true) && (IsOutOfBounds == false)) {
            Error.setText("");

            //Vê se os valores das linhas de Y estão repetidos    
            for (int y = 0; y < 9; y++) {
                matrix.add(Integer.parseInt(board[y][0].getText()));
                matrix.add(Integer.parseInt(board[y][1].getText()));
                matrix.add(Integer.parseInt(board[y][2].getText()));
                matrix.add(Integer.parseInt(board[y][3].getText()));
                matrix.add(Integer.parseInt(board[y][4].getText()));
                matrix.add(Integer.parseInt(board[y][5].getText()));
                matrix.add(Integer.parseInt(board[y][6].getText()));
                matrix.add(Integer.parseInt(board[y][7].getText()));
                matrix.add(Integer.parseInt(board[y][8].getText()));
                
                //Collections.frequency ajuda a verificar se numa linha existem números repetidos, o que não pode acontecer num jogo de sudoku.
                if ((Collections.frequency(matrix, 1) > 1) || (Collections.frequency(matrix, 2) > 1) || (Collections.frequency(matrix, 3) > 1) || (Collections.frequency(matrix, 4) > 1) || (Collections.frequency(matrix, 5) > 1) || (Collections.frequency(matrix, 6) > 1) || (Collections.frequency(matrix, 7) > 1) || (Collections.frequency(matrix, 8) > 1) || (Collections.frequency(matrix, 9) > 1)) {
                    Error.setText("You've got it wrong on line "+y+"!");
                }
                matrix.clear();
            }
            for (int x = 0; x < 9; x++) {
                matrix.add(Integer.parseInt(board[0][x].getText()));
                matrix.add(Integer.parseInt(board[1][x].getText()));
                matrix.add(Integer.parseInt(board[2][x].getText()));
                matrix.add(Integer.parseInt(board[3][x].getText()));
                matrix.add(Integer.parseInt(board[4][x].getText()));
                matrix.add(Integer.parseInt(board[5][x].getText()));
                matrix.add(Integer.parseInt(board[6][x].getText()));
                matrix.add(Integer.parseInt(board[7][x].getText()));
                matrix.add(Integer.parseInt(board[8][x].getText()));
                
                if ((Collections.frequency(matrix, 1) > 1) || (Collections.frequency(matrix, 2) > 1) || (Collections.frequency(matrix, 3) > 1) || (Collections.frequency(matrix, 4) > 1) || (Collections.frequency(matrix, 5) > 1) || (Collections.frequency(matrix, 6) > 1) || (Collections.frequency(matrix, 7) > 1) || (Collections.frequency(matrix, 8) > 1) || (Collections.frequency(matrix, 9) > 1)) {
                    Error.setText("You've got it wrong on column "+x+"!");
                }
                matrix.clear();
            }
            
            

            if (Error.getText().equals("")) {
                Error.setStyle("-fx-text-fill: green");
                Error.setText("W-W-Wait! You did it!! Very Well!");

                LoginController es = new LoginController();
                User = SearchUser(es.getNome());
                InsertUser(User, segundos, n);

                ((Node) (event.getSource())).getScene().getWindow().hide();

                Stage stage = new Stage();
                Parent parent = FXMLLoader.load(getClass().getResource("/mysudoku/hard_gratz.fxml"));
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("You've completed the Sudoku!");
                stage.show();

            }

        }

    }

    /*public TextField[][] getBoard(){
        return board;
    }*/
    
    public int getUser() {
        return User;
    }

    public int getTime() {
        return segundos;
    } 
    
    public int getCurrentBoard() {
        return n;
    }

    public static void InsertUser(int id_user, int time, int borderid) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String insert = " INSERT INTO hard (id_user,time,borderid)" + " VALUES (?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(insert);
            preparedStmt.setInt(1, id_user);
            preparedStmt.setInt(2, time);
            preparedStmt.setInt(3, borderid);
            preparedStmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Erro na conexão à BD: Verifique se a ligação com o servidor foi estabelecida.");
        }

    }

    public static int SearchRecord(String name, int id) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM hard";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            int record = Integer.MAX_VALUE;
            while (rs.next()) {

                int time = rs.getInt("time");
                int User = rs.getInt("id_user");

                if (User == id && record >= time) {
                    record = time;
                }

            }
            st.close();
            if (record != Integer.MAX_VALUE) {
                return record;
            }
        } catch (SQLException e) {
            System.err.println("Erro na conexão à BD: Verifique se a ligação com o servidor foi estabelecida.");
        }
        return 0;
    }

    public static int SearchUser(String name) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM utilizadores";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String bd_name = rs.getString("nome");

                if (bd_name.equals(name)) {
                    return id;
                }

            }
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro na conexão à BD: Verifique se a ligação com o servidor foi estabelecida.");
        }
        return 0;
    }

}
