package br.ufscar.dc.dsw.controller;

import jakarta.validation.Valid;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import br.ufscar.dc.dsw.service.spec.IInscricaoService;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	private IEmpresaService service;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IVagaService vagaService;

	@Autowired
	private IInscricaoService inscricaoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Empresa empresa) {
		return "empresa/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("empresas", service.buscarTodos());
		return "empresa/lista";
	}

	@GetMapping("/vagas")
	public String listarVagas(ModelMap model) {

		model.addAttribute("vagas", vagaService.buscarVagasEmpresa(getEmpresa()));
		return "empresa/listaVagas";
	}

	private Long getEmpresa() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		Usuario user = usuarioDetails.getUsuario();
		Empresa empresa = service.buscarPorId(user.getId());

		if (empresa == null) {
			throw new RuntimeException("Empresa não encontrada para o usuário com ID: " + user.getId());
		}

		return empresa.getId();
	}

	@GetMapping("/cadastrarVaga")
	public String cadastrarVaga(ModelMap model) {
        
		Vaga vaga = new Vaga();
		Empresa empresa = service.buscarPorId(getEmpresa());
		vaga.setEmpresa(empresa);

		System.out.println("ID da empresa na vaga: " + vaga.getEmpresa());

		model.addAttribute("vaga", vaga);

		return "empresa/cadastroVaga";
	
	}

	@PostMapping("/salvarVaga")
	public String salvarVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr) {


		Empresa empresa = service.buscarPorId(getEmpresa());
		vaga.setEmpresa(empresa);
		System.out.println("ID da empresa na vaga SALVAR VAGA: " + vaga.getEmpresa());

		// if (result.hasErrors()) {
		// 
		// 	System.out.println("Erros de validação: " + result.getAllErrors() );
		// 	return "empresa/cadastroVaga";
		// }
	
		vagaService.salvar(vaga);
		attr.addFlashAttribute("sucess", "vaga.create.sucess");
		return "redirect:/empresas/vagas";
	}


	@GetMapping("/inscricoes/{id}")
	public String listarIncricoes(@PathVariable("id") Long id, ModelMap model) {

		model.addAttribute("inscricoes", inscricaoService.buscarTodosPorVaga(id));
		return "empresa/listaInscricoes";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "empresa/cadastro";
		}

		usuarioService.salvar(usuarioService.buscarPorId(empresa.getId()));
		service.salvar(empresa);
		attr.addFlashAttribute("sucess", "empresa.create.sucess");
		return "redirect:/empresas/listar";
	}

	@PostMapping("/resultado/{id}")
	public String resultado(@PathVariable("id") Long id, @RequestParam("resul") String resul, RedirectAttributes attr) {
		Inscricao inscricao = inscricaoService.buscarPorId(id);

		if (resul.equals("Nao")) {
			inscricao.setResultado("NÃO SELECIONADO");
		} else if (resul.equals("Entrevista")) {
			inscricao.setResultado("ENTREVISTA");
		} else {
			inscricao.setResultado("ANÁLISE");
		}

		inscricaoService.salvar(inscricao);

		Long vagaId = inscricao.getVaga().getId();

		attr.addFlashAttribute("success", "Inscrição analisada com sucesso.");
		return "redirect:/empresas/inscricoes/" + vagaId;
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("empresa", service.buscarPorId(id));
		return "empresa/cadastro";
	}


	@PostMapping("/editar")
	public String editar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr) {

		if (result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null) { //CNPJ ou cnpj?
			return "empresa/cadastro";
		}

		// Buscar a empresa existente para obter os dados atuais
		Empresa empresaExistente = service.buscarPorId(empresa.getId());
		Usuario usuarioExistente = usuarioService.buscarPorId(empresaExistente.getId());

		Usuario usuario = usuarioService.buscarPorId(empresa.getId());

		usuario.setPassword(usuarioExistente.getPassword());
		usuario.setId(usuarioExistente.getId()); 

		// Salvar as alterações
		usuarioService.salvar(usuario);
		service.salvar(empresa);
		attr.addFlashAttribute("sucess", "empresa.edit.sucess");
		return "redirect:/empresas/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.empresaTemVagas(id)) {
			model.addAttribute("fail", "empresa.delete.fail");
		} else {
			service.excluir(id);
			model.addAttribute("sucess", "empresa.delete.sucess");
		}
		return listar(model);
	}
}
