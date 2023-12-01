package br.ucb.poo.dao;

import org.hibernate.query.Query;

import br.ucb.poo.model.Medicamento;
import br.ucb.poo.model.Produto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ProdutoDAO {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void close() {
        sessionFactory.close();
    }

public void salvarProduto(Produto produto) {
    Transaction transaction = null;
    try (Session session = sessionFactory.openSession()) {
        transaction = session.beginTransaction();
        if (produto instanceof Medicamento) {
            Medicamento medicamento = (Medicamento) produto;
            session.save(medicamento);
        } else {
            session.save(produto);
        }
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}

    

    public Produto getProdutoByNome(String nome) {
        try (Session session = sessionFactory.openSession()) {
            Query<Produto> query = session.createQuery("FROM Produto WHERE nome = :nome", Produto.class);
            query.setParameter("nome", nome);
            return query.uniqueResult();
        }
    }
    public Produto getMedicamentoByNome(String nome) {
        try (Session session = sessionFactory.openSession()) {
            Query<Medicamento> query = session.createQuery("FROM Medicamento WHERE nome = :nome", Medicamento.class);
            query.setParameter("nome", nome);
            return query.uniqueResult();
        }
    }

    public void atualizarProduto(Produto produto) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (produto instanceof Medicamento) {
            Medicamento medicamento = (Medicamento) produto;
            session.update(medicamento);
        } else {
            session.update(produto);
        }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletarProdutoByNome(String nome) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Produto> query = session.createQuery("FROM Produto WHERE nome = :nome", Produto.class);
            query.setParameter("nome", nome);
            Produto produto = query.uniqueResult();
            
            if (produto != null) {
                transaction = session.beginTransaction();
    
                
                if (produto instanceof Medicamento) {
                    
                }
    
                session.delete(produto);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }    

    public void atualizarProduto(String nomeProduto, double novoPreco) {
    }
}
