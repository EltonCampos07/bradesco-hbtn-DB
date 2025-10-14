package models;

import entities.Curso;

import javax.persistence.EntityManager;
import java.util.List;

public class CursoModel {

    private EntityManager em;

    public CursoModel(EntityManager em) {
        this.em = em;
    }

    public void create(Curso curso) {
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
    }

    public Curso findById(Long id) {
        return em.find(Curso.class, id);
    }

    public List<Curso> findAll() {
        return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    }

    public void update(Curso curso) {
        em.getTransaction().begin();
        em.merge(curso);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Curso curso = findById(id);
        if (curso != null) {
            em.remove(curso);
        }
        em.getTransaction().commit();
    }
}
