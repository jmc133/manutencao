package br.ufg.dlog.controller;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.dlog.classes.FatasmaVeiculo;
import br.ufg.dlog.classes.OrdemServico;
import br.ufg.dlog.classes.RecebimentoVeiculosDeOS;
import br.ufg.dlog.classes.UsuariosEmpresas;
import br.ufg.dlog.classes.pessoa_juridica;
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
	Long id;
	
	
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
    	ModelAndView mv = new ModelAndView("receberveiculooficina.html");
    	
    	if(!Objects.isNull(os)) {
    	mv.addObject("receber", new RecebimentoVeiculosDeOS());
    	mv.addObject("veiculos", fantasmaVeiculoRepository.buscaVeiculoPorPatrimonio(os.getVeiculo()));
    	mv.addObject("msg","Preencha os campos com os dados de recebimento para OS "+os.getId_ordem());
    	}else {
    		mv.addObject("receber", new RecebimentoVeiculosDeOS());
        	mv.addObject("veiculos", new FatasmaVeiculo());
        	mv.addObject("msg","Não foi encontrada a OS, verifique e começe novamente");
    	}
    	return mv;
	}
	@RequestMapping("/salvarentrada")
	public ModelAndView SalvarEntrada(@RequestParam("kmentrada")Long kmentrada,@RequestParam("posicaotanquerecebido")String posicaotanquerecebido,
			@RequestParam("posicaotanqueentregue")String posicaotanqueentregue, @RequestParam("verificar")Boolean verificar,
			@RequestParam("responsavelreceber")String responsavelreceber) {
		Authentication autenticar = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mv = new ModelAndView("receberveiculooficina.html");
		if (!Objects.isNull(os)) {
		Double MaxKm=(double) 0;
		Boolean testekm = false;
		String veiculo = os.getVeiculo();
		System.out.println("*** veiculo está recebendo "+veiculo+" *** "+ os.getVeiculo()+" --- o verificar está: "+verificar+" posição do tanque está: "+posicaotanquerecebido+" posição de entrega está: "+posicaotanqueentregue);				
		MaxKm = movimentoRepository.maximoKm(veiculo);
		testekm = false;
	
		System.out.println("O km máximo é: "+MaxKm+" Responsável: "+responsavelreceber+" autenticar "+autenticar.getName());
		if(!Objects.isNull(kmentrada)) {
			if(kmentrada>MaxKm) {
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
		
		System.out.println("O km máximo é: "+MaxKm+" Responsável: "+responsavelreceber+" PJ: "+pj.getCd_pessoa_jur()+" via UE: "+ue.getVinculo()+" usuario "+usuarioAutenticado);
		
    	
    	if(recebimentosVeiculosOsRepository.verIdmaximo()!=null) {
    		id= recebimentosVeiculosOsRepository.verIdmaximo()+1;
    	}else {
    		id=(long) 1;
    	}
    	if(verificar.TRUE) {
    	if(testekm==true) {
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
