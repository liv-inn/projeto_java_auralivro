

package com.project.library.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.project.library.models.Autor;

@Repository
public class AutorDAO {
    private JdbcTemplate jdbc;

    @Autowired
    public AutorDAO(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    public void inserir(Autor autor) {
        String sql = "INSERT INTO autor(nome, nacionalidade) VALUES(?, ?)";
        jdbc.update(sql, autor.getNome(), autor.getNacionalidade());
    }

    public List<Autor> listarTodos() {
        String sql = "SELECT * FROM autor";
        return jdbc.query(sql, new AutorRowMapper());
    }

    public Autor buscarPorId(int id) {
        String sql = "SELECT * FROM autor WHERE id = ?";
        return jdbc.queryForObject(sql, new AutorRowMapper(), id);
    }

    public void atualizar(Autor autor) {
        String sql = "UPDATE autor SET nome = ?, nacionalidade = ? WHERE id = ?";
        jdbc.update(sql, autor.getNome(), autor.getNacionalidade(), autor.getId());
    }

    public void deletar(int id) {
        String sql = "DELETE FROM autor WHERE id = ?";
        jdbc.update(sql, id);
    }

    private static class AutorRowMapper implements RowMapper<Autor> {
        @Override
        public Autor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Autor autor = new Autor();
            autor.setId(rs.getInt("id"));
            autor.setNome(rs.getString("nome"));
            autor.setNacionalidade(rs.getString("nacionalidade"));
            return autor;
        }
    }
}


