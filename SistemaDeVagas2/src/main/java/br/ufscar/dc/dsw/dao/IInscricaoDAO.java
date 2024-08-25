package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

@SuppressWarnings("unchecked")
public interface IInscricaoDAO extends CrudRepository<Inscricao, Long>{

	Inscricao findById(long id);

	List<Inscricao> findAllByProfissional(Profissional p);

    List<Inscricao> findAllByVaga(Vaga v);
	
	Inscricao save(Inscricao inscricao);
}