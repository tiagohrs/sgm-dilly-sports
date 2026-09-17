import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

public class OrdemServicoDAO {

    public void inserir(OrdemServico os) {
        String sql = "INSERT INTO ordem_servico (id_maquina, descricao, tipo, prioridade, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexaoBD.conectar();
            PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setInt(1, os.getIdMaquina());
                stmt.setString(2, os.getDescricao());
                stmt.setString(3, os.getTipo());
                stmt.setString(4, os.getPrioridade());
                stmt.setString(5, os.getStatus());

                stmt.executeUpdate();
                System.out.println("Ordem de serviço cadastrada com sucesso!");

            } catch (SQLException e) {
                System.out.println("Erro ao inserir ordem de serviço: " + e.getMessage());
            }
    }
    
    public ArrayList<OrdemServico> listarTodas() {
        ArrayList<OrdemServico> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordem_servico";

        try (Connection con = ConexaoBD.conectar();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    OrdemServico os = new OrdemServico(
                        rs.getInt("id"),
                        rs.getInt("id_maquina"),
                        rs.getString("descricao"),
                        rs.getString("tipo"),
                        rs.getString("prioridade"),
                        rs.getString("status")
                    );

                    Timestamp conclusao = rs.getTimestamp("data_conclusao");
                    if (conclusao != null) {
                        os.setDataConclusao(conclusao.toLocalDateTime());
                    }

                    lista.add(os);
                }

        } catch (SQLException e) {
                System.out.println("Erro ao listar ordem de serviço: " + e.getMessage());
            }

            return lista;
    }

    public void atualizarStatus(int id, String novoStatus) {
        String sql = "UPDATE ordem_servico SET status = ? WHERE id = ?";

        try (Connection con = ConexaoBD.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, id);

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Status da ordem de serviço atualizado com sucesso!");
            } else {
                System.out.println("Nenhuma ordem de serviço encontrada com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM ordem_servico WHERE id = ?";

        try (Connection con = ConexaoBD.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Ordem de serviço excluída com sucesso!");
            } else {
                System.out.println("Nenhuma ordem de serviço encontrada com esse ID.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir ordem de serviço: " + e.getMessage());
        }
    }

    public void concluir(int id) {
        String sql = "UPDATE ordem_servico SET status = 'CONCLUIDA', data_conclusao = NOW() WHERE id = ?";
        
        try (Connection con = ConexaoBD.conectar();
            PreparedStatement stmt = con.prepareStatement(sql )) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Odem de servico concluída com sucesso!");
            } else {
                System.out.println("Nenhuma ordem de serviço encontrada com esse ID.");
            }


        } catch (SQLException e) {
            System.out.println("Erro ao concluir ordem de serviço: " + e.getMessage());
        }
    }

    public Double calcularMTTR(int idMaquina) {
        String sql = "SELECT AVG(TIMESTAMPDIFF(HOUR, data_abertura, data_conclusao)) AS mttr_horas " +
                     "FROM ordem_servico WHERE id_maquina = ? AND status = 'CONCLUIDA'";
        
        try (Connection con = ConexaoBD.conectar();
            PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setInt(1, idMaquina);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    double valor = rs.getDouble("mttr_horas");
                    if (rs.wasNull()) {
                        return null;
                    }
                    return valor;
                }

        } catch (SQLException e) {
            System.out.println("Erro ao calcular MTTR: " + e.getMessage());
        }       

        return null;
    }
    
    public Double calcularMTBF(int idMaquina) {
        String sql = "SELECT AVG(diferenca_horas) AS mtbf_horas " +
                     "FROM ( " +
                     "    SELECT TIMESTAMPDIFF(HOUR, " +
                     "        LAG(data_abertura) OVER (ORDER BY data_abertura), " +
                     "        data_abertura " +
                     "    ) AS diferenca_horas " +
                     "    FROM ordem_servico " +
                     "    WHERE id_maquina = ? " +
                     ") AS diferencas " +
                     "WHERE diferenca_horas IS NOT NULL";
        
        try (Connection con = ConexaoBD.conectar();
            PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idMaquina);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                double valor = rs.getDouble("mtbf_horas");
                if (rs.wasNull()) {
                    return null;
                }
                return valor;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao calcular MTBF: " + e.getMessage());
        }

        return null;
    }

}
