package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;

public interface IEditoraService {

	Empresa buscarPorId(Long id);

	List<Empresa> buscarTodos();

	void salvar(Empresa editora);

	void excluir(Long id);
	
	boolean editoraTemLivros(Long id);
}
