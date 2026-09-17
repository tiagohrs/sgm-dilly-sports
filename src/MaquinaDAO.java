import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaquinaDAO {
    
    public void inserir (Maquina m) {
        String sql = "Insert INTO maquina (nome, setor, criticidade, status) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexaoBD.conectar();
            PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, m.getNome());
                stmt.setString(2, m.getSetor());
                stmt.setString(3, m.getCriticidade());
                stmt.setString(4, m.getStatus());

                stmt.executeUpdate();
                System.out.println("Máquina cadastrada com sucesso!");

            } catch (SQLException e) {
                System.out.println("Erro ao inserir máquina: " + e.getMessage());
            }
    }

    public ArrayList<Maquina> listarTodas() {
        ArrayList<Maquina> lista = new ArrayList<>();
        String sql = "SELECT * FROM maquina";

        try (Connection con = ConexaoBD.conectar();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Maquina m = new Maquina(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("setor"),
                    rs.getString("criticidade"),
                    rs.getString("status")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar máquinas: " + e.getMessage());
        }

        return lista;

    }

    public void atualizar(Maquina m) {
        String sql = "UPDATE maquina SET nome = ?, setor = ?, criticidade = ?, status = ? WHERE id = ?";

        try (Connection con = ConexaoBD.conectar();
            PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, m.getNome());
                stmt.setString(2, m.getSetor());
                stmt.setString(3, m.getCriticidade());
                stmt.setString(4, m.getStatus());
                stmt.setInt(5, m.getId());
                
                int linhas = stmt.executeUpdate();
                if (linhas > 0) {
                    System.out.println("Máquina atualizada com sucesso!");
                } else {
                    System.out.println("Nenhuma máquina encontrada com esse ID.");
                }
        
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar máquina: " + e.getMessage());
        }
    
    }

    public void excluir(int id) {
        String sql = "DELETE FROM maquina WHERE id = ?";

        try (Connection con = ConexaoBD.conectar();
        PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Máquina excluida com sucesso!");
            } else {
                System.out.println("Nenhuma máquina encontrada com esse ID.");
            }

        }   catch (SQLException e) {
            System.out.println("Erro ao excluir máquina: " + e.getMessage());
        }
    }

    public boolean existe(int id) {
        String sql = "SELECT 1 FROM maquina WHERE id = ?";

        try (Connection con = ConexaoBD.conectar();
            PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Erro ao verificar máquina: " + e.getMessage());
            return false;
        }
    }

}
