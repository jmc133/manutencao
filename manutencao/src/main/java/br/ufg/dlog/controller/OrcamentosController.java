package br.ufg.dlog.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.dlog.classes.DefeitosRelatados;
import br.ufg.dlog.classes.FantOrcAtribuido;
import br.ufg.dlog.classes.FantTotalizadorOrcamentos;
import br.ufg.dlog.classes.FantVisualizarOrcamentos;
import br.ufg.dlog.classes.Orcamentos;
import br.ufg.dlog.classes.Usuario;
import br.ufg.dlog.dao.EscolherOrcamento;
import br.ufg.dlog.repository.DefeitosRelatadosRepository;
import br.ufg.dlog.repository.FantContaOrcamentoRepository;
import br.ufg.dlog.repository.FantOrcAtribuidoRepository;
import br.ufg.dlog.repository.FantTotalizadorOrcamentosRepository;
import br.ufg.dlog.repository.FantVisualizarOrcamentosRepository;
import br.ufg.dlog.repository.OrcamentosRepository;
import br.ufg.dlog.repository.OrdemServicoRepository;
import br.ufg.dlog.repository.UsuarioRepository;

@Controller
public class OrcamentosController {
	Long ordemServico;
	Usuario u = new Usuario();
	String nomeUsuario;
	
	@Autowired
	OrcamentosRepository orcamentosReposoitory;
	@Autowired
	FantContaOrcamentoRepository fantContaOrcamentoRepository;
	@Autowired
	FantVisualizarOrcamentosRepository fantVisualizarOrcamentosRepository;
	@Autowired
	FantTotalizadorOrcamentosRepository fantTotalizadorOrcamentosRepository;
	@Autowired
	OrdemServicoRepository ordemServicoRepository;
	@Autowired
	DefeitosRelatadosRepository defeitosRelatadosRepository;
	@Autowired
	FantOrcAtribuidoRepository fantOrcAtribuidoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	EscolherOrcamento Eo = new EscolherOrcamento();
	
	@RequestMapping("/verorcamentos")
	public ModelAndView VerOrcamentos() {
		ModelAndView mv = new ModelAndView("verificarorcamentos.html");
		
		if(!Objects.isNull(fantContaOrcamentoRepository.idOrcados())) {
			mv.addObject("idorcados", fantContaOrcamentoRepository.idOrcados());
			mv.addObject("servicoorcados", new FantVisualizarOrcamentos());
			mv.addObject("totalizador",new FantTotalizadorOrcamentos());
			mv.addObject("total_atribuido", new FantOrcAtribuido());
			mv.addObject("atribuicao",0);
		}else {
			mv.addObject("idorcados", null);
			mv.addObject("servicoorcados", new FantVisualizarOrcamentos());
			mv.addObject("totalizador",new FantTotalizadorOrcamentos());
			mv.addObject("total_atribuido", new FantOrcAtribuido());
			mv.addObject("atribuicao",0);
		}
		
		
		return mv;
	}
	
	@RequestMapping("/orcamentoporordem/{id_ordem}")
	public ModelAndView OrcamentosPorOrdem(@PathVariable("id_ordem") Long id_ordem) {
		ModelAndView mv = new ModelAndView("verificarorcamentos.html");
		if(id_ordem!=null) {
			ordemServico=id_ordem;
			
			mv.addObject("idorcados", fantContaOrcamentoRepository.idOrcados());
			mv.addObject("servicoorcados", fantVisualizarOrcamentosRepository.visualizarOrcamentos(id_ordem));
			mv.addObject("totalizador",fantTotalizadorOrcamentosRepository.totalizadorOrcamentos(id_ordem));
			mv.addObject("total_atribuido", fantOrcAtribuidoRepository.listaTotalAtribuido(ordemServico));
		}else {
			ordemServico=(long) 0;
			mv.addObject("idorcados", null);
			mv.addObject("servicoorcados", new FantVisualizarOrcamentos());
			mv.addObject("totalizador",new FantTotalizadorOrcamentos());
			mv.addObject("total_atribuido", new FantOrcAtribuido());
			mv.addObject("atribuicao",0);
		}
		
		
		return mv;
	}
	@RequestMapping("/atribuir/{id_orc}")
	public ModelAndView AtribuirServico(@PathVariable("id_orc") Long id_orc) {
		ModelAndView mv = new ModelAndView("verificarorcamentos.html");
		Orcamentos orc = new Orcamentos();
		orc = orcamentosReposoitory.selecionaOrcamentoPorId(id_orc);
		Authentication autenticar = SecurityContextHolder.getContext().getAuthentication();
		if(!Objects.isNull(autenticar)) {
			u = usuarioRepository.selecionaUsuarioPorLogin(autenticar.getName());
		}
		
		if(id_orc!=null) {
			
			Orcamentos orcGrava = new Orcamentos();
			
			System.out.println("Id: "+orc.getId_orcamento()+" fk_ordem: "+orc.getFk_ordem_servico()+" fk_defeitos: "+orc.getFk_defeitos_relatados()+" oçamento atribuido: "+orc.getOrc_atribuido());
			
			if(!Objects.equals(orc.getOrc_atribuido(),"S") ) {
					
			DefeitosRelatados drGrava = new DefeitosRelatados();
			DefeitosRelatados drRecebe = new DefeitosRelatados();
			drRecebe = defeitosRelatadosRepository.defeitoRelatado(orc.getFk_defeitos_relatados());
			
			drGrava.setAtribuido(orc.getFk_pessoa_juridica());
			drGrava.setDescricao(drRecebe.getDescricao());
			drGrava.setFkOrdemServico(drRecebe.getFkOrdemServico());
			drGrava.setIdDefeitos(orc.getFk_defeitos_relatados());
			drGrava.setQtdRelatado(drRecebe.getQtdRelatado());
			drGrava.setIdDefeitos(drRecebe.getIdDefeitos());
			
			orcGrava.setData_orcamento(orc.getData_orcamento());
			orcGrava.setFk_defeitos_relatados(orc.getFk_defeitos_relatados());
			orcGrava.setFk_ordem_servico(orc.getFk_ordem_servico());
			orcGrava.setFk_pessoa_juridica(orc.getFk_pessoa_juridica());
			orcGrava.setId_orcamento(id_orc);
			orcGrava.setOrcador(orc.getOrcador());
			orcGrava.setResponsavel_orcamento(orc.getResponsavel_orcamento());
			orcGrava.setValor_total(orc.getValor_total());
			orcGrava.setValor_unitario(orc.getValor_unitario());
			orcGrava.setOrc_atribuido("S");
			orcGrava.setFk_atribuidor(u.getId());
			
			
			Eo.atribuirNao(orc.getFk_defeitos_relatados());
		
			
			//orcamentosReposoitory.UpdateOrcamentosDesfaz(orc.getFk_defeitos_relatados());
			defeitosRelatadosRepository.save(drGrava);
			orcamentosReposoitory.save(orcGrava);
			}else {
				
				System.out.println("Id: "+orc.getId_orcamento()+" fk_ordem: "+orc.getFk_ordem_servico()+" fk_defeitos: "+orc.getFk_defeitos_relatados()+" oçamento atribuido: "+orc.getOrc_atribuido());
				DefeitosRelatados drGrava = new DefeitosRelatados();
				DefeitosRelatados drRecebe = new DefeitosRelatados();
				drRecebe = defeitosRelatadosRepository.defeitoRelatado(orc.getFk_defeitos_relatados());
				
				drGrava.setAtribuido(null);
				drGrava.setDescricao(drRecebe.getDescricao());
				drGrava.setFkOrdemServico(drRecebe.getFkOrdemServico());
				drGrava.setIdDefeitos(orc.getFk_defeitos_relatados());
				drGrava.setQtdRelatado(drRecebe.getQtdRelatado());
				drGrava.setIdDefeitos(drRecebe.getIdDefeitos());
				
				orcGrava.setData_orcamento(orc.getData_orcamento());
				orcGrava.setFk_defeitos_relatados(orc.getFk_defeitos_relatados());
				orcGrava.setFk_ordem_servico(orc.getFk_ordem_servico());
				orcGrava.setFk_pessoa_juridica(orc.getFk_pessoa_juridica());
				orcGrava.setId_orcamento(id_orc);
				orcGrava.setOrcador(orc.getOrcador());
				orcGrava.setResponsavel_orcamento(orc.getResponsavel_orcamento());
				orcGrava.setValor_total(orc.getValor_total());
				orcGrava.setValor_unitario(orc.getValor_unitario());
				orcGrava.setOrc_atribuido("N");
				
				Eo.atribuirNao(orc.getFk_defeitos_relatados());
				defeitosRelatadosRepository.save(drGrava);
				orcamentosReposoitory.save(orcGrava);
				
			}
			mv.addObject("idorcados", fantContaOrcamentoRepository.idOrcados());
			mv.addObject("servicoorcados", fantVisualizarOrcamentosRepository.visualizarOrcamentos(ordemServico));
			mv.addObject("totalizador",fantTotalizadorOrcamentosRepository.totalizadorOrcamentos(ordemServico));
			mv.addObject("total_atribuido", fantOrcAtribuidoRepository.listaTotalAtribuido(ordemServico));
		
		}else {
			mv.addObject("idorcados", null);
			mv.addObject("servicoorcados", new FantVisualizarOrcamentos());
			mv.addObject("totalizador",new FantTotalizadorOrcamentos());
			mv.addObject("total_atribuido", new FantOrcAtribuido());
			mv.addObject("atribuicao",0);
		}
		
		
		return mv;
	}

}
