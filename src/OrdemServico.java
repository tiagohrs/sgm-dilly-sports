import java.time.LocalDateTime;

public class OrdemServico {
    private int id;
    private int idMaquina;
    private String descricao;
    private String tipo;
    private String prioridade;
    private String status;
    private LocalDateTime dataConclusao;

        public OrdemServico(int id, int idMaquina, String descricao, String tipo, String prioridade, String status) {
        this.id = id;
        this.idMaquina = idMaquina;
        this.descricao = descricao;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.status = status;
    }

    public OrdemServico(int idMaquina, String descricao, String tipo, String prioridade, String status) {
        this.idMaquina = idMaquina;
        this.descricao = descricao;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    @Override 
    public String toString() {
        return "OS " + id +
               " | Máquina ID: " + idMaquina +
               " | Descrição: " + descricao +
               " | Tipo: " + tipo +
               " | Prioridade: " + prioridade +
               " | Status: " + status +
               " | Conclusão: " + (dataConclusao != null ? dataConclusao : "Em aberto");
    }

}
