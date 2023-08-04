package br.ufg.dlog.controller;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.dlog.classes.DefeitosRelatados;
import br.ufg.dlog.classes.Orcamentos;
import br.ufg.dlog.classes.OrdemServico;
import br.ufg.dlog.repository.DefeitosRelatadosRepository;

import br.ufg.dlog.repository.FantMovimentoRepository;
import br.ufg.dlog.repository.FantUsuarioRepository;
import br.ufg.dlog.repository.FatasmaVeiculoRepository;
import br.ufg.dlog.repository.OrdemServicoRepository;
import br.ufg.dlog.repository.PessoaJuridicaRepository;
import br.ufg.dlog.repository.PessoaRepository;
import br.ufg.dlog.repository.UsuarioRepository;
import br.ufg.dlog.repository.VeiculosRepository;

@Controller
public class IndexController {
	Long usuarioid;

	@Autowired
	private FantUsuarioRepository fantUsuarioRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private FantMovimentoRepository fantMovimentoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRerpository;
	@Autowired
	private VeiculosRepository veiculosRepository;
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	@Autowired
	private DefeitosRelatadosRepository defeitosRelatadosRepository;
	@Autowired
	private FatasmaVeiculoRepository fantasmaVeiculoRepository;
	
	
	@RequestMapping("/login")
	public String index() {
		return "login";
	}
	/*@RequestMapping("/logincadastro")
	public String LoginCadastro() {
		return "logincadastro";
	}*/
	
	
	@RequestMapping( method = RequestMethod.GET, value = "**/index2")
	public ModelAndView index2(){
		ModelAndView mv = new ModelAndView("/index.html");
		Authentication autenticar = SecurityContextHolder.getContext().getAuthentication();
		String usuarioAutenticado = autenticar.getName();
		usuarioid = usuarioRepository.buscaIdLogin(usuarioAutenticado);
		mv.addObject("regra",fantUsuarioRepository.verificaPermissao(usuarioid));
		
		
		mv.addObject("logo", "/logoUFGDlog.jpg");
		return mv;
	
	}
	
	
	@RequestMapping("/index")
	public ModelAndView redireciona() {
		ModelAndView mv = new ModelAndView("/index.html");
		return mv;
	}
	@RequestMapping("/tabelamovimento")
	public ModelAndView tabelaMovimento() {
		ModelAndView mv = new ModelAndView("reservas/movimento.html");
		mv.addObject("mov", fantMovimentoRepository.buscaApartirDeHoje());
		mv.addObject("botaovoltar",1);
		mv.addObject("df",DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		return mv;
	}
	
	@RequestMapping("/movimento")
	public ModelAndView movimento() {
		ModelAndView mv = new ModelAndView("reservas/movimento.html");
		return mv;
	}
	
    @GetMapping("**/disponibilidademotoristas")
    public ModelAndView DispoinibilidadeMotoristas() {
    	ModelAndView mv = new ModelAndView("/disponibilidademotoristas.html");
    	Date data = new Date();
    	SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	String datas = f.format(data);
    	mv.addObject("msg", "Disponibilidades dia  "+datas);
    	mv.addObject("Lista", pessoaRepository.motoristasNaoEscalados());
    	return mv;
    	
    }
	
	
	@RequestMapping("/ajuda")
	public ModelAndView Ajuda() {
		ModelAndView mv = new ModelAndView("/ajuda.html");
		
		return mv;
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	@RequestMapping("/principal")
	public ModelAndView Principal() {
		
		 
		ModelAndView mv = new ModelAndView("/principal.html");
		mv.addObject("servicos", pessoaJuridicaRerpository.fornecedorDeServicos());
		mv.addObject("pecas",pessoaJuridicaRerpository.fornecedorDePecas());
		mv.addObject("pecasservicos", pessoaJuridicaRerpository.fornecedorDePecasServicos());
		mv.addObject("veiculosativos", veiculosRepository.buscaTesteManutencao());
		mv.addObject("os", ordemServicoRepository.ordemServicoAbertas());
		mv.addObject("veiculosoficina", ordemServicoRepository.veiculosComServicoAberto());
		return mv;
	}
	@RequestMapping("/ospesquisa/{os}")
	public ModelAndView OsPesquisa(@PathVariable("os") Long os) {
		
		ModelAndView mv = new ModelAndView("ordemdeservico.html");
		if (os!=null) {
			mv.addObject("msg", "Pesquisa realizada com sucesso!");				
			mv.addObject("os",ordemServicoRepository.findAll());
			mv.addObject("osid", ordemServicoRepository.verOrdemPorId(os));
			mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());								
			mv.addObject("itens", defeitosRelatadosRepository.itensRelatados(os));
			mv.addObject("idos", os);
			mv.addObject("item", defeitosRelatadosRepository.findById(os));
			
		}else {
			mv.addObject("msg", "Não encontrado resultado para pesquisa!");				
			mv.addObject("os",ordemServicoRepository.findAll());
			mv.addObject("osid", new OrdemServico());
			mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());								
			mv.addObject("itens", new DefeitosRelatados());
			mv.addObject("idos", os);
			mv.addObject("item", new DefeitosRelatados());
		}
		return mv;
		
	}
	@RequestMapping("/osvpesquisa/{v}")
	public ModelAndView OsPesquisa(@PathVariable("v") String v) {
		ModelAndView mv = new ModelAndView("ordemdeservico.html");
		if (v!=null&& !v.isEmpty()&& !Objects.isNull(v)) {
			Long IdOs = ordemServicoRepository.ordemServicoPorVeiculo(v);
			mv.addObject("msg", "Pesquisa realizada com sucesso!");				
			mv.addObject("os",ordemServicoRepository.ordensServicosAbertas());
			mv.addObject("osid", ordemServicoRepository.verOrdemPorId(IdOs));
			mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());								
			mv.addObject("itens", defeitosRelatadosRepository.itensRelatados(IdOs));
			mv.addObject("idos", IdOs);
			mv.addObject("item", defeitosRelatadosRepository.findById(IdOs));
		}else {
			mv.addObject("msg", "Não encontrado resultado para pesquisa!");				
			mv.addObject("os",ordemServicoRepository.findAll());
			mv.addObject("osid", new OrdemServico());
			mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());								
			mv.addObject("itens", new DefeitosRelatados());
			mv.addObject("idos", null);
			mv.addObject("item", new DefeitosRelatados());
		}
		return mv;
		
	}
	@RequestMapping("/termooficina")
	public ModelAndView TermoResponsabilidadeOficina() {
		ModelAndView mv = new ModelAndView("termoresponsabilidadeoficina.html");
		
		return mv;
		
	}
	@RequestMapping("/termocondutor")
	public ModelAndView TermoResponsabilidadeCondutor() {
		ModelAndView mv = new ModelAndView("termocondutor.html");
		
		return mv;
		
	}

}
