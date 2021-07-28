package DataAccessLayer;
import java.sql.*;
import org.sqlite.JDBC;
public class CreateDataBase {
    private final String path;
    private final String connectionString;

    public CreateDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        path = String.format("jdbc:sqlite:%s\\%s", System.getProperty("user.dir"), "kanban.db");
        this.connectionString = path;
        CreateAllTables();
    }

    private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /// <summary>
    ///  create the user table
    /// </summary>
    /// <returns></returns>
    public String CreateUsersTable() {
        String usersTableCommand = "CREATE TABLE IF NOT EXISTS Users(ID    INTEGER NOT NULL UNIQUE," +
                "Nickname TEXT NOT NULL ," +
                "Sequence INTEGER NOT NULL," +
                "PRIMARY KEY(ID))";
        return usersTableCommand;
    }
    /// <summary>
    /// create the board table
    /// </summary>
    /// <returns></returns>

    /// <summary>
    /// create all the tables that we mention above
    /// </summary>
    public void CreateAllTables() {
        try (Connection connection = connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(CreateUsersTable());) {
        } catch (SQLException ex) {
        }
    }
}