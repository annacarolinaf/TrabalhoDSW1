package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
@RequestMapping("/vagas")
public class VagasController {
	
	@Autowired
	private IVagaService service;
	
	
	// @GetMapping("/cadastrar")
	// public String cadastrar(Compra compra) {
	// 	String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
	// 	compra.setUsuario(this.getUsuario());
	// 	compra.setData(data);
	// 	return "compra/cadastro";
	// }
	
	// private Usuario getUsuario() {
	// 	UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// 	return usuarioDetails.getUsuario();
	// }
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
					
		model.addAttribute("vagas",service.buscarTodos());
		
		return "vagas/lista";
	}
	
	// @PostMapping("/salvar")
	// public String salvar(@Valid Compra compra, BindingResult result, RedirectAttributes attr) {
		
	// 	if (result.hasErrors()) {
	// 		return "compra/cadastro";
	// 	}
		
	// 	service.salvar(compra);
	// 	attr.addFlashAttribute("sucess", "compra.create.sucess");
	// 	return "redirect:/compras/listar";
	// }
	
	// @ModelAttribute("livros")
	// public List<Livro> listaLivros() {
	// 	return livroService.buscarTodos();
	// }
}
