public class Maquina {

    private int id;
    private String nome;
    private String setor;
    private String criticidade;
    private String status;

    public Maquina(int id, String nome, String setor, String criticidade, String status) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
        this.criticidade = criticidade;
        this.status = status;
    }

    public Maquina(String nome, String setor, String criticidade, String status) {
        this.nome = nome;
        this.setor = setor;
        this.criticidade = criticidade;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSetor() {
        return setor;
    }

    public String getCriticidade() {
        return criticidade;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Nome: " + nome +
               " | Setor: " + setor +
               " | Criticidade: " + criticidade +
               " | Status: " + status;
    }
}