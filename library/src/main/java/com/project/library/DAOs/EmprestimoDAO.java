package com.project.library.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.project.library.models.Emprestimo;

@Repository
public class EmprestimoDAO {
    private JdbcTemplate jdbc;

    @Autowired
    public EmprestimoDAO(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    public void inserir(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo(livro_id, data_emprestimo, nome_locatario) VALUES(?, ?, ?)";
        jdbc.update(sql, emprestimo.getLivroId(), emprestimo.getDataEmprestimo(), emprestimo.getNomeLocatario());
    }

    public List<Emprestimo> listarTodos() {
        String sql = "SELECT e.*, l.titulo as livro_titulo FROM emprestimo e JOIN livro l ON e.livro_id = l.id";
        return jdbc.query(sql, new EmprestimoRowMapper());
    }

    public Emprestimo buscarPorId(Long id) {
        String sql = "SELECT e.*, l.titulo as livro_titulo FROM emprestimo e JOIN livro l ON e.livro_id = l.id WHERE e.id = ?";
        return jdbc.queryForObject(sql, new EmprestimoRowMapper(), id);
    }

    public void atualizar(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET livro_id = ?, data_emprestimo = ?, data_devolucao = ?, nome_locatario = ? WHERE id = ?";
        jdbc.update(sql, emprestimo.getLivroId(), emprestimo.getDataEmprestimo(), 
                   emprestimo.getDataDevolucao(), emprestimo.getNomeLocatario(), emprestimo.getId());
    }

    public void registrarDevolucao(Long id, LocalDate dataDevolucao) {
        String sql = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ?";
        jdbc.update(sql, dataDevolucao, id);
    }

    private static class EmprestimoRowMapper implements RowMapper<Emprestimo> {
        @Override 
        public Emprestimo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setId(rs.getLong("id"));
            emprestimo.setLivroId(rs.getLong("livro_id"));
            emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
            
            if (rs.getDate("data_devolucao") != null) {
                emprestimo.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
            }
            
            emprestimo.setNomeLocatario(rs.getString("nome_locatario"));
            emprestimo.setLivroTitulo(rs.getString("livro_titulo"));
            return emprestimo;
        }
    }
}