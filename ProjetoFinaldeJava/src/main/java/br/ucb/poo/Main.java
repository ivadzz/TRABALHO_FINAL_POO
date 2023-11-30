package br.ucb.poo;

import java.util.Scanner;

import br.ucb.poo.dao.ProdutoDAO;
import br.ucb.poo.model.Medicamento;
import br.ucb.poo.model.Receita;
import br.ucb.poo.model.Produto;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;

        while (escolha != 5) {
            System.out.println("[1] Cadastrar Produto\n[2] Consultar Produto\n[3] Atualizar Produto\n[4] Excluir Produto\n[5]Sair\nOpção: ");
            escolha = scanner.nextInt();
            scanner.nextLine();
            String procurarProduto;

            switch (escolha) {
                case 1:
                System.out.print("Nome do produto: ");
                String nomeProduto = scanner.nextLine();
                System.out.print("Preço do produto: ");
                double precoProduto = scanner.nextDouble();
                scanner.nextLine(); 
                System.out.print("É um medicamento? (S/N): ");
                String resposta = scanner.nextLine();
                Produto novoProduto;
                if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Composição do medicamento: ");
                String composicaoMedicamento = scanner.nextLine();
                System.out.print("Tipo da Receita: ");
                String tipoReceita = scanner.nextLine();
                novoProduto = new Medicamento(nomeProduto, precoProduto, composicaoMedicamento, tipoReceita);
                } else {
                novoProduto = new Produto(nomeProduto, precoProduto);
            }
                produtoDAO.salvarProduto(novoProduto);
                System.out.println("Produto registrado com sucesso!");
            break;
                case 2:
                System.out.println("Informe o nome do produto que deseja consultar: ");
                procurarProduto = scanner.nextLine();
                
                Produto produtoEncontrado = produtoDAO.getProdutoByNome(procurarProduto);
                Medicamento medicamentoEncontrado = (Medicamento) produtoDAO.getMedicamentoByNome(procurarProduto);
                
                if (produtoEncontrado != null) {
                    System.out.println("Produto encontrado: " + produtoEncontrado);
                } else if (medicamentoEncontrado != null) {
                    System.out.println("Medicamento encontrado: " + medicamentoEncontrado);
                } else {
                    System.out.println("Produto ou Medicamento não encontrado.");
                }
                break;
                case 3:             
                System.out.print("Nome do produto: ");
                procurarProduto = scanner.nextLine();
                Produto produtoAtualizado = produtoDAO.getProdutoByNome(procurarProduto);
                if (produtoAtualizado != null) {
                    System.out.println("Produto encontrado: " + produtoAtualizado);
                    System.out.print("Novo preço do produto: ");
                    double novoPreco = scanner.nextDouble();
                    scanner.nextLine();
                    produtoAtualizado.setPreco(novoPreco);
                    if (produtoAtualizado instanceof Medicamento) {
                        Medicamento medicamentoAtualizado = (Medicamento) produtoAtualizado;
                        System.out.print("Nova composição: ");
                        String novaComposicao = scanner.nextLine();
                        medicamentoAtualizado.setComposicao(novaComposicao);
                        System.out.print("Novo tipo de receita: ");
                        String novoTipoReceita = scanner.nextLine();
                        medicamentoAtualizado.setTipoReceita(novoTipoReceita);
                    }
                    produtoDAO.atualizarProduto(produtoAtualizado);
                    System.out.println("Produto atualizado com sucesso!");
                } else {
                    System.out.println("Produto não encontrado.");
                }
                break;
                case 4:
                System.out.println("Digite o nome do produto que deseja excluir: ");
                procurarProduto = scanner.nextLine();
                Produto produtoParaExcluir = produtoDAO.getProdutoByNome(procurarProduto);
                if (produtoParaExcluir != null) {
                    produtoDAO.deletarProdutoByNome(procurarProduto);
                    System.out.println("Produto excluído com sucesso!");
                } else {
                    Produto medicamentoParaExcluir = produtoDAO.getMedicamentoByNome(procurarProduto);
                    if (medicamentoParaExcluir != null) {
                        produtoDAO.deletarProdutoByNome(procurarProduto);
                        System.out.println("Medicamento excluído com sucesso!");
                    } else {
                        System.out.println("Produto ou medicamento não encontrado.");
                    }
                }
                break;
                case 5:
                    System.out.println("Saindo... Até mais :)");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        produtoDAO.close();
        scanner.close();
    }
}

