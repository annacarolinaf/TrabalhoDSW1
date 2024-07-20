package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, profissional.getCPF());
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


}