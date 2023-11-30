package br.ucb.poo.model;

import javax.persistence.Entity;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
import javax.persistence.Id;


@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {
    @Id
    private String nome;
    private double preco;

    public Produto(String nomeProduto, double precoProduto, String composicaoReceita, String tipoReceita) {
        
    }
    public Produto() {
    
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Produto " +
                "nome'" + nome + '\'' +
                ", preco='" + preco + '\'' +
                '}';
    }
}
