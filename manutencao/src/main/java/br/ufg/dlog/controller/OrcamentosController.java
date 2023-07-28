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
import br.ufg.dlog.classes.OrdemServico;
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
	@RequestMapping(value = {"{/atribuir/{pj}","/{atribuir/{fk_defeitos}"})
	public ModelAndView AtribuirServico(@PathVariable("pj") Long pj, @PathVariable("fk_defeitos") Long fk_defeitos) {
		ModelAndView mv = new ModelAndView("verificarorcamentos.html");
		if(pj!=null&&fk_defeitos!=null) {
			DefeitosRelatados drGrava = new DefeitosRelatados();
			DefeitosRelatados drRecebe = new DefeitosRelatados();
			drRecebe = defeitosRelatadosRepository.defeitoRelatado(fk_defeitos);
			
			drGrava.setAtribuido(pj);
			drGrava.setDescricao(drRecebe.getDescricao());
			drGrava.setFkOrdemServico(drRecebe.getFkOrdemServico());
			drGrava.setIdDefeitos(fk_defeitos);
			drGrava.setQtdRelatado(drRecebe.getQtdRelatado());
			
			defeitosRelatadosRepository.save(drGrava);
			
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
