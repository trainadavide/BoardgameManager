package Database;
import java.sql.*;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/BoardgameManager";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    public static ResultSet result(String query) {
        Connection connection = null;
        ResultSet resultSet = null;

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
                try {
                    resultSet = statement.executeQuery(query);
                }catch (SQLException e){}

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
        return resultSet;
    }
}