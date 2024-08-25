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
@RequestMapping("/home")
public class VagasController {
    
    @Autowired
    private IVagaService service;
    
    @GetMapping
    public String home(ModelMap model) {
        // Adiciona a lista de vagas ao modelo
        model.addAttribute("vagas", service.buscarTodos());
        // Retorna a página de entrada
        return "home"; // Nome do arquivo HTML sem a extensão
    }
}

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