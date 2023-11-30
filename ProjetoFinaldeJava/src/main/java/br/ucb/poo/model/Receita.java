package br.ucb.poo.model;

import javax.persistence.Entity;

@Entity
public class Receita extends Produto {
    private String tipo;

    
    public Receita (String tipo ,String tipoReceita, String nome, String preco, String composicao) {
        super(nome, 0, preco, composicao);
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @Override
    public String toString() {
        return "Medicamento{" +
                "nome='" + getNome() + '\'' +
                ", preco=" + getPreco() +
                ", tipo='" + getTipo() + '\'' +
                '}';
    }
}
