package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;


public class InscricaoDAO extends GenericDAO{

    public List<Inscricao> getAllProfissional(Profissional profissional) {

        List<Inscricao> listaInscricao = new ArrayList<>();

        String sql = "SELECT * FROM Inscricao i, Profissional p, Vaga v WHERE i.cpf_id = p.cpf and i.vaga_id = v.id_vaga";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCPF());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long vaga_id = resultSet.getLong("vaga_id");
                Vaga vaga = new VagaDAO().get(vaga_id);

                Integer resultado = resultSet.getInt("resultado");
                String qualificacao = resultSet.getString("qualificacao");

                Inscricao inscricao = new Inscricao(profissional, vaga, resultado, qualificacao);

                listaInscricao.add(inscricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaInscricao;
    }
    
    public List<Inscricao> getAllEmpresa(Empresa empresa) {

        List<Inscricao> listaInscricao = new ArrayList<>();

        String sql = "SELECT * FROM Inscricao i, Vaga v, Empresa e WHERE e.cnpj = ? and e.cnpj = v.empresa_id and v.id_vaga = i.vaga_id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCNPJ());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long vaga_id = resultSet.getLong("vaga_id");
                Vaga vaga = new VagaDAO().get(vaga_id);

                String cpf_id = resultSet.getString("cpf_id");
                Profissional profissional = new ProfissionalDAO().get(cpf_id);

                Integer resultado = resultSet.getInt("resultado");
                String qualificacao = resultSet.getString("qualificacao");

                Inscricao inscricao = new Inscricao(profissional, vaga, resultado, qualificacao);

                listaInscricao.add(inscricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaInscricao;
    }
}
