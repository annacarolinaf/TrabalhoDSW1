package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IInscricaoDAO;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.IInscricaoService;

@Service
@Transactional(readOnly = false)
public class InscricaoService implements IInscricaoService {

	@Autowired
	IInscricaoDAO dao;
	
	public void salvar(Inscricao inscricao) {
		dao.save(inscricao);
	}

	@Transactional(readOnly = true)
	public Inscricao buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Inscricao> buscarTodosPorProfissional(Profissional p) {
		return dao.findAllByProfissional(p);
	}

	@Transactional(readOnly = true)
	public List<Inscricao> buscarTodosPorVaga(Long id) {
		return dao.findAllByVaga(id);
	}
}
