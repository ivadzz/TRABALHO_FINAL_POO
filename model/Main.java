package model;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Farmacia farmacia = new Farmacia();

            while (true) {
                System.out.println("\n=== Menu ===");
                System.out.println("1 - Cadastrar Produto");
                System.out.println("2 - Consultar Estoque");
                System.out.println("3 - Atualizar Preço");
                System.out.println("4 - Excluir Produto");
                System.out.println("0 - Sair");

                System.out.print("Escolha uma opção: ");
                int escolha = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer

                switch (escolha) {
                    case 1:
                        System.out.print("Nome do produto: ");
                        String nomeProduto = scanner.nextLine();
                        System.out.print("Preço do produto: ");
                        double precoProduto = scanner.nextDouble();
                        scanner.nextLine();  // Limpar o buffer
                        System.out.print("É um medicamento? (S/N): ");
                        String resposta = scanner.nextLine();
                        Produto novoProduto;
                        if (resposta.equalsIgnoreCase("S")) {
                            System.out.print("Fórmula do medicamento: ");
                            String formula = scanner.nextLine();
                            novoProduto = new Medicamento(nomeProduto, precoProduto, formula);
                        } else {
                            novoProduto = new Produto(nomeProduto, precoProduto);
                        }
                        farmacia.cadastrarProduto(novoProduto);
                        break;

                    case 2:
                        farmacia.consultarEstoque();
                        break;

                    case 3:
                        System.out.print("Nome do produto: ");
                        nomeProduto = scanner.nextLine();
                        System.out.print("Novo preço: ");
                        double novoPreco = scanner.nextDouble();
                        scanner.nextLine();  // Limpar o buffer
                        farmacia.atualizarPreco(nomeProduto, novoPreco);
                        break;

                    case 4:
                        System.out.print("Nome do produto: ");
                        nomeProduto = scanner.nextLine();
                        farmacia.excluirProduto(nomeProduto);
                        break;

                    

                    case 0:
                        System.out.println("Saindo do programa. Até mais!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Main []";
    }
}

class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}



class Medicamento extends Produto {
    private String formula;

    public Medicamento(String nome, double preco, String formula) {
        super(nome, preco);
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "nome='" + getNome() + '\'' +
                ", preco=" + getPreco() +
                ", formula='" + formula + '\'' +
                '}';
    }
}

class Farmacia {
    private ArrayList<Produto> estoque;
    

    public Farmacia() {
        this.estoque = new ArrayList<>();
        
    }

    public void cadastrarProduto(Produto produto) {
        estoque.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    public void consultarEstoque() {
        System.out.println("Estoque da farmácia:");
        for (Produto produto : estoque) {
            System.out.println(produto);
        }
    }

    public void atualizarPreco(String nomeProduto, double novoPreco) {
        for (Produto produto : estoque) {
            if (produto.getNome().equals(nomeProduto)) {
                produto.setPreco(novoPreco);
                System.out.println("Preço atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    public void excluirProduto(String nomeProduto) {
        for (Produto produto : estoque) {
            if (produto.getNome().equals(nomeProduto)) {
                estoque.remove(produto);
                System.out.println("Produto excluído com sucesso!");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    
}
