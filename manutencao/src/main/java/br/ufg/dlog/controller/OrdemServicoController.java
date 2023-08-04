package br.ufg.dlog.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.dlog.repository.DefeitosRelatadosRepository;
import br.ufg.dlog.repository.FantOrcamentoRepository;
import br.ufg.dlog.repository.FatasmaVeiculoRepository;
import br.ufg.dlog.repository.OrcamentosRepository;
import br.ufg.dlog.repository.OrdemServicoRepository;
import br.ufg.dlog.repository.PessoaJuridicaRepository;
import br.ufg.dlog.repository.UsuarioRepository;
import br.ufg.dlog.repository.UsuariosEmpresasRepository;
import br.ufg.dlog.repository.VeiculosRepository;
import br.ufg.dlog.classes.DefeitosRelatados;
import br.ufg.dlog.classes.Orcamentos;
import br.ufg.dlog.classes.OrdemServico;
import br.ufg.dlog.classes.Usuario;
import br.ufg.dlog.classes.UsuariosEmpresas;
import br.ufg.dlog.classes.pessoa_juridica;

@Controller
public class OrdemServicoController {
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
	private VeiculosRepository veiculosRepository;
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
	
	
	@RequestMapping("/ordermservico")
	public ModelAndView OrdemServico() {
		ModelAndView mv = new ModelAndView("/ordemdeservico.html");
		if(!Objects.isNull(orcamentosRepository.qtdOrcamentosAbertos())) {
			 QtdOrcAbertos = orcamentosRepository.qtdOrcamentosAbertos();
			}else {QtdOrcAbertos=0;}
		mv.addObject("os",ordemServicoRepository.findAll());
		mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());
		mv.addObject("msg", "Bem vindo ao formulário de ordem de serviço, existe(m) "+QtdOrcAbertos+" orçamento(s) enviado(s)");
		mv.addObject("osid", new OrdemServico());
		mv.addObject("itens", new DefeitosRelatados());
		mv.addObject("idos", IdServico);
		mv.addObject("item", new DefeitosRelatados());
		return mv;
		
	}
	@RequestMapping(method = RequestMethod.POST, value = "/salvarordem")
	public ModelAndView SalvarOrdem(@RequestParam("veiculo")String veiculo) throws ParseException {
		Authentication autenticar = SecurityContextHolder.getContext().getAuthentication();
		String usuarioAutenticado = autenticar.getName();
		Long usuarioId = usuarioRepository.buscaIdLogin(usuarioAutenticado);
		Boolean OsAbertas=false;
		
		
		OrdemServico ordemServico = new OrdemServico();
		ModelAndView mv = new ModelAndView("ordemdeservico.html");
		if(ordemServicoRepository.selecionaMaxIdOrdemServico()==null) {
			ordemServico.setId_ordem((long) 1);				
			IdServico = (long) 1;
		}else {
			ordemServico.setId_ordem(ordemServicoRepository.selecionaMaxIdOrdemServico()+1);
			IdServico = ordemServicoRepository.selecionaMaxIdOrdemServico()+1;			
		}
		ordemServico.setCriador_os(usuarioId);
		ordemServico.setVeiculo(veiculo);
		ordemServico.setData_emissao(new Date());
		ordemServico.setPosicao_para_orcamento("S");
		ordemServicoRepository.save(ordemServico);
		if(ordemServicoRepository.ordemServicoAbertas()!=null) {
			OsAbertas=true;
		}
		mv.addObject("idos", IdServico);
		if(OsAbertas==true) {
		mv.addObject("os",ordemServicoRepository.ordensServicosAbertas());
		}else {
			mv.addObject("os",new OrdemServico());
		}
		mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());
		mv.addObject("msg", "Ordem de serviço inserida com sucesso, existe(m) "+QtdOrcAbertos+" orçamento(s) enviado(s)");
		mv.addObject("osid", ordemServicoRepository.verOrdemPorId(IdServico));
		mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(IdServico));
		mv.addObject("item", new DefeitosRelatados());
		return mv;
		
	}
	@RequestMapping(method = RequestMethod.POST, value = "/salvaritens")
	public ModelAndView SalvarItens(@RequestParam("QtdRelatado")Float QtdRelatado,@RequestParam("descricao")String descricao ) throws ParseException {
		ModelAndView mv = new ModelAndView("ordemdeservico.html");
		DefeitosRelatados dr = new DefeitosRelatados();
		Boolean OsAbertas=false;
		if(ordemServicoRepository.ordemServicoAbertas()!=null) {
			OsAbertas=true;
		}
		
		Long Iddescricao=(long) 1;
		if (defeitosRelatadosRepository.maxId()==null) {
			 Iddescricao= (long) 1;
		}else {
			Iddescricao = defeitosRelatadosRepository.maxId()+1;
		}
		
		if(!Objects.isNull(descricao)&&QtdRelatado!=null&&descricao!=null) {
			
			if(IdServico!=null&& IdServico!=0) {
				dr.setIdDefeitos(Iddescricao);
				dr.setFkOrdemServico(IdServico);
				dr.setQtdRelatado(QtdRelatado);  //(QtdRelatado));
				dr.setDescricao(descricao);
				defeitosRelatadosRepository.save(dr);
				
				mv.addObject("msg", "Descrição inserida com sucesso!");		
				mv.addObject("os",ordemServicoRepository.findAll());
				/*if(OsAbertas==true) {
					mv.addObject("os", ordemServicoRepository.ordemServicoAbertas());
				}else {
				mv.addObject("os",new OrdemServico());
				}*/
				
				mv.addObject("osid", ordemServicoRepository.verOrdemPorId(IdServico));
				mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());								
				mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(IdServico));
				mv.addObject("idos", IdServico);
				mv.addObject("item", defeitosRelatadosRepository.findById(Iddescricao));
			}else {
				mv.addObject("msg", "Ordem de serviço não foi identificada!");				
				mv.addObject("os",new OrdemServico());
				mv.addObject("osid", new OrdemServico());
				mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());								
				mv.addObject("itens", new DefeitosRelatados());
				mv.addObject("idos", IdServico);
				mv.addObject("item", new DefeitosRelatados());
			}
			
		}else {
			mv.addObject("msg", "Quantidade ou descição não inserida!");				
			mv.addObject("os",new OrdemServico());
			mv.addObject("osid", ordemServicoRepository.verOrdemPorId(IdServico));
			mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());								
			mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(IdServico));
			mv.addObject("idos", IdServico);
			mv.addObject("item", new DefeitosRelatados());
		}
		return mv;
	}
	@GetMapping("**/pesquisaros")
	public ModelAndView PesquisarOs(@RequestParam("id_ordem") Long id_ordem) {
		ModelAndView mv = new ModelAndView("ordemdeservico.html");
		
		
		if(id_ordem!=null ) {
		IdServico = (id_ordem);
		
		mv.addObject("msg", "Pesquisa realizada com sucesso!");				
		mv.addObject("os",ordemServicoRepository.findAll());
		mv.addObject("osid", ordemServicoRepository.verOrdemPorId(id_ordem));
		mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());								
		mv.addObject("itens", defeitosRelatadosRepository.itensRelatadosAtivos(IdServico));
		mv.addObject("idos", IdServico);
		mv.addObject("item", defeitosRelatadosRepository.findById(id_ordem));
		}else {
			mv.addObject("msg", "Não encontrado resultado para pesquisa!");				
			mv.addObject("os",ordemServicoRepository.findAll());
			mv.addObject("osid", new OrdemServico());
			mv.addObject("veiculos",veiculosRepository.findVeiculosAtivos());								
			mv.addObject("itens", new DefeitosRelatados());
			mv.addObject("idos", IdServico);
			mv.addObject("item", new DefeitosRelatados());
		}
		
		return mv;
		
	}
    	
    	
    	
	
	


}
