import java.util.Scanner;

public class Main {

    private static Banco banco;

    public static void main(String[] args) {

        banco = new Banco();

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("=== Menu do Banco ===");
            System.out.println("1. Criar conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Ver extrato");
            System.out.println("5. Transferir");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    criarConta(scanner);
                    break;
                case 2:
                    realizarDeposito(scanner);
                    break;
                case 3:
                    realizarSaque(scanner);
                    break;
                case 4:
                    verExtrato(scanner);
                    break;
                case 5:
                    realizarTransferencia(scanner);
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }

    }

    private static void criarConta(Scanner scanner) {

        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.println("Escolha o tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        if(tipoConta != 1 && tipoConta != 2){
            return;
        }

        Cliente cliente = new Cliente(nome, senha);

        Conta conta = (tipoConta == 1) ? new ContaCorrente(cliente) : new ContaPoupanca(cliente);

        banco.adicionarConta(conta);

        System.out.println(String.format("Conta %d criada com sucesso!", conta.getNumero()));

    }

    private static Conta autenticarConta(Scanner scanner) {
        
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Conta conta = banco.obterContaPorNumero(numero);

        if (conta != null && conta.getCliente().getSenha().equals(senha)) {
            return conta;
        } else {
            System.out.println("Nome ou senha inválidos.");
            return null;
        }
    }

    private static void realizarDeposito(Scanner scanner) {

        Conta conta = autenticarConta(scanner);

        if (conta != null) {

            System.out.print("Valor do depósito: R$ ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); 
            conta.depositar(valor);

            System.out.println("Depósito realizado com sucesso!");

        }

    }

    private static void realizarSaque(Scanner scanner) {

        Conta conta = autenticarConta(scanner);

        if (conta != null) {

            System.out.print("Valor do saque: R$ ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            conta.sacar(valor);
            System.out.println("Saque realizado com sucesso!");

        }

    }

    private static void verExtrato(Scanner scanner) {
    
        Conta conta = autenticarConta(scanner);

        if (conta != null) {
            conta.extrato();
        }

    }

    private static void realizarTransferencia(Scanner scanner) {

        Conta contaOrigem = autenticarConta(scanner);

        if (contaOrigem != null) {

            System.out.print("Número da conta de destino: ");
            int numero = scanner.nextInt();
            scanner.nextLine();

            Conta contaDestino = banco.obterContaPorNumero(numero);

            if (contaDestino != null) {

                System.out.print("Valor da transferência: R$ ");
                double valor = scanner.nextDouble();
                scanner.nextLine();

                contaOrigem.transferir(valor, contaDestino);
                System.out.println("Transferência realizada com sucesso!");

            } else {
                System.out.println("Conta de destino não encontrada.");
            }

        }

    }
}
