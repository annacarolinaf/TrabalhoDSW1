package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

public class ProfissionalDAO extends GenericDAO {

    public void insert(Profissional profissional) {

        if (profissional == null) {
            throw new IllegalArgumentException("Profissional cannot be null");
        }

        Usuario usuario = profissional.getUsuario();
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario cannot be null");
        }

        Long usuarioId = usuario.getId();
        if (usuarioId == null) {
            throw new IllegalArgumentException("Usuario ID cannot be null");
        }

        System.out.println("Inserting Profissional: " + profissional);
        System.out.println("Usuario ID: " + usuarioId);
        String sql = "INSERT INTO Profissional (cpf, data_nasc, sexo, telefone, id_usuario) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setString(1, profissional.getCpf());
            statement.setString(2, profissional.getData_nasc());
            statement.setString(3, profissional.getSexo());
            statement.setString(4, profissional.getTelefone());
            statement.setLong(5, usuarioId);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional getByIdUsuario(Usuario usuario) {
        Profissional profissional = null;

        String sql = "SELECT * FROM Profissional e WHERE e.id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String data_nasc = resultSet.getString("data_nasc");
                String sexo = resultSet.getString("sexo");
                String telefone = resultSet.getString("telefone");

                profissional = new Profissional(cpf, data_nasc, sexo, telefone, usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    public Profissional get(String cpf) {
        Profissional profissional = null;

        String sql = "SELECT * FROM Profissional p, Usuario u WHERE e.cpf = ? AND p.id_usuario = u.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String data_nasc = resultSet.getString("data_nasc");
                String sexo = resultSet.getString("sexo");
                String telefone = resultSet.getString("telefone");
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");

                Usuario usuario = new Usuario(id, nome, email, senha, papel);
                profissional = new Profissional(cpf, data_nasc, sexo, telefone, usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissional SET cpf = ?, data_nasc = ?, sexo = ?, telefone = ?";
        sql += " WHERE id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCpf());
            statement.setString(2, profissional.getData_nasc());
            statement.setString(3, profissional.getSexo());
            statement.setString(4, profissional.getTelefone());
            statement.setLong(5, profissional.getUsuario().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Profissional profissional) {
        String sql_inscricao = "DELETE FROM Inscricao WHERE cpf_id = ?;";
        String sql_profissional = "DELETE FROM Profissional WHERE cpf = ?";
        String sql_usuario = "DELETE FROM Usuario WHERE id = ?";

        Long id = profissional.getUsuario().getId();

        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql_inscricao);

            statement.setString(1, profissional.getCpf());
            statement.executeUpdate();

            statement = conn.prepareStatement(sql_inscricao);

            statement.setString(1, profissional.getCpf());
            statement.executeUpdate();

            statement = conn.prepareStatement(sql_profissional);

            statement.setString(1, profissional.getCpf());
            statement.executeUpdate();

            statement = conn.prepareStatement(sql_usuario);

            statement.setLong(1, id);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}