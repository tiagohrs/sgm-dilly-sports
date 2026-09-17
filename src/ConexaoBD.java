import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    
    private static final String URL = "jdbc:mysql://localhost:3306/sgm_dilly_sports";
    private static final String USUARIO = "root";
    private static final String SENHA = "PIRULITO123";
    
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO,SENHA );
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco: " + e.getMessage());
            return null;
        }
    }

    

}
