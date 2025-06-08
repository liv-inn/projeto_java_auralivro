
CREATE TABLE autor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nacionalidade VARCHAR(100)
);

CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    ano_publicacao INTEGER,
    autor_id INTEGER NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES autor(id) ON DELETE CASCADE
);

CREATE TABLE emprestimo (
    id SERIAL PRIMARY KEY,
    livro_id INTEGER NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE,
    nome_locatario VARCHAR(100) NOT NULL,
    FOREIGN KEY (livro_id) REFERENCES livro(id) ON DELETE CASCADE
);

CREATE INDEX idx_livro_autor ON livro(autor_id);
CREATE INDEX idx_emprestimo_livro ON emprestimo(livro_id);
CREATE INDEX idx_emprestimo_devolucao ON emprestimo(data_devolucao);

INSERT INTO autor (nome, nacionalidade) VALUES 
('Machado de Assis', 'Brasileira'),
('Clarice Lispector', 'Brasileira'),
('J.K. Rowling', 'Britânica'),
('George Orwell', 'Britânica'),
('Agatha Christie', 'Britânica'),
('J.R.R. Tolkien', 'Britânica'),
('Gabriel García Márquez', 'Colombiana');

INSERT INTO livro (titulo, isbn, ano_publicacao, autor_id) VALUES
('Dom Casmurro', '978-8535902775', 1899, 1),
('Memórias Póstumas de Brás Cubas', '978-8535902776', 1881, 1),
('A Hora da Estrela', '978-8535902777', 1977, 2),
('Harry Potter e a Pedra Filosofal', '978-8535902778', 1997, 3),
('1984', '978-8535902779', 1949, 4),
('O Assassinato no Expresso do Oriente', '978-8535902780', 1934, 5),
('O Senhor dos Anéis', '978-8535902781', 1954, 6),
('Cem Anos de Solidão', '978-8535902782', 1967, 7);

INSERT INTO emprestimo (livro_id, data_emprestimo, nome_locatario) VALUES
(1, '2023-01-15', 'João Silva'),
(3, '2023-02-20', 'Maria Oliveira'),
(4, '2023-03-10', 'Carlos Souza'),
(6, '2023-04-05', 'Ana Paula'),
(7, '2023-05-12', 'Roberto Carlos');

UPDATE emprestimo SET data_devolucao = '2023-01-30' WHERE id = 1;
UPDATE emprestimo SET data_devolucao = '2023-04-20' WHERE id = 4;