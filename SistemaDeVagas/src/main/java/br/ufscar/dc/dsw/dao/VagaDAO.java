package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;

public class VagaDAO extends GenericDAO {

    public void insert(Vaga vaga) {

        String sql = "INSERT INTO Vaga (salario, descricao, data_limite, empresa_id)  VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setFloat(1, vaga.getSalario()); //salario
            statement.setString(2, vaga.getDescricao()); //descricao
            statement.setString(3, vaga.getData_limite()); //data_limite
            statement.setString(5, vaga.getEmpresa().getCNPJ()); //empresa_id
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vaga> getAll() {

        List<Vaga> listaVagas = new ArrayList<>();

        String sql = "SELECT * FROM Inscricao i, Empresa e, Vaga v, Profissional p, Usuario u  WHERE e.cnpj = v.empresa_id AND i.vaga_id = v.id_vaga AND p.cpf = i.cpf_id order by v.id_vaga";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                
                //retornos da tabela Usuario
                String nome_u = resultSet.getString("nome"); //vem da tabela usuário
                String e_mail = resultSet.getString("email"); 
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                Long id_usuario = resultSet.getLong("id"); //id do usuário
                
                //retornos da tabela Vaga
                Long id_vaga = resultSet.getLong("id_vaga"); //id_vaga
                Float salario = resultSet.getFloat("salario"); //salario
                String descricao_vaga = resultSet.getString("descricao_vaga"); //descricao
                String data_limite = resultSet.getString("data_limite"); // data_limite
                
                //retornos da tabela Empresa
                String cnpj = resultSet.getString("cnpj");  //empresa_id 
                String descricao = resultSet.getString("descricao");
                String cidade = resultSet.getString("cidade");

                //Criação dos novos objetos para a leitura
                Usuario usuario = new Usuario(id_usuario, e_mail, nome_u, senha, papel);
                Empresa empresa = new Empresa(cnpj, cidade, descricao, usuario);
                Vaga vaga = new Vaga(id_vaga, salario, descricao_vaga, data_limite, empresa);

                listaVagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }

    public void delete(Vaga vaga) {
        String sql = "DELETE FROM Vaga where id_vaga = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getID());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Vaga vaga) {
        String sql = "UPDATE Vaga SET salario = ?, descricao_vaga = ?, data_limite = ?";
        sql += ", empresa_id = ? WHERE id_vaga = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setFloat(1, vaga.getSalario());//salario
            statement.setString(2, vaga.getDescricao());//descricao
            statement.setString(3, vaga.getData_limite());//data_limite
            statement.setString(4, vaga.getEmpresa().getCNPJ());//empresa_id = cnpj
            statement.setLong(5, vaga.getID()); //id da vaga
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vaga get(Long id) {
        Vaga vaga = null;

        String sql = "SELECT * from Vaga v, Empresa e where v.id_vaga = ? and v.empresa_id = e.cnpj";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                Float salario = resultSet.getFloat("salario");
                String descricao_vaga = resultSet.getString("descricao_vaga");
                String data_limite = resultSet.getString("data_limite");
                String empresaID = resultSet.getString("empresa_id");

                Empresa empresa = new EmpresaDAO().get(empresaID);

                vaga = new Vaga(id, salario, descricao_vaga, data_limite, empresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vaga;
    }

    public List<Vaga> getAllVagasEmpresa(Usuario usuario) {

        List<Vaga> listaVagas = new ArrayList<>();

        String sql = "SELECT * from Vaga v, Empresa e, Usuario u where e.id_usuario = ? and v.empresa_id = e.cnpj and u.id = e.id_usuario";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Long id = resultSet.getLong("id_vaga");
                Float salario = resultSet.getFloat("salario");
                String descricao_vaga = resultSet.getString("descricao_vaga");
                String data_limite = resultSet.getString("data_limite");
                String empresa_id = resultSet.getString("empresa_id");

                Empresa empresa = new EmpresaDAO().get(empresa_id);
                

                Vaga vaga = new Vaga(id, salario, descricao_vaga, data_limite, empresa);

                listaVagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }
}
