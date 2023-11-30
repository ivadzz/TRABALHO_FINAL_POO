package br.ucb.poo.model;

import javax.persistence.Entity;

@Entity
public class Medicamento extends Produto{
    private String composicao; 

    public Medicamento(String nome, double preco, String composicao, String composicaoReceita) {
        super(nome, preco);
        this.composicao = composicao;
    }
    public Medicamento() {
    
    }

    public String getComposicao() {
        return composicao;
    }
    public void setComposicao(String composicao) {
        this.composicao = composicao;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "nome='" + getNome() + '\'' +
                ", preco=" + getPreco() +
                ", composicao='" + getComposicao() + '\'' +
                '}';
    }

    public void setTipoReceita(String novoTipoReceita) {
    }
}


