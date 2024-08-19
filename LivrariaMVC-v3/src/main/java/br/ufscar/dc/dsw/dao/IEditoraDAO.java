package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Empresa;

@SuppressWarnings("unchecked")
public interface IEditoraDAO extends CrudRepository<Empresa, Long>{

	Empresa findById(long id);
	
	Empresa findByCNPJ (String CNPJ);

	List<Empresa> findAll();
	
	Empresa save(Empresa editora);

	void deleteById(Long id);
}
