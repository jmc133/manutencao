package br.ufg.dlog.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.dlog.classes.Aquisicao;
import br.ufg.dlog.classes.Cidade;
import br.ufg.dlog.classes.Combustivel;
import br.ufg.dlog.classes.Cor;
import br.ufg.dlog.classes.Especie;

import br.ufg.dlog.classes.Grupo;
import br.ufg.dlog.classes.Impedimento;
import br.ufg.dlog.classes.Lotacao;
import br.ufg.dlog.classes.Marca;
import br.ufg.dlog.classes.Modelo;

import br.ufg.dlog.classes.Situacao;
import br.ufg.dlog.classes.Tipo;
import br.ufg.dlog.classes.Veiculos;
import br.ufg.dlog.repository.AquisicaoRepository;
import br.ufg.dlog.repository.CidadeRepository;
import br.ufg.dlog.repository.CombustivelRepository;
import br.ufg.dlog.repository.CorRepository;
import br.ufg.dlog.repository.EspecieRepository;

import br.ufg.dlog.repository.FatasmaVeiculoRepository;
import br.ufg.dlog.repository.GrupoRepository;
import br.ufg.dlog.repository.ImpedimentoRepository;
import br.ufg.dlog.repository.LotacaoRepository;
import br.ufg.dlog.repository.MarcaRepository;
import br.ufg.dlog.repository.ModeloRepository;
import br.ufg.dlog.repository.SituacaoRepository;
import br.ufg.dlog.repository.TipoRepository;
import br.ufg.dlog.repository.VeiculosRepository;

@Controller
public class VeiculoControler {
	String patrimonioClass;
	int insercao=0;
	@Autowired
	private VeiculosRepository veiculoRepository;
	@Autowired
	private LotacaoRepository lotacaoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private AquisicaoRepository aquisicaoRepository;
	@Autowired
	private MarcaRepository marcaRepository;
	@Autowired
	private ModeloRepository modeloRepository;
	@Autowired
	private TipoRepository tipoRepository;
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private CorRepository corRepository;
	@Autowired
	private SituacaoRepository situacaoRepository;
	@Autowired
	private CombustivelRepository combustivelRepository;
	@Autowired
	private EspecieRepository especieRepository;
	@Autowired
	private FatasmaVeiculoRepository fantasmaVeiculoRepository;
	@Autowired
	private ImpedimentoRepository impedimentoRepository;
	
	@GetMapping("/veiculo")
	public ModelAndView iniciarVeiculo() {
		insercao=0;
		ModelAndView mv = new ModelAndView("veiculo/veiculo.html");
		mv.addObject("veic",new Veiculos());
		mv.addObject("cid",new Cidade());
		mv.addObject("aquisicao",new Aquisicao());
		mv.addObject("marca", new Marca());
		mv.addObject("modelo", new Modelo());
		mv.addObject("tipo",new Tipo());
		mv.addObject("grupo", new Grupo());
		mv.addObject("cor", new Cor());
		mv.addObject("situacao", new Situacao());
		mv.addObject("combustivel",new Combustivel());
		mv.addObject("lotacao", new Lotacao());
		mv.addObject("especie",new Especie());
		mv.addObject("insercaoVeic",new Veiculos());
		mv.addObject("selecao",fantasmaVeiculoRepository.buscaPlacaPtrimonio());
		mv.addObject("btnsalvar",insercao);
		mv.addObject("impedimento", new Impedimento());
		
		return mv;
		
	}
	
	@GetMapping("/umveiculo")
	public ModelAndView pesquisarUmVeiculo(@RequestParam ("pesquisa")String pesquisa) {
		  insercao=0;
		
		ModelAndView mv = new ModelAndView("veiculo/veiculo.html");
		if (pesquisa!=null && !pesquisa.isEmpty()) {
			patrimonioClass=pesquisa;
			mv.addObject("veic", veiculoRepository.pesquisaPorPatrimonio(pesquisa));
			mv.addObject("lotacao", lotacaoRepository.findAll());
			mv.addObject("cid", cidadeRepository.cidadesOrdenadasPorNome());
			mv.addObject("aquisicao", aquisicaoRepository.findAll());
			mv.addObject("marca", marcaRepository.findAll());
			mv.addObject("modelo", modeloRepository.findAll());
			mv.addObject("tipo", tipoRepository.findAll());
			mv.addObject("grupo",  grupoRepository.findAll());
			mv.addObject("cor", corRepository.findAll());
			mv.addObject("situacao", situacaoRepository.findAll());
			mv.addObject("combustivel", combustivelRepository.findAll());
			mv.addObject("especie", especieRepository.findAll());
			mv.addObject("msg", "Busca realizada com sucesso");
			mv.addObject("insercaoVeic",new Veiculos());
			mv.addObject("selecao",fantasmaVeiculoRepository.buscaPlacaPtrimonio());
			mv.addObject("impedimento", impedimentoRepository.findAll());
			mv.addObject("btnsalvar",insercao);
		}else {
			mv.addObject("msg", "Busca nao encontrada, digite novamente com atencao");
			mv.addObject("veic",new Veiculos());
			mv.addObject("cid",new Cidade());
			mv.addObject("aquisicao",new Aquisicao());
			mv.addObject("marca", new Marca());
			mv.addObject("modelo", new Modelo());
			mv.addObject("tipo",new Tipo());
			mv.addObject("grupo", new Grupo());
			mv.addObject("cor", new Cor());
			mv.addObject("situacao",new Situacao());
			mv.addObject("combustivel",new Combustivel());
			mv.addObject("lotacao", new Lotacao());
			mv.addObject("especie",new Especie());
			mv.addObject("insercaoVeic",new Veiculos());
			mv.addObject("selecao",fantasmaVeiculoRepository.buscaPlacaPtrimonio());
			mv.addObject("impedimento", new Impedimento());
			mv.addObject("btnsalvar",insercao);
			
		}
		return mv;
			
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarveiculo", consumes = {"multipart/form-data"})
	public ModelAndView salvarVeiculo(@Valid Veiculos veiculo, final MultipartFile file)throws IOException {
		ModelAndView mv = new ModelAndView("veiculo/veiculo.html");
		//veiculo.setFoto(file.getBytes());
		if (patrimonioClass!=null) {
		veiculoRepository.save(veiculo);
		insercao=0;
		mv.addObject("msg", "Alteracao do veiculo salva");
		mv.addObject("veic", veiculoRepository.pesquisaPorPatrimonio(patrimonioClass));
		mv.addObject("lotacao", lotacaoRepository.findAll());
		mv.addObject("cid", cidadeRepository.cidadesOrdenadasPorNome());
		mv.addObject("aquisicao", aquisicaoRepository.findAll());
		mv.addObject("marca", marcaRepository.findAll());
		mv.addObject("modelo", modeloRepository.findAll());
		mv.addObject("tipo", tipoRepository.findAll());
		mv.addObject("grupo",  grupoRepository.findAll());
		mv.addObject("cor", corRepository.findAll());
		mv.addObject("situacao", situacaoRepository.findAll());
		mv.addObject("combustivel", combustivelRepository.findAll());
		mv.addObject("especie", especieRepository.findAll());
		mv.addObject("selecao",fantasmaVeiculoRepository.buscaPlacaPtrimonio());
		mv.addObject("insercaoVeic",new Veiculos());
		mv.addObject("impedimento", impedimentoRepository.findAll());
		mv.addObject("btnsalvar",insercao);
		}else {
			mv.addObject("msg", "Sem veiculo para alteracao");
			mv.addObject("veic",new Veiculos());
			mv.addObject("cid",new Cidade());
			mv.addObject("aquisicao",new Aquisicao());
			mv.addObject("marca", new Marca());
			mv.addObject("modelo", new Modelo());
			mv.addObject("tipo",new Tipo());
			mv.addObject("grupo", new Grupo());
			mv.addObject("cor", new Cor());
			mv.addObject("situacao",new Situacao());
			mv.addObject("combustivel",new Combustivel());
			mv.addObject("lotacao", new Lotacao());
			mv.addObject("impedimento", new Impedimento());
			mv.addObject("especie",new Especie());
			mv.addObject("insercaoVeic",new Veiculos());
			mv.addObject("selecao",fantasmaVeiculoRepository.buscaPlacaPtrimonio());
			mv.addObject("btnsalvar",insercao);
		}
		return mv;
	}
	@GetMapping("novoveiculo")
	public ModelAndView NovoVeiculo() {
		ModelAndView mv = new ModelAndView("veiculo/veiculo.html");
		insercao=1;
		mv.addObject("lotacao", lotacaoRepository.findAll());
		mv.addObject("cid", cidadeRepository.cidadesOrdenadasPorNome());
		mv.addObject("aquisicao", aquisicaoRepository.findAll());
		mv.addObject("marca", marcaRepository.findAll());
		mv.addObject("modelo", modeloRepository.findAll());
		mv.addObject("tipo", tipoRepository.findAll());
		mv.addObject("grupo",  grupoRepository.findAll());
		mv.addObject("cor", corRepository.findAll());
		mv.addObject("situacao", situacaoRepository.findAll());
		mv.addObject("combustivel", combustivelRepository.findAll());
		mv.addObject("especie", especieRepository.findAll());
		mv.addObject("insercaoVeic",new Veiculos());
		mv.addObject("veic",new Veiculos());
		mv.addObject("selecao",fantasmaVeiculoRepository.buscaPlacaPtrimonio());
		mv.addObject("msg", "Sem veiculo para inserir");
		mv.addObject("impedimento", impedimentoRepository.findAll());
		mv.addObject("btnsalvar",insercao);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/novosalvar" )
	public ModelAndView salvarNovoVeiculo(@Valid Veiculos veiculo)throws IOException {
		ModelAndView mv = new ModelAndView("veiculo/veiculo.html");
		
		patrimonioClass = veiculo.getPatrimonio();
		if (veiculo.getPatrimonio()!=null && !veiculo.getPatrimonio().isEmpty()) {
			veiculoRepository.save(veiculo);
			insercao=0;
			mv.addObject("lotacao", lotacaoRepository.findAll());
			mv.addObject("cid", cidadeRepository.cidadesOrdenadasPorNome());
			mv.addObject("aquisicao", aquisicaoRepository.findAll());
			mv.addObject("marca", marcaRepository.findAll());
			mv.addObject("modelo", modeloRepository.findAll());
			mv.addObject("tipo", tipoRepository.findAll());
			mv.addObject("grupo",  grupoRepository.findAll());
			mv.addObject("cor", corRepository.findAll());
			mv.addObject("situacao", situacaoRepository.findAll());
			mv.addObject("combustivel", combustivelRepository.findAll());
			mv.addObject("especie", especieRepository.findAll());
			mv.addObject("veic",veiculoRepository.pesquisaPorPatrimonio(patrimonioClass));
			mv.addObject("insercaoVeic",veiculoRepository.pesquisaPorPatrimonio(patrimonioClass));
			mv.addObject("selecao",fantasmaVeiculoRepository.buscaPlacaPtrimonio());
			mv.addObject("msg", "Veiculo " + veiculo.getPlaca_atual()+" inserido com sucesso");
			mv.addObject("btnsalvar",insercao);
			mv.addObject("impedimento", impedimentoRepository.findAll());
			
		}else {
			mv.addObject("lotacao", lotacaoRepository.findAll());
			mv.addObject("cid", cidadeRepository.cidadesOrdenadasPorNome());
			mv.addObject("aquisicao", aquisicaoRepository.findAll());
			mv.addObject("marca", marcaRepository.findAll());
			mv.addObject("modelo", modeloRepository.findAll());
			mv.addObject("tipo", tipoRepository.findAll());
			mv.addObject("grupo",  grupoRepository.findAll());
			mv.addObject("cor", corRepository.findAll());
			mv.addObject("situacao", situacaoRepository.findAll());
			mv.addObject("combustivel", combustivelRepository.findAll());
			mv.addObject("especie", especieRepository.findAll());
			mv.addObject("insercaoVeic",new Veiculos());
			mv.addObject("veic",new Veiculos());
			mv.addObject("selecao",fantasmaVeiculoRepository.buscaPlacaPtrimonio());
			mv.addObject("msg", "Sem veiculo para inserir");
			mv.addObject("btnsalvar",insercao);
			mv.addObject("impedimento", impedimentoRepository.findAll());
		}
		return mv;
	}

}
