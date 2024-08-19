package br.ufscar.dc.dsw.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IEditoraService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {

	@Autowired
	private IProfissionalService profissionalService;

	@Autowired
	private IEditoraService editoraService;

	@GetMapping("/cadastrar")
	public String cadastrar(Profissional profissional) {
		return "profissional/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("livros", profissionalService.buscarTodos());
		return "profissional/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "profissional/cadastro";
		}

		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "profissional.create.sucess");
		return "redirect:/profissionais/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("livro", profissionalService.buscarPorId(id));
		return "profissional/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "livro/cadastro";
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

	@ModelAttribute("editoras")
	public List<Editora> listaEditoras() {
		return editoraService.buscarTodos();
	}
}
