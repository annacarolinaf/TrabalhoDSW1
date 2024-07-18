package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;

public class EmpresaDAO extends GenericDAO {

    public List<Empresa> getAll() {

        List<Empresa> listaEmpresas = new ArrayList<>();

        String sql = "SELECT * from Empresa";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                Empresa Empresa = new Empresa(cnpj);
                listaEmpresas.add(Empresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEmpresas;
    }

    public Empresa get(Long id) {
        Empresa Empresa = null;
        
        String sql = "SELECT * from Empresa where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                Empresa = new Empresa(id, cnpj, nome);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Empresa;
    }
}
