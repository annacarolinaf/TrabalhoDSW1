package br.ufscar.dc.dsw.controller;

<<<<<<< Updated upstream
=======
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IInscricaoService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/inscricoes")
public class InscricaoController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IProfissionalService profissionalService;

	@Autowired
	private IVagaService vagaService;

	@Autowired
	private IInscricaoService inscricaoService;

	@GetMapping("/cadastrar/[]")
	public String cadastrar(Profissional profissional) {
		return "profissional/cadastro";
	}

	@GetMapping("/")
	public String listarVagas(ModelMap model) {
		model.addAttribute("vagas", vagaService.buscarTodos());
		return "inscricao/listaVagas";
	}

	@PostMapping("/buscar")
	public String listarVagasPorCidade(@RequestParam("cidade") String cidade, ModelMap model) {
		System.out.println("vagasFiltradas");
		List<Vaga> vagasFiltradas = vagaService.buscarVagasCidade(cidade);
		model.addAttribute("vagas", vagasFiltradas);
		return "inscricao/listaVagas"; 
	}


	@GetMapping("/listar")
	public String listarInscricoes(ModelMap model) {
		model.addAttribute("inscricoes", inscricaoService.buscarTodosPorProfissional(profissionalService.buscarPorId(getProfissional())));
		return "inscricao/lista";
	}

	private Long getProfissional() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Usuario user = usuarioDetails.getUsuario();
		Profissional profissional = profissionalService.buscarPorUserId(user.getId());

		if (profissional == null) {
			throw new RuntimeException("Profissional não encontrada para o usuário com ID: " + user.getId());
		}

		return profissional.getId();
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "profissional/cadastro";
		}

		usuarioService.salvar(profissional.getUsuario());
		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "profissional.create.sucess");
		return "redirect:/profissionais/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissional", profissionalService.buscarPorId(id));
		return "profissional/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "profissional/cadastro";
		}

		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "profissional.edit.sucess");
		return "redirect:/profissionais/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		profissionalService.excluir(id);
		attr.addFlashAttribute("sucess", "profissional.delete.sucess");
		return "redirect:/profissionais/listar";
	}

	//@ModelAttribute("editoras")
	//public List<Editora> listaEditoras() {
	//	return editoraService.buscarTodos();
	//}
}