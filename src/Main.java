import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        MaquinaDAO dao = new MaquinaDAO();
        OrdemServicoDAO osDao = new OrdemServicoDAO();
        int opcaoPrincipal;

        do {
            System.out.println("\n===== SGM - DILLY SPORTS =====");
            System.out.println("1 - Máquinas");
            System.out.println("2 - Ordens de Serviço");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoPrincipal = Integer.parseInt(leia.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite apenas o número.");
                opcaoPrincipal = -1;
            }

            switch (opcaoPrincipal) {

                case 1:
                    menuMaquinas(leia, dao, osDao);
                    break;

                case 2:
                    menuOrdensServico(leia, osDao);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcaoPrincipal != 0);

        leia.close();
    }

    public static void menuMaquinas(Scanner leia, MaquinaDAO dao, OrdemServicoDAO osDao) {
        int opcao;

        do {
            System.out.println("\n----- MÁQUINAS -----");
            System.out.println("1 - Cadastrar máquina");
            System.out.println("2 - Listar máquinas");
            System.out.println("3 - Atualizar máquina");
            System.out.println("4 - Excluir máquina");
            System.out.println("5 - Ver MTTR da máquina");
            System.out.println("6 - Ver MTBF da máquina");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(leia.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite apenas o número.");
                opcao = -1;
            }

            switch (opcao) {

                case 1:
                    System.out.print("Nome da máquina: ");
                    String nome = leia.nextLine();

                    System.out.print("Setor: ");
                    String setor = leia.nextLine();

                    System.out.print("Criticidade (BAIXA, MEDIA, ALTA): ");
                    String criticidade = leia.nextLine().toUpperCase();

                    System.out.print("Status (ATIVA, EM_MANUTENCAO, INATIVA): ");
                    String status = leia.nextLine().toUpperCase();

                    Maquina novaMaquina = new Maquina(nome, setor, criticidade, status);
                    dao.inserir(novaMaquina);
                    break;

                case 2:
                    ArrayList<Maquina> maquinas = dao.listarTodas();
                    if (maquinas.isEmpty()) {
                        System.out.println("Nenhuma máquina cadastrada ainda.");
                    } else {
                        System.out.println("\n--- Máquinas cadastradas ---");
                        for (Maquina m : maquinas) {
                            System.out.println(m);
                        }
                    }
                    break;

                case 3:
                    System.out.print("ID da máquina que deseja atualizar: ");
                    int idAtualizar = Integer.parseInt(leia.nextLine());

                    System.out.print("Novo nome: ");
                    String novoNome = leia.nextLine();

                    System.out.print("Novo setor: ");
                    String novoSetor = leia.nextLine();

                    System.out.print("Nova criticidade (BAIXA, MEDIA, ALTA): ");
                    String novaCriticidade = leia.nextLine().toUpperCase();

                    System.out.print("Novo status (ATIVA, EM_MANUTENCAO, INATIVA): ");
                    String novoStatus = leia.nextLine().toUpperCase();

                    Maquina maquinaAtualizada = new Maquina(idAtualizar, novoNome, novoSetor, novaCriticidade,
                            novoStatus);
                    dao.atualizar(maquinaAtualizada);
                    break;

                case 4:
                    System.out.print("ID da máquina que deseja excluir: ");
                    int idExcluir = Integer.parseInt(leia.nextLine());
                    dao.excluir(idExcluir);
                    break;
                
                case 5:
                    System.out.print("ID da máquina: ");
                    int idMTTR = Integer.parseInt(leia.nextLine());
                    Double mttr = osDao.calcularMTTR(idMTTR);
                        if (mttr == null) {
                            System.out.println("Essa máquina não tem ordens de serviço concluídas.");
                        } else {
                            System.out.printf("MTTR (tempo médio de reparo): %.2f horas%n", mttr);
                        }
                    break;

                case 6:
                    System.out.print("ID da maquina: ");
                    int idMTBF = Integer.parseInt(leia.nextLine());
                    Double mtbf = osDao.calcularMTBF(idMTBF);
                        if (mtbf == null) {
                        System.out.println("Essa máquina ainda não tem dados suficientes para calcular o MTBF.");
                        } else {
                        System.out.printf("MTBF (tempo médio entre falhas): %.2f horas%n", mtbf);
                        }
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    public static void menuOrdensServico(Scanner leia, OrdemServicoDAO osDao) {
        int opcao;

        do {
            System.out.println("\n----- ORDENS DE SERVIÇO -----");
            System.out.println("1 - Abrir ordem de serviço");
            System.out.println("2 - Listar ordens de serviço");
            System.out.println("3 - Atualizar status");
            System.out.println("4 - Excluir ordem de serviço");
            System.out.println("5 - Concluir ordem de serviço");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(leia.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite apenas o número.");
                opcao = -1;
            }

            switch (opcao) {

                case 1:
                    System.out.print("ID da máquina relacionada: ");
                    int idMaquina = Integer.parseInt(leia.nextLine());

                    System.out.print("Descrição: ");
                    String descricao = leia.nextLine();

                    System.out.print("Tipo (CORRETIVA, PREVENTIVA): ");
                    String tipo = leia.nextLine().toUpperCase();

                    System.out.print("Prioridade (BAIXA, MEDIA, ALTA): ");
                    String prioridade = leia.nextLine().toUpperCase();

                    OrdemServico novaOS = new OrdemServico(idMaquina, descricao, tipo, prioridade, "ABERTA");
                    osDao.inserir(novaOS);
                    break;

                case 2:
                    ArrayList<OrdemServico> ordens = osDao.listarTodas();
                    if (ordens.isEmpty()) {
                        System.out.println("Nenhuma ordem de serviço cadastrada ainda.");
                    } else {
                        System.out.println("\n--- Ordens de serviço cadastradas ---");
                        for (OrdemServico os : ordens) {
                            System.out.println(os);
                        }
                    }
                    break;

                case 3:
                    System.out.print("ID da ordem de serviço: ");
                    int idOS = Integer.parseInt(leia.nextLine());

                    System.out.print("Novo status (ABERTA, EM_ANDAMENTO, CONCLUIDA): ");
                    String novoStatusOS = leia.nextLine().toUpperCase();

                    osDao.atualizarStatus(idOS, novoStatusOS);
                    break;

                case 4:
                    System.out.print("ID da ordem de serviço que deseja excluir: ");
                    int idExcluirOS = Integer.parseInt(leia.nextLine());
                    osDao.excluir(idExcluirOS);
                    break;

                case 5:
                    System.out.print("ID da ordem de serviço que deseja concluir: ");
                    int idConcluir = Integer.parseInt(leia.nextLine());
                    osDao.concluir(idConcluir);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }
}