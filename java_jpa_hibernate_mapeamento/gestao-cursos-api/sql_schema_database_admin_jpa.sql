-- Tabela de Alunos
CREATE TABLE alunos (
    id BIGINT PRIMARY KEY AUTOINCREMENT,
    nome_completo VARCHAR(255),
    matricula VARCHAR(100),
    nascimento DATE,
    email VARCHAR(255)
);

-- Tabela de Endereços
CREATE TABLE enderecos (
    id BIGINT PRIMARY KEY AUTOINCREMENT,
    logradouro VARCHAR(255),
    endereco VARCHAR(255),
    numero VARCHAR(50),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    cep INTEGER,
    aluno_id BIGINT,
    FOREIGN KEY (aluno_id) REFERENCES alunos(id)
);

-- Tabela de Telefones
CREATE TABLE telefones (
    id BIGINT PRIMARY KEY AUTOINCREMENT,
    ddd VARCHAR(5),
    numero VARCHAR(20),
    aluno_id BIGINT,
    FOREIGN KEY (aluno_id) REFERENCES alunos(id)
);

-- Tabela de Professores
CREATE TABLE professores (
    id BIGINT PRIMARY KEY AUTOINCREMENT,
    nome_completo VARCHAR(255),
    matricula VARCHAR(100),
    email VARCHAR(255)
);

-- Tabela de Cursos
CREATE TABLE cursos (
    id BIGINT PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(255),
    descricao TEXT,
    professor_id BIGINT,
    FOREIGN KEY (professor_id) REFERENCES professores(id)
);

-- Tabela de Materiais do Curso
CREATE TABLE materiais_curso (
    id BIGINT PRIMARY KEY AUTOINCREMENT,
    url VARCHAR(255),
    curso_id BIGINT UNIQUE,
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

-- Tabela de relacionamento Alunos x Cursos (ManyToMany)
CREATE TABLE alunos_cursos (
    aluno_id BIGINT,
    curso_id BIGINT,
    PRIMARY KEY (aluno_id, curso_id),
    FOREIGN KEY (aluno_id) REFERENCES alunos(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);
