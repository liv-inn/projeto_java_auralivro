
package com.project.library.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.project.library.models.Livro;

@Repository
public class LivroDAO {
    private JdbcTemplate jdbc;

    @Autowired
    public LivroDAO(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    public void inserir(Livro livro) {
        String sql = "INSERT INTO livro(titulo, isbn, ano_publicacao, autor_id) VALUES(?, ?, ?, ?)";
        jdbc.update(sql, livro.getTitulo(), livro.getIsbn(), livro.getAnoPublicacao(), livro.getAutorId());
    }

    public List<Livro> listarTodos() {
        String sql = "SELECT l.*, a.nome as autor_nome FROM livro l JOIN autor a ON l.autor_id = a.id";
        return jdbc.query(sql, new LivroRowMapper());
    }

    public Livro buscarPorId(int id) {
        String sql = "SELECT l.*, a.nome as autor_nome FROM livro l JOIN autor a ON l.autor_id = a.id WHERE l.id = ?";
        return jdbc.queryForObject(sql, new LivroRowMapper(), id);
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, isbn = ?, ano_publicacao = ?, autor_id = ? WHERE id = ?";
        jdbc.update(sql, livro.getTitulo(), livro.getIsbn(), livro.getAnoPublicacao(), livro.getAutorId(), livro.getId());
    }

    public void deletar(int id) {
        String sql = "DELETE FROM livro WHERE id = ?";
        jdbc.update(sql, id);
    }

    public List<Livro> buscarPorAutor(int autorId) {
        String sql = "SELECT l.*, a.nome as autor_nome FROM livro l JOIN autor a ON l.autor_id = a.id WHERE l.autor_id = ?";
        return jdbc.query(sql, new LivroRowMapper(), autorId);
    }

    private static class LivroRowMapper implements RowMapper<Livro> {
        @Override
        public Livro mapRow(ResultSet rs, int rowNum) throws SQLException {
            Livro livro = new Livro();
            livro.setId(rs.getInt("id"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setIsbn(rs.getString("isbn"));
            livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
            livro.setAutorId(rs.getInt("autor_id"));
            livro.setAutorNome(rs.getString("autor_nome"));
            return livro;
        }
    }
}