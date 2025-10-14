package models;

import entities.Aluno;

import javax.persistence.EntityManager;
import java.util.List;

public class AlunoModel {

    private EntityManager em;

    public AlunoModel(EntityManager em) {
        this.em = em;
    }

    public void create(Aluno aluno) {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public Aluno findById(Long id) {
        return em.find(Aluno.class, id);
    }

    public List<Aluno> findAll() {
        return em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }

    public void update(Aluno aluno) {
        em.getTransaction().begin();
        em.merge(aluno);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Aluno aluno = findById(id);
        if (aluno != null) {
            em.remove(aluno);
        }
        em.getTransaction().commit();
    }
}
