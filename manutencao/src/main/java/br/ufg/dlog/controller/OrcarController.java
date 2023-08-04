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
	    mv.addObject("msg", "Bem vindo! Selecione uma ordem de serviço "+ue.getNome());
	    mv.addObject("editar", null);
	    mv.addObject("orcados", null);
	    mv.addObject("qtd",0);
	    mv.addObject("novitem", 0);
	    mv.addObject("idordem", null);
	    mv.addObject("item", new DefeitosRelatados());
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
	    mv.addObject("msg", "← Ordem de serviço selecionada, agora escolha um item "+ue.getNome());
	    mv.addObject("editar", null);
	    mv.addObject("orcados", null);
	    mv.addObject("qtd",0);
	    mv.addObject("novitem", 0);
	    mv.addObject("idordem", idOrdem);
	   
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
	    mv.addObject("msg", "Item escolhido para orçar, agora insira o valor unitário "+ue.getNome());
	    mv.addObject("editar", "1");
	    mv.addObject("qtd",dr.getQtdRelatado());
	    mv.addObject("orcados", fantOrcamentoRepository.orcamentosPorFornecedor(idOrdem, pj.getCd_pessoa_jur())) ;
	    mv.addObject("novitem", 0);
	    mv.addObject("idordem", idOrdem);
	   
	  
	return mv;
}
	@RequestMapping("/salvarorcamento")
	public ModelAndView SalvarOrcamento(@RequestParam("valor_unitario")Double valor_unitario) {
    	ModelAndView mv = new ModelAndView("orcar.html");
    	DefeitosRelatados atribuido = new DefeitosRelatados();
    	if(idDefeitos!=null) {
    		atribuido = defeitosRelatadosRepository.defeitoRelatado(idDefeitos);
    	}
    	
    	if (Objects.isNull(atribuido.getAtribuido())) {
    	if(!Objects.isNull(valor_unitario)&&valor_unitario>0) {
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
    		mv.addObject("novitem", 0);
    		mv.addObject("idordem", idOrdem);
    		
    		
    	}else {
    		mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
		    mv.addObject("ordem", ordemServicoRepository.findById(idOrdem));
		    mv.addObject("osaberta", ordemServicoRepository.ordemServicoAbertas());
		    mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(idOrdem));
		    mv.addObject("item", defeitosRelatadosRepository.defeitoRelatado(idDefeitos));
		    mv.addObject("pj", pj);
		    mv.addObject("orcamentos", new Orcamentos());
		    mv.addObject("msg", "Não foi encontrado o valor orçado ou foi inserido valor =0 "+ue.getNome());
		    mv.addObject("editar", "1");
		    mv.addObject("qtd",dr.getQtdRelatado());
		    mv.addObject("orcados", fantOrcamentoRepository.orcamentosPorFornecedor(idOrdem, pj.getCd_pessoa_jur()));
		    mv.addObject("novitem", 0);
		    mv.addObject("idordem", idOrdem);
		 
    		
    	}
    	}else {
    		
    	    mv.addObject("msg", "Este item já foi atribuido, não podendo mais ser orçado "+ue.getNome());
    	    mv.addObject("orcamentos", new Orcamentos());
    	}
    	mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
	    mv.addObject("ordem", ordemServicoRepository.findById(idOrdem));
	    mv.addObject("osaberta", ordemServicoRepository.ordemServicoAbertas());
	    mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(idOrdem));
	    mv.addObject("item", defeitosRelatadosRepository.defeitoRelatado(idDefeitos));
	    mv.addObject("pj", pj);
	    mv.addObject("qtd",0);
	    mv.addObject("orcados", fantOrcamentoRepository.orcamentosPorFornecedor(idOrdem, pj.getCd_pessoa_jur()));
	    mv.addObject("novitem", 0);
	    mv.addObject("idordem", idOrdem);
	   
	    
	return mv;
}
	@RequestMapping("/adicionaritens")
	public ModelAndView AdicionarItens(@RequestParam("QtdRelatado")Float QtdRelatado,@RequestParam("descricao")String descricao) {
		ModelAndView mv = new ModelAndView("orcar.html");
		descricao= descricao.trim();
		Long Iddescricao;
		DefeitosRelatados dr = new DefeitosRelatados();
		Boolean OsAbertas=false;
		if(ordemServicoRepository.ordemServicoAbertas()!=null) {
			OsAbertas=true;
		}
		if (defeitosRelatadosRepository.maxId()==null) {
			 Iddescricao= (long) 1;
		}else {
			Iddescricao = defeitosRelatadosRepository.maxId()+1;
		}
		if(!Objects.isNull(descricao)&&QtdRelatado!=null&&!Objects.equals(descricao, "")) {
			if(idOrdem!=null&& idOrdem!=0) {
				dr.setIdDefeitos(Iddescricao);
				dr.setFkOrdemServico(idOrdem);
				dr.setQtdRelatado(QtdRelatado);  //(QtdRelatado));
				dr.setDescricao(descricao);
				defeitosRelatadosRepository.save(dr);
				mv.addObject("msg", "Item inserido com sucesso "+ue.getNome());
			}else {
				mv.addObject("msg", "Não foi possível inserir não há ordem de serviço selecionada "+ue.getNome());
			}
		}else {
			mv.addObject("msg", "Não foram preenchidos os campos necesários "+ue.getNome());
		}
		
		mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
	    mv.addObject("ordem", ordemServicoRepository.findById(idOrdem));
	    mv.addObject("osaberta", ordemServicoRepository.ordemServicoAbertas());
	    mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(idOrdem));
	    mv.addObject("item", new DefeitosRelatados());
	    mv.addObject("pj", pj);
	    mv.addObject("orcamentos", new Orcamentos());
	    
	    mv.addObject("editar", null);
	    mv.addObject("orcados", null);
	    mv.addObject("qtd",0);
	    mv.addObject("novitem", 1);
	    mv.addObject("idordem", idOrdem);
	    
		
		return mv;
	}
	@RequestMapping("/abriradicionaritens")
	public ModelAndView AbrirAdicionarItens() {
		ModelAndView mv = new ModelAndView("orcar.html");
		mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
	    mv.addObject("ordem", ordemServicoRepository.findById(idOrdem));
	    mv.addObject("osaberta", ordemServicoRepository.ordemServicoAbertas());
	    mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(idOrdem));
	    mv.addObject("item", new DefeitosRelatados());
	    mv.addObject("pj", pj);
	    mv.addObject("orcamentos", new Orcamentos());
	    mv.addObject("msg", "← Ordem de serviço selecionada, agora escolha um item "+ue.getNome());
	    mv.addObject("editar", null);
	    mv.addObject("orcados", null);
	    mv.addObject("qtd",0);
	    mv.addObject("novitem", 1);
	    mv.addObject("idordem", idOrdem);
		
		return mv;
	}

}
