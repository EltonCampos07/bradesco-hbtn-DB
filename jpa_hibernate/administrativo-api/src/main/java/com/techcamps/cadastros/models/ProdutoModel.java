package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProdutoModel {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private final EntityManager em = emf.createEntityManager();

    public void create(Produto produto) {
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public Produto findById(Long id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> findAll() {
        return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }

    public void update(Long id, Produto dadosAtualizados) {
        em.getTransaction().begin();
        Produto produto = findById(id);
        if (produto != null) {
            produto.setNome(dadosAtualizados.getNome());
            produto.setQuantidade(dadosAtualizados.getQuantidade());
            produto.setPreco(dadosAtualizados.getPreco());
            produto.setStatus(dadosAtualizados.getStatus());
            em.merge(produto);
        }
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Produto produto = findById(id);
        if (produto != null) em.remove(produto);
        em.getTransaction().commit();
    }
}
