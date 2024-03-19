package View;

import javax.swing.*;
import java.sql.*;

public class AddGameWindow extends JFrame{
    private static final String URL = "jdbc:postgresql://localhost:5432/BoardgameManager";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    public AddGameWindow(){
        Connection connection = null;
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());

        try {
            // Carica il driver JDBC per PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Effettua la connessione al database PostgreSQL
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Se la connessione ha avuto successo
            if (connection != null) {
                System.out.println("Connessione al database stabilita!");
                // Crea uno statement per eseguire la query
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //QUERY
                ResultSet resultSet = statement.executeQuery("Select * from boardgame");
                int i=1;
                while(resultSet.next()){
                    resultSet.absolute(i);
                    JLabel title = new JLabel(resultSet.getString("name"));
                    title.setBounds(10,10*i,100,100);
                    JLabel minP = new JLabel(resultSet.getString("minplayers"));
                    minP.setBounds(100,10*i,100,100);
                    JLabel maxP = new JLabel(resultSet.getString("maxplayers"));
                    maxP.setBounds(130,10*i,100,100);
                    JLabel avgTime = new JLabel(resultSet.getString("avgduration"));
                    avgTime.setBounds(160,10*i,100,100);
                    this.add(title);
                    this.add(minP);
                    this.add(maxP);
                    this.add(avgTime);
                    i++;
                }

            } else {
                System.out.println("Connessione al database non riuscita!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC non trovato!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Errore durante la connessione al database!");
            e.printStackTrace();
        } finally {
            // Chiudi la connessione quando hai finito
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connessione al database chiusa!");
                }
            } catch (SQLException e) {
                System.out.println("Errore durante la chiusura della connessione!");
                e.printStackTrace();
            }
        }

        this.setSize(800,700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
