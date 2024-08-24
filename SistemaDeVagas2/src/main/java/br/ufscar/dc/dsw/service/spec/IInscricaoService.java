package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

public interface IInscricaoService {

	Inscricao buscarPorId(Long id);

	List<Inscricao> buscarTodosPorProfissional(Profissional p);

    List<Inscricao> buscarTodosPorVaga(Vaga v);
	
	void salvar(Inscricao inscricao);
}
