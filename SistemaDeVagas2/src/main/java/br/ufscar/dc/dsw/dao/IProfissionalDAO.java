package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IProfissionalDAO extends CrudRepository<Profissional, Long>{

	Profissional findById(long id);

	Profissional findByCpf (String cpf);

	List<Profissional> findAll();

	Profissional findByUsuario(Usuario u);
	
	Profissional save(Profissional profissional);

	void deleteById(Long id);

	@Query("SELECT u FROM Profissional u WHERE u.usuario.id = :usuarioId")
    Profissional findByUserId(@Param("usuarioId") Long usuarioId);
}