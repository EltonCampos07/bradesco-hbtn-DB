package models;

import entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PessoaModel {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private final EntityManager em = emf.createEntityManager();

    public void create(Pessoa pessoa) {
        em.getTransaction().begin();
        em.persist(pessoa);
        em.getTransaction().commit();
    }

    public Pessoa findById(Long id) {
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> findAll() {
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
    }

    public void update(Long id, Pessoa dadosAtualizados) {
        em.getTransaction().begin();
        Pessoa pessoa = findById(id);
        if (pessoa != null) {
            pessoa.setNome(dadosAtualizados.getNome());
            pessoa.setEmail(dadosAtualizados.getEmail());
            pessoa.setIdade(dadosAtualizados.getIdade());
            pessoa.setCpf(dadosAtualizados.getCpf());
            pessoa.setDataNascimento(dadosAtualizados.getDataNascimento());
            em.merge(pessoa);
        }
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Pessoa pessoa = findById(id);
        if (pessoa != null) em.remove(pessoa);
        em.getTransaction().commit();
    }
}
