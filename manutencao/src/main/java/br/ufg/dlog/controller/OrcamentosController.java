package br.ufg.dlog.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.dlog.classes.DefeitosRelatados;
import br.ufg.dlog.classes.FantTotalizadorOrcamentos;
import br.ufg.dlog.classes.FantVisualizarOrcamentos;
import br.ufg.dlog.classes.Orcamentos;
import br.ufg.dlog.repository.DefeitosRelatadosRepository;
import br.ufg.dlog.repository.FantContaOrcamentoRepository;
import br.ufg.dlog.repository.FantTotalizadorOrcamentosRepository;
import br.ufg.dlog.repository.FantVisualizarOrcamentosRepository;
import br.ufg.dlog.repository.OrcamentosRepository;
import br.ufg.dlog.repository.OrdemServicoRepository;

@Controller
public class OrcamentosController {
	Long ordemServico;
	
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
	
	@RequestMapping("/verorcamentos")
	public ModelAndView VerOrcamentos() {
		ModelAndView mv = new ModelAndView("verificarorcamentos.html");
		
		if(!Objects.isNull(fantContaOrcamentoRepository.idOrcados())) {
			mv.addObject("idorcados", fantContaOrcamentoRepository.idOrcados());
			mv.addObject("servicoorcados", new FantVisualizarOrcamentos());
			mv.addObject("totalizador",new FantTotalizadorOrcamentos());
			mv.addObject("atribuicao",0);
		}else {
			mv.addObject("idorcados", null);
			mv.addObject("servicoorcados", new FantVisualizarOrcamentos());
			mv.addObject("totalizador",new FantTotalizadorOrcamentos());
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
		}else {
			ordemServico=(long) 0;
			mv.addObject("idorcados", null);
			mv.addObject("servicoorcados", new FantVisualizarOrcamentos());
			mv.addObject("totalizador",new FantTotalizadorOrcamentos());
			mv.addObject("atribuicao",0);
		}
		
		
		return mv;
	}
	@RequestMapping("/atribuir/{id_orc}")
	public ModelAndView AtribuirServico(@PathVariable("id_orc") Long id_orc) {
		ModelAndView mv = new ModelAndView("verificarorcamentos.html");
		System.out.println("Vim dentro de atribuir serviço com id_orc "+id_orc);
		if(id_orc!=null) {
			Orcamentos orc = new Orcamentos();
			Orcamentos orcGrava = new Orcamentos();
			orc = orcamentosReposoitory.selecionaOrcamentoPorId(id_orc);
			if(!Objects.equals(orc.getOrc_atribuido(),"S") ) {
				System.out.println("**Dentro de atribuir !=S  com id_orc "+id_orc+" com get atribuido: "+orc.getOrc_atribuido());	
			DefeitosRelatados drGrava = new DefeitosRelatados();
			DefeitosRelatados drRecebe = new DefeitosRelatados();
			drRecebe = defeitosRelatadosRepository.defeitoRelatado(orc.getFk_defeitos_relatados());
			
			drGrava.setAtribuido(orc.getFk_pessoa_juridica());
			drGrava.setDescricao(drRecebe.getDescricao());
			drGrava.setFkOrdemServico(drRecebe.getFkOrdemServico());
			drGrava.setIdDefeitos(orc.getFk_defeitos_relatados());
			drGrava.setQtdRelatado(drRecebe.getQtdRelatado());
			drGrava.setIdDefeitos(id_orc);
			
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
			
			orcamentosReposoitory.UpdateOrcamentosDesfaz(orc.getFk_defeitos_relatados());
			defeitosRelatadosRepository.save(drGrava);
			orcamentosReposoitory.save(orcGrava);
			}else {
				System.out.println("**Dentro de atribuir ==S  com id_orc "+id_orc);
				DefeitosRelatados drGrava = new DefeitosRelatados();
				DefeitosRelatados drRecebe = new DefeitosRelatados();
				drRecebe = defeitosRelatadosRepository.defeitoRelatado(orc.getFk_defeitos_relatados());
				
				drGrava.setAtribuido(null);
				drGrava.setDescricao(drRecebe.getDescricao());
				drGrava.setFkOrdemServico(drRecebe.getFkOrdemServico());
				drGrava.setIdDefeitos(orc.getFk_defeitos_relatados());
				drGrava.setQtdRelatado(drRecebe.getQtdRelatado());
				drGrava.setIdDefeitos(id_orc);
				
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
				
				orcamentosReposoitory.UpdateOrcamentosDesfaz(orc.getFk_defeitos_relatados());
				defeitosRelatadosRepository.save(drGrava);
				orcamentosReposoitory.save(orcGrava);
				
			}
			mv.addObject("idorcados", fantContaOrcamentoRepository.idOrcados());
			mv.addObject("servicoorcados", fantVisualizarOrcamentosRepository.visualizarOrcamentos(ordemServico));
			mv.addObject("totalizador",fantTotalizadorOrcamentosRepository.totalizadorOrcamentos(ordemServico));
		
		}else {
			mv.addObject("idorcados", null);
			mv.addObject("servicoorcados", new FantVisualizarOrcamentos());
			mv.addObject("totalizador",new FantTotalizadorOrcamentos());
			mv.addObject("atribuicao",0);
		}
		
		
		return mv;
	}

}
