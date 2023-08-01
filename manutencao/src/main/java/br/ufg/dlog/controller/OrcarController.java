package br.ufg.dlog.controller;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.dlog.classes.DefeitosRelatados;
import br.ufg.dlog.classes.Orcamentos;
import br.ufg.dlog.classes.OrdemServico;
import br.ufg.dlog.classes.Usuario;
import br.ufg.dlog.classes.UsuariosEmpresas;
import br.ufg.dlog.classes.pessoa_juridica;
import br.ufg.dlog.repository.DefeitosRelatadosRepository;
import br.ufg.dlog.repository.FantOrcamentoRepository;
import br.ufg.dlog.repository.FatasmaVeiculoRepository;
import br.ufg.dlog.repository.OrcamentosRepository;
import br.ufg.dlog.repository.OrdemServicoRepository;
import br.ufg.dlog.repository.PessoaJuridicaRepository;
import br.ufg.dlog.repository.UsuarioRepository;
import br.ufg.dlog.repository.UsuariosEmpresasRepository;
import br.ufg.dlog.repository.VeiculosRepository;

@Controller
public class OrcarController {
	Long IdServico=(long) 0;
	Long idOrdem;
	Long idDefeitos;
	String usuarioAutenticado;
	int QtdOrcAbertos;
	
	DefeitosRelatados dr = new DefeitosRelatados();
	UsuariosEmpresas ue = new UsuariosEmpresas();
	pessoa_juridica pj = new pessoa_juridica();
	Usuario u = new Usuario();
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	@Autowired 
	private UsuarioRepository usuarioRepository;
	@Autowired
	private DefeitosRelatadosRepository defeitosRelatadosRepository;
	@Autowired
	private FatasmaVeiculoRepository fantasmaVeiculoRepository;
	@Autowired
	private OrcamentosRepository orcamentosRepository;
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	@Autowired
	private UsuariosEmpresasRepository usuarioEmpresaRepository;
	@Autowired
	private FantOrcamentoRepository fantOrcamentoRepository;
	
	@RequestMapping("/orcar")
	public ModelAndView Orcar() {
		Authentication autenticar = SecurityContextHolder.getContext().getAuthentication();
    	ModelAndView mv = new ModelAndView("orcar.html");
    	
    	if(!Objects.isNull(autenticar)) {
      		 usuarioAutenticado = autenticar.getName();
      		}
       	ue = usuarioEmpresaRepository.selecionaUsuarioPorEmail(usuarioAutenticado);
       	pj = pessoaJuridicaRepository.selecionaPessoaJuridicaPorId(ue.getVinculo());
       	u = usuarioRepository.selecionaUsuarioPorLogin(usuarioAutenticado);
       	
       	mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
	    mv.addObject("ordem", new OrdemServico());
	    mv.addObject("osaberta", ordemServicoRepository.ordemServicoAbertas());
	    mv.addObject("itens", new DefeitosRelatados());
	    mv.addObject("item", new DefeitosRelatados());
	    mv.addObject("pj", pj);
	    mv.addObject("orcamentos", new Orcamentos());
	    mv.addObject("msg", "Selecionar ordem de serviço "+ue.getNome());
	    mv.addObject("editar", null);
	    mv.addObject("orcados", null);
	    mv.addObject("qtd",0);
	return mv;
    	
	}
	
	@RequestMapping("/selecionarordem/{id_ordem}")
	public ModelAndView SelecionarOrdem(@PathVariable("id_ordem") Long id_ordem) {
		Authentication autenticar = SecurityContextHolder.getContext().getAuthentication();
    	ModelAndView mv = new ModelAndView("orcar.html");
    	
    	if(!Objects.isNull(autenticar)) {
   		 usuarioAutenticado = autenticar.getName();
   		}
    	ue = usuarioEmpresaRepository.selecionaUsuarioPorEmail(usuarioAutenticado);
    	pj = pessoaJuridicaRepository.selecionaPessoaJuridicaPorId(ue.getVinculo());
    	u = usuarioRepository.selecionaUsuarioPorLogin(usuarioAutenticado);
    	idOrdem=id_ordem;
    	mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
	    mv.addObject("ordem", ordemServicoRepository.findById(id_ordem));
	    mv.addObject("osaberta", ordemServicoRepository.ordemServicoAbertas());
	    mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(id_ordem));
	    mv.addObject("item", new DefeitosRelatados());
	    mv.addObject("pj", pj);
	    mv.addObject("orcamentos", new Orcamentos());
	    mv.addObject("msg", "Selecionar ordem de serviço "+ue.getNome());
	    mv.addObject("editar", null);
	    mv.addObject("orcados", null);
	    mv.addObject("qtd",0);
	return mv;
}
	@RequestMapping("/editaritensordem/{id_defeitos}")
	public ModelAndView EditarItensOrdem(@PathVariable("id_defeitos") Long id_defeitos) {
    	ModelAndView mv = new ModelAndView("orcar.html");
    	
    	
    				
    	idDefeitos=id_defeitos;
    	dr = defeitosRelatadosRepository.defeitoRelatado(id_defeitos);
    	mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
	    mv.addObject("ordem", ordemServicoRepository.findById(idOrdem));
	    mv.addObject("osaberta", ordemServicoRepository.ordemServicoAbertas());
	    mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(idOrdem));
	    mv.addObject("item", defeitosRelatadosRepository.defeitoRelatado(id_defeitos));
	    mv.addObject("pj", pj);
	    mv.addObject("orcamentos", new Orcamentos());
	    mv.addObject("msg", "Escolhendo item para orçar "+ue.getNome());
	    mv.addObject("editar", "1");
	    mv.addObject("qtd",dr.getQtdRelatado());
	    mv.addObject("orcados", fantOrcamentoRepository.orcamentosPorFornecedor(idOrdem, pj.getCd_pessoa_jur())) ;
	  
	return mv;
}
	@RequestMapping("/salvarorcamento")
	public ModelAndView SalvarOrcamento(@RequestParam("valor_unitario")Double valor_unitario,@RequestParam("valor_total")Double valor_total) {
    	ModelAndView mv = new ModelAndView("orcar.html");
    	
    	
    	if(valor_unitario!=null&&valor_unitario>0) {
    		Long id;
    		if (orcamentosRepository.selecionaMaxIdOrcamento()==null) {
    			id=(long) 1;
    		}else {
    		 id = orcamentosRepository.selecionaMaxIdOrcamento()+1;
    		}
    		Orcamentos o = new Orcamentos();
    		o= orcamentosRepository.verificaSeJaFoiOrcado(pj.getCd_pessoa_jur(), idOrdem, idDefeitos);
    		if (Objects.isNull(o)) {
    		Orcamentos orc = new Orcamentos();
    		orc.setData_orcamento(new Date());
    		orc.setFk_defeitos_relatados(idDefeitos);
    		orc.setFk_ordem_servico(idOrdem);
    		orc.setFk_pessoa_juridica(pj.getCd_pessoa_jur());
    		orc.setId_orcamento(id);
    		orc.setResponsavel_orcamento(ue.getId_ue()) ;
    		orc.setValor_total(dr.getQtdRelatado()*valor_unitario);
    		orc.setValor_unitario(valor_unitario);
    		orc.setOrcador(ue.getNome());
    		orcamentosRepository.save(orc);
    		mv.addObject("orcamentos", orcamentosRepository.findById(id));
    		}else {
    			Orcamentos orc = new Orcamentos();
    			orc.setData_orcamento(new Date());
	    		orc.setFk_defeitos_relatados(o.getFk_defeitos_relatados());
	    		orc.setFk_ordem_servico(o.getFk_ordem_servico());
	    		orc.setFk_pessoa_juridica(o.getFk_pessoa_juridica());
	    		orc.setId_orcamento(o.getId_orcamento());
	    		orc.setResponsavel_orcamento(o.getResponsavel_orcamento()) ;
	    		orc.setValor_total(dr.getQtdRelatado()*valor_unitario);
	    		orc.setValor_unitario(valor_unitario);
	    		orc.setOrcador(o.getOrcador());
	    		orcamentosRepository.save(orc);
	    		mv.addObject("orcamentos", orcamentosRepository.findById(o.getId_orcamento()));
    		}
    		
    		
    		
    		
    		mv.addObject("msg", "Item orçado salvo "+ue.getNome());	    		
    		mv.addObject("item", defeitosRelatadosRepository.defeitoRelatado(idDefeitos));
    		mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(idOrdem));
    		mv.addObject("editar", null);
    		mv.addObject("qtd",dr.getQtdRelatado());
    		mv.addObject("orcados",fantOrcamentoRepository.orcamentosPorFornecedor(idOrdem, pj.getCd_pessoa_jur()));
    		
    	}else {
    		mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
		    mv.addObject("ordem", ordemServicoRepository.findById(idOrdem));
		    mv.addObject("osaberta", ordemServicoRepository.ordemServicoAbertas());
		    mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(idOrdem));
		    mv.addObject("item", defeitosRelatadosRepository.defeitoRelatado(idDefeitos));
		    mv.addObject("pj", pj);
		    mv.addObject("orcamentos", new Orcamentos());
		    mv.addObject("msg", "Não foi encontrado o valor orçado "+ue.getNome());
		    mv.addObject("editar", "1");
		    mv.addObject("qtd",dr.getQtdRelatado());
		    mv.addObject("orcados", fantOrcamentoRepository.orcamentosPorFornecedor(idOrdem, pj.getCd_pessoa_jur()));
    		
    	}
    	mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
	    mv.addObject("ordem", ordemServicoRepository.findById(idOrdem));
	    mv.addObject("osaberta", ordemServicoRepository.ordemServicoAbertas());
	    mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(idOrdem));
	    mv.addObject("item", defeitosRelatadosRepository.defeitoRelatado(idDefeitos));
	    mv.addObject("pj", pj);
	    mv.addObject("qtd",0);
	    mv.addObject("orcados", fantOrcamentoRepository.orcamentosPorFornecedor(idOrdem, pj.getCd_pessoa_jur()));
	    
	return mv;
}

}
