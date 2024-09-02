package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IProfissionalService profissionalService;

	@Autowired
	private IVagaService vagaService;

	@GetMapping("/cadastrar")
	public String cadastrar(Profissional profissional) {
		return "profissional/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("profissionais", profissionalService.buscarTodos());
		return "profissional/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "profissional/cadastro";
		}

		usuarioService.salvar(usuarioService.buscarPorId(profissional.getId()));
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

		// if (result.hasErrors()) {
		// 	return "profissional/cadastro";
		// }

		if (result.getFieldErrorCount() > 1 || result.getFieldError("cpf") == null) {
			return "profissional/cadastro";
		}

		Profissional profissionalExistente = profissionalService.buscarPorId(profissional.getId());
		Usuario usuarioExistente = usuarioService.buscarPorId(profissionalExistente.getId());

		Usuario usuario = usuarioService.buscarPorId(profissional.getId());

		usuario.setPassword(usuarioExistente.getPassword());
		usuario.setId(usuarioExistente.getId()); 

		usuarioService.salvar(usuario);
		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "profissional.edit.sucess");
		return "redirect:/profissionais/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (profissionalService.profissionalTemInscricao(id)) {
			model.addAttribute("fail", "profissional.delete.fail");
		} else {
			profissionalService.excluir(id);
			model.addAttribute("sucess", "profissional.delete.sucess");
		}
		return listar(model);
	}

	//@ModelAttribute("editoras")
	//public List<Editora> listaEditoras() {
	//	return editoraService.buscarTodos();
	//}
}