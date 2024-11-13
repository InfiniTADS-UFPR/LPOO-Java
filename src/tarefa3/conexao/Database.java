package tarefa3.conexao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private static Properties properties;

    private Database() {
    }

    public static Connection getConnection() throws SQLException, IOException {
        readProperties();
        return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.password"));
    }

    public static void readProperties() throws IOException {
        if (properties == null) {
            try (FileInputStream file = new FileInputStream("./src/tarefa03.properties")) {
                Properties props = new Properties();
                props.load(file);
                properties = props;
            }
        }
    }
}
