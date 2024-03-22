package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

public class AddGameWindow extends JFrame{
    private static final String URL = "jdbc:postgresql://localhost:5432/BoardgameManager";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private final boolean collection;

    public AddGameWindow(boolean collection){

        this.collection = collection;

        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(0,0,400,600);

        final Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Carica il driver JDBC per PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Se la connessione ha avuto successo
            if (connection != null) {
                System.out.println("Connessione al database stabilita!");
                // Crea uno statement per eseguire la query
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //QUERY

                ResultSet resultSet = statement.executeQuery("Select * from boardgame");
                int i=1;
                JPanel game = null;
                while(resultSet.next()){
                    game = new JPanel();
                    game.setBounds(0,0,800,200);
                    resultSet.absolute(i);
                    JLabel title = new JLabel(resultSet.getString("name"));
                    title.setBounds(10,0,100,100);
                    JLabel minP = new JLabel("Giocatori: "+resultSet.getString("minplayers")+" - "+resultSet.getString("maxplayers"));
                    minP.setBounds(10,10,100,100);
                    JLabel avgTime = new JLabel("Durata: "+resultSet.getString("avgduration"));
                    avgTime.setBounds(100,10,100,100);

                    URL imageURL = null;
                    try {
                        imageURL = new URL(resultSet.getString("image"));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    ImageIcon img = new ImageIcon(imageURL);
                    JLabel label = new JLabel(img);
                    label.setBounds(0,0,100,100);
                    game.add(label);

                    JButton add = new JButton("+");
                    add.setBounds(600,0,50,50);

                    game.add(title);
                    game.add(minP);
                    game.add(avgTime);
                    panel.add(game);
                    panel.add(add);
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
        scrollPane.setViewportView(panel);
        scrollPane.setBounds(0,0,780,680);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setSize(800,700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
