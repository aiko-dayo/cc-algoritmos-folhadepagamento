import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

class Funcionario {
    int registro;
    String nomeCompleto;
    static final double salarioBase = 2000.00;

    Funcionario(int registro, String nomeCompleto) {
        this.registro = registro;
        this.nomeCompleto = nomeCompleto;
    }

    double calcularBonus() {
        return 0.0;
    }

    double calcularSalario() {
        return salarioBase;
    }

    void exibirDados() {
        System.out.println("Nome completo: " + nomeCompleto);
        System.out.println("Número de registro: " + registro);
        System.out.printf("Salário fixo: R$ %.2f\n", salarioBase);
        System.out.printf("Bônus: R$ %.2f\n", calcularBonus());
        System.out.printf("Salário final: R$ %.2f\n", calcularSalario());
        System.out.println();
    }
}

class FuncionarioComissionado extends Funcionario {
    double totalVendas;
    double percentualComissao;

    FuncionarioComissionado(int registro, String nomeCompleto, double totalVendas, double percentualComissao) {
        super(registro, nomeCompleto);
        this.totalVendas = totalVendas;
        this.percentualComissao = percentualComissao;
    }

    @Override
    double calcularBonus() {
        return totalVendas * percentualComissao / 100;
    }

    @Override
    double calcularSalario() {
        return salarioBase + calcularBonus();
    }
}

class FuncionarioProducao extends Funcionario {
    int quantidadePecas;
    double valorPorPeca;

    FuncionarioProducao(int registro, String nomeCompleto, int quantidadePecas, double valorPorPeca){
        super(registro, nomeCompleto);
        this.quantidadePecas = quantidadePecas;
        this.valorPorPeca = valorPorPeca;
    }

    @Override
    double calcularBonus() {
        return valorPorPeca * quantidadePecas;
    }

    @Override
    double calcularSalario() {
        return salarioBase + calcularBonus();
    }
}

public class SistemaPagamento {
    static void exibirMenu() {
        System.out.println("\n=== FOLHA DE PAGAMENTO === ");
        System.out.println("1 - Cadastrar funcionário");
        System.out.println("2 - Cadastrar funcionário comissionado");
        System.out.println("3 - Cadastrar funcionário de produção");
        System.out.println("4 - Gerar folha de pagamento");
        System.out.println("0 - Sair do programa");
        System.out.print("Escolha uma opção: (Digite o número) ");
    }

    static void cadastrarFuncionario(Scanner entrada, ArrayList<Funcionario> lista) {
        System.out.println("\n--- Cadastrar funcionário ---");

        entrada.nextLine(); // limpa buffer do menu
        System.out.print("Nome completo: ");
        String nomeCompleto = entrada.nextLine();

        System.out.print("Registro de funcionário: ");
        int registro = entrada.nextInt();

        Funcionario f = new Funcionario(registro, nomeCompleto);
        lista.add(f);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    static void cadastrarComissionado(Scanner entrada, ArrayList<Funcionario> lista) {
        System.out.println("\n--- Cadastrar funcionário Comissionado ---");

        entrada.nextLine(); // limpa buffer
        System.out.print("Nome completo: ");
        String nomeCompleto = entrada.nextLine();

        System.out.print("Registro de funcionário: ");
        int registro = entrada.nextInt();

        System.out.print("Valor total de vendas: R$ ");
        double vendas = entrada.nextDouble();

        System.out.print("Informe o percentual de comissão (%%): ");
        double comissao = entrada.nextDouble();

        FuncionarioComissionado f = new FuncionarioComissionado(registro, nomeCompleto, vendas, comissao);
        lista.add(f);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    static void cadastrarProducao(Scanner entrada, ArrayList<Funcionario> lista) {
        System.out.println("\n--- Cadastrar funcionário de Produção ---");

        entrada.nextLine(); // limpa buffer
        System.out.print("Nome completo: ");
        String nomeCompleto = entrada.nextLine();

        System.out.print("Registro de funcionário: ");
        int registro = entrada.nextInt();

        System.out.print("Número de peças produzidas: ");
        int quantidadePecas = entrada.nextInt();

        System.out.print("Informe o valor do bônus por peça: R$ ");
        double valorPorPeca = entrada.nextDouble();

        FuncionarioProducao f = new FuncionarioProducao(registro, nomeCompleto, quantidadePecas, valorPorPeca);
        lista.add(f);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    static void gerarFolhaPagamento(ArrayList<Funcionario> lista) {
        System.out.println("\n--- Folha de Pagamento ---");

        if (lista.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
        } else {
            System.out.println("Total de pessoas cadastradas: " + lista.size());
            System.out.println("================================");
            for (Funcionario f : lista) {
                f.exibirDados();
            }
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner entrada = new Scanner(System.in);
        ArrayList<Funcionario> colaboradores = new ArrayList<>();
        int opcao;

        System.out.println("Bem-vindo ao Sistema de Folha de Pagamento!");

        do {
            exibirMenu();
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarFuncionario(entrada, colaboradores);
                    break;
                case 2:
                    cadastrarComissionado(entrada, colaboradores);
                    break;
                case 3:
                    cadastrarProducao(entrada, colaboradores);
                    break;
                case 4:
                    gerarFolhaPagamento(colaboradores);
                    break;
                case 0:
                    System.out.println("\nEncerrando o programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida, escolha entre 0 e 4.");
            }
        } while (opcao != 0);

        entrada.close();
    }
}