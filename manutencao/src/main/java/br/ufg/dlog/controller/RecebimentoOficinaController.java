package br.ufg.dlog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.dlog.classes.DefeitosRelatados;
import br.ufg.dlog.classes.FatasmaVeiculo;
import br.ufg.dlog.classes.OrdemServico;
import br.ufg.dlog.classes.RecebimentoVeiculosDeOS;
import br.ufg.dlog.classes.UsuariosEmpresas;
import br.ufg.dlog.classes.pessoa_juridica;
import br.ufg.dlog.repository.DefeitosRelatadosRepository;
import br.ufg.dlog.repository.FatasmaVeiculoRepository;
import br.ufg.dlog.repository.MovimentoRepository;
import br.ufg.dlog.repository.OrdemServicoRepository;
import br.ufg.dlog.repository.PessoaJuridicaRepository;
import br.ufg.dlog.repository.RecebimentoVeiculosOSRepository;
import br.ufg.dlog.repository.UsuariosEmpresasRepository;

@Controller
public class RecebimentoOficinaController {
	OrdemServico os = new OrdemServico();
	
	UsuariosEmpresas ue = new UsuariosEmpresas();
	pessoa_juridica pj = new pessoa_juridica();
	String usuarioAutenticado;
	List<DefeitosRelatados> dr = new ArrayList<>();
	Long id;
	RecebimentoVeiculosDeOS RvOs = new RecebimentoVeiculosDeOS();
	
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	@Autowired
	private FatasmaVeiculoRepository fantasmaVeiculoRepository;
	@Autowired
	private UsuariosEmpresasRepository usuarioEmpresaRepository;
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	@Autowired
	private RecebimentoVeiculosOSRepository recebimentosVeiculosOsRepository;
	@Autowired
	private MovimentoRepository movimentoRepository;
	@Autowired
	private DefeitosRelatadosRepository defeitosRelatadosRepository;
	
	
	
	
	@RequestMapping("/receberveiculo")
	public ModelAndView ReceberVeiculos() {
		Authentication autenticar = SecurityContextHolder.getContext().getAuthentication();	
    	ModelAndView mv = new ModelAndView("receberveiculooficina.html");
    	mv.addObject("receber", new RecebimentoVeiculosDeOS());
    	mv.addObject("veiculo", new FatasmaVeiculo());
    	mv.addObject("msg","Escolha a OS do veículos que está recebendo");
    	return mv;
	}
	@RequestMapping("/receberveiculoos")
	public ModelAndView ReceberVeiculosOs(@RequestParam("id_ordem")Long id_ordem) {
		Authentication autenticar = SecurityContextHolder.getContext().getAuthentication();	
		os = ordemServicoRepository.verOrdemPorId(id_ordem);
		 usuarioAutenticado = autenticar.getName();
	 	 ue = usuarioEmpresaRepository.selecionaUsuarioPorEmail(usuarioAutenticado);
		 pj = pessoaJuridicaRepository.selecionaPessoaJuridicaPorId(ue.getVinculo());
		 RvOs = recebimentosVeiculosOsRepository.selecionaPorOSePJ(id_ordem, pj.getCd_pessoa_jur());
		 Boolean recebimentoExistente = false;
		 
    	ModelAndView mv = new ModelAndView("receberveiculooficina.html");
    	
    	if(!Objects.isNull(RvOs)) {
    		recebimentoExistente= false;
    	}else {
    		recebimentoExistente=true;
    	}
    		
    	
    	
    	if(!Objects.isNull(os)) {
    		if(recebimentoExistente==true) {
    	     mv.addObject("receber", new RecebimentoVeiculosDeOS());
    	     mv.addObject("veiculos", fantasmaVeiculoRepository.buscaVeiculoPorPatrimonio(os.getVeiculo()));
    	     mv.addObject("msg","Preencha os campos com os dados de recebimento para OS "+os.getId_ordem());
    		}else {
    			mv.addObject("receber", recebimentosVeiculosOsRepository.selecionaPorId(RvOs.getIdrecebimentos()));
       	        mv.addObject("veiculos", fantasmaVeiculoRepository.buscaVeiculoPorPatrimonio(os.getVeiculo()));
       	        mv.addObject("msg","Preencha os campos com os dados para entrega do veiculos "+os.getId_ordem());
    		}
    	}else {
    		mv.addObject("receber", new RecebimentoVeiculosDeOS());
        	mv.addObject("veiculos", new FatasmaVeiculo());
        	mv.addObject("msg","Não foi encontrada a OS, verifique e começe novamente");
    	}
    	return mv;
	}
	@RequestMapping("/salvarentrada")
	public ModelAndView SalvarEntrada(@RequestParam("kmentrada")Long kmentrada,@RequestParam("posicaotanquerecebido")String posicaotanquerecebido,
			@RequestParam("posicaotanqueentregue")String posicaotanqueentregue, @RequestParam("verificar")Boolean verificar,@RequestParam("responsavelliberar")String responsavelliberar,
			@RequestParam("responsavelreceber")String responsavelreceber,@RequestParam("kmentrega")String kmentrega) {
		Authentication autenticar = SecurityContextHolder.getContext().getAuthentication();
		Boolean recebimentoExistente = false;
		
		
		if(Objects.equals(kmentrega, "")||Objects.isNull(kmentrega)) {
			kmentrega="0";
			
		}
		
		if(!Objects.isNull(RvOs)) {
    		recebimentoExistente= false;
    	}else {
    		recebimentoExistente=true;
    	}
		
		ModelAndView mv = new ModelAndView("receberveiculooficina.html");
		if (!Objects.isNull(os)) {
		Double MaxKm=(double) 0;
		Boolean testekm = false;
		String veiculo = os.getVeiculo();
						
		MaxKm = movimentoRepository.maximoKm(veiculo);
		testekm = false;
	
		
		if(!Objects.isNull(kmentrada)) {
			if(kmentrada>MaxKm||Long.parseLong(kmentrega) >MaxKm) {
				testekm=true;
			}
		}
		if(!Objects.isNull(autenticar)) {
	 		 usuarioAutenticado = autenticar.getName();
	 		ue = usuarioEmpresaRepository.selecionaUsuarioPorEmail(usuarioAutenticado);
		   	pj = pessoaJuridicaRepository.selecionaPessoaJuridicaPorId(ue.getVinculo());
	 		}else {
	 			mv.addObject("receber", new RecebimentoVeiculosDeOS());
	        	mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
	        	mv.addObject("msg","Não foi encontrado usuário autenticado para executar a operação");
	 		}
		
		
		
    	
    	if(recebimentosVeiculosOsRepository.verIdmaximo()!=null) {
    		id= recebimentosVeiculosOsRepository.verIdmaximo()+1;
    	}else {
    		id=(long) 1;
    	}
    	if(verificar.TRUE) {
    		dr = defeitosRelatadosRepository.verificarDefeitosPorOrdemServicos(os.getId_ordem());
    	if(testekm==true&&!Objects.isNull(dr)) {
    		if(recebimentoExistente==true) {
    			
    	   RecebimentoVeiculosDeOS rOs = new RecebimentoVeiculosDeOS();
    	   rOs.setConfirmaleitura(true);
    	   rOs.setDataentrada(new Date());
    	   rOs.setFkordemservico(os.getId_ordem());
    	   rOs.setFkpessoajuridica(pj.getCd_pessoa_jur());
    	   rOs.setKmentrada(kmentrada);
    	   rOs.setPosicaotanquerecebido(posicaotanquerecebido);
    	   rOs.setResponsavelreceber(responsavelreceber);
    	   rOs.setIdrecebimentos(id);
    	   rOs.setVeiculo(os.getVeiculo());
    	   recebimentosVeiculosOsRepository.save(rOs);
    	   
    	   mv.addObject("receber", recebimentosVeiculosOsRepository.selecionaPorId(id));
    	   mv.addObject("veiculos", fantasmaVeiculoRepository.buscaVeiculoPorPatrimonio(veiculo));
    	   mv.addObject("msg","Veiculo recebido com sucesso, OS:  "+os.getId_ordem());
    		}else if(Long.parseLong(kmentrega)>=RvOs.getKmentrada()) {
    			    			
    			RecebimentoVeiculosDeOS rOs = new RecebimentoVeiculosDeOS();
    			rOs.setDataentrega(new Date());
    			rOs.setKmentrega(Long.parseLong(kmentrega));
    			rOs.setPosicaotanqueentregue(posicaotanqueentregue);
    			rOs.setResponsavelliberar(responsavelliberar);
    			rOs.setIdrecebimentos(RvOs.getIdrecebimentos());
    			rOs.setConfirmaleitura(true);
    			rOs.setDataentrada(RvOs.getDataentrada());
    			rOs.setFkordemservico(RvOs.getFkordemservico());
    			rOs.setFkpessoajuridica(RvOs.getFkpessoajuridica());
    			rOs.setKmentrada(RvOs.getKmentrada());
    			rOs.setPosicaotanquerecebido(RvOs.getPosicaotanquerecebido());
    			rOs.setResponsavelreceber(RvOs.getResponsavelreceber());
    			rOs.setVeiculo(os.getVeiculo());
    			recebimentosVeiculosOsRepository.save(rOs);
    			
    		     mv.addObject("receber", recebimentosVeiculosOsRepository.selecionaPorId(RvOs.getIdrecebimentos()));
    	    	 mv.addObject("veiculos", fantasmaVeiculoRepository.buscaVeiculoPorPatrimonio(veiculo));
    	    	 mv.addObject("msg","Veiculo entregue com sucesso, OS:  "+os.getId_ordem());
    		}else {
    		    	mv.addObject("receber", recebimentosVeiculosOsRepository.selecionaPorId(RvOs.getIdrecebimentos()));
   	    	        mv.addObject("veiculos", fantasmaVeiculoRepository.buscaVeiculoPorPatrimonio(veiculo));
   	    	        mv.addObject("msg","Quilometragem  de entraga incorreta, verifique e salve novamente. OS Nº:  "+os.getId_ordem());
    		}
    	}else {
    		mv.addObject("receber", new RecebimentoVeiculosDeOS());
        	mv.addObject("veiculos", new FatasmaVeiculo());
        	mv.addObject("msg","Quilometro inserido incorreto, verifique e começe novamente");
    	}
    	}else {
    	    	mv.addObject("receber", new RecebimentoVeiculosDeOS());
        	    mv.addObject("veiculos", fantasmaVeiculoRepository.buscaPlacaPtrimonio());
        	    mv.addObject("msg","Verifique o termo de referencia e confirme, caso não esteja de acordo, não receba o veículo");
    	}
		}else {
			mv.addObject("receber", new RecebimentoVeiculosDeOS());
        	mv.addObject("veiculos", new FatasmaVeiculo());
        	mv.addObject("msg","Não foi encontrado a OS para o recebimentos do veículo ");
		}
    	return mv;
	}

}
