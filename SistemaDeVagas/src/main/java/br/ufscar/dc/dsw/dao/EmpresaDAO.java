package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;

public class EmpresaDAO extends GenericDAO {

    public Empresa get(Usuario usuario) {
        Empresa Empresa = null;
        
        String sql = "SELECT * from Empresa where id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, usuario.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
<<<<<<< HEAD
                String nome = resultSet.getString("nome");
                Empresa Empresa = new Empresa(cnpj);
                listaEmpresas.add(Empresa);
=======
                String cidade = resultSet.getString("cidade");
                String descricao = resultSet.getString("descricao");
                Empresa = new Empresa(cnpj, cidade, descricao, usuario);
>>>>>>> f5ecce8609eeb589d373d67c8ca9cd7cc2b0d74e
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Empresa;
    }

    public void insert(Empresa empresa) {    
        String sql = "INSERT INTO Empresa (cnpj, cidade, descricao, id_usuario) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;    
            statement = conn.prepareStatement(sql);
            statement.setString(1, empresa.getCNPJ());
            statement.setString(2, empresa.getCidade());
            statement.setString(3, empresa.getDescricao());
            statement.setLong(4, empresa.getUsuario().getId());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Empresa empresa){
        String sql = "DELETE FROM Empresa WHERE id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, empresa.getUsuario().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(Empresa empresa) {
        String sql = "UPDATE Empresa SET cnpj = ?, cidade = ?, descricao = ?";
        sql += " WHERE id_usuario = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCNPJ());
            statement.setString(2, empresa.getCidade());
            statement.setString(3, empresa.getDescricao());
            statement.setLong(4, empresa.getUsuario().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
