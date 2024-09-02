package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IEmpresaDAO extends CrudRepository<Empresa, Long>{

	Empresa findById(long id);
	
	Empresa findByCNPJ (String CNPJ);

	List<Empresa> findAll();
	
	Empresa save(Empresa Empresa);

	void deleteById(Long id);

}
