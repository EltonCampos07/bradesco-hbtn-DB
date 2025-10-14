package com.techcamps.gestao.cursos.demo;

import com.techcamps.gestao.cursos.entities.*;
import com.techcamps.gestao.cursos.models.AlunoModel;
import com.techcamps.gestao.cursos.models.CursoModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class GestaoCursosMain {

    static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Instanciando os Models
        AlunoModel alunoModel = new AlunoModel(em);
        CursoModel cursoModel = new CursoModel(em);

        // Criando Professor
        Professor professor = new Professor();
        professor.setNomeCompleto("João Silva");
        professor.setMatricula("P123");
        professor.setEmail("joao@escola.com");

        em.getTransaction().begin();
        em.persist(professor);
        em.getTransaction().commit();

        // Criando Aluno
        Aluno aluno = new Aluno();
        aluno.setNomeCompleto("Maria Souza");
        aluno.setMatricula("A456");
        aluno.setNascimento(new Date());
        aluno.setEmail("maria@aluno.com");

        // Telefone
        Telefone telefone = new Telefone();
        telefone.setDDD("11");
        telefone.setNumero("999999999");
        telefone.setAluno(aluno);

        // Endereço
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua A");
        endereco.setEndereco("Rua A, 100");
        endereco.setNumero("100");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCep(12345678);
        endereco.setAluno(aluno);

        aluno.setTelefones(List.of(telefone));
        aluno.setEnderecos(List.of(endereco));

        alunoModel.create(aluno);

        // Criando Curso
        Curso curso = new Curso();
        curso.setNome("Java Básico");
        curso.setSigla("JAVAB");
        curso.setProfessor(professor);
        curso.setAlunos(List.of(aluno));

        // Material do Curso
        MaterialCurso material = new MaterialCurso();
        material.setUrl("http://material.com/java");
        material.setCurso(curso);
        curso.setMaterial(material);

        cursoModel.create(curso);

        // Testando findAll
        List<Aluno> alunos = alunoModel.findAll();
        List<Curso> cursos = cursoModel.findAll();

        System.out.println("Alunos cadastrados: " + alunos.size());
        System.out.println("Cursos cadastrados: " + cursos.size());

        // Testando update
        aluno.setNomeCompleto("Maria Souza Atualizada");
        alunoModel.update(aluno);

        // Testando delete
        cursoModel.delete(curso.getId());

        em.close();
        emf.close();
    }
}
