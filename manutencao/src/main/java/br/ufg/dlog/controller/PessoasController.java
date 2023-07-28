package br.ufg.dlog.controller;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.dlog.classes.Pessoa;
import br.ufg.dlog.classes.ValidaCPF;
import br.ufg.dlog.repository.LotacaoRepository;
import br.ufg.dlog.repository.PessoaRepository;
import br.ufg.dlog.repository.UsuarioRepository;

@Controller
public class PessoasController {
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private LotacaoRepository lotacaoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	
	@GetMapping("**/pessoas")
	public ModelAndView iniciarPessoas() {
		ModelAndView mv = new ModelAndView("pessoas.html");
		mv.addObject("pessoa",new Pessoa());
		mv.addObject("lotacao", lotacaoRepository.findAll());
		mv.addObject("msg","Bem vindo ao formulario de pessoas");
		mv.addObject("todaspessoas",new Pessoa());
		
		return mv;
		
	}
	
	@GetMapping("**/listartodaspessoas")
	public ModelAndView listarTodasPessoas() {
		ModelAndView mv = new ModelAndView("pessoas.html");
		mv.addObject("pessoa",new Pessoa());
		mv.addObject("todaspessoas", pessoaRepository.findAll());
		mv.addObject("lotacao", lotacaoRepository.findAll());
		mv.addObject("msg","Lista de todas pessoas, ativos ou inativos");
		
		return mv;
		
	}
	
	@PostMapping("**/pesquisarpessoa")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa, @PageableDefault(size=10,sort= {"nm_pessoa_func"})Pageable pageable) {

		ModelAndView mv = new ModelAndView("pessoas.html");
		if (nomepesquisa!=null && !nomepesquisa.isEmpty()) {
			mv.addObject("todaspessoas",  pessoaRepository.pesquisaPorNome(nomepesquisa));
			mv.addObject("msg","Lista de pessoas com "+nomepesquisa+" no nome, ativos ou inativos");
			mv.addObject("pessoa",new Pessoa());
			mv.addObject("lotacao", lotacaoRepository.findAll());
		}else {
			mv.addObject("todaspessoas",new Pessoa());
			mv.addObject("pessoa",new Pessoa());
			mv.addObject("lotacao", lotacaoRepository.findAll());
			mv.addObject("msg","Nenhuma pessoa encontrada para a pesquisa com "+nomepesquisa);
			
		}
			
		
		return mv;
	}
	@GetMapping("/editarpessoa/{cd_pessoa_func}")
	public ModelAndView editar(@PathVariable("cd_pessoa_func") Long cd_pessoa_func) {
		Pessoa pessoa = new Pessoa();
		pessoa=pessoaRepository.pesquisaId(cd_pessoa_func);
		
		ModelAndView mv = new ModelAndView("pessoas.html");
		mv.addObject("pessoa",pessoa);
		mv.addObject("lotacao", lotacaoRepository.findAll());
		mv.addObject("todaspessoas",new Pessoa());
		mv.addObject("msg","");
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa")
	public ModelAndView salvarPessoa(@Valid Pessoa pessoa, BindingResult bindingResult) throws IOException {
		ModelAndView mv = new ModelAndView("pessoas.html");
        
		String nome=pessoa.getNm_pessoa_func(); String apelido=pessoa.getPri_nome();
		String cargo=pessoa.getCargo_f();String matricula=pessoa.getMatricula_f();
		String cpf=pessoa.getCpf(); String identidade = pessoa.getIdentidade();
		String ativo = pessoa.getAtivo(); String servidorPublico = pessoa.getServidor_publico();
		String cep = pessoa.getCep(); String rua=pessoa.getEndereco();
		if (pessoa.getCd_pessoa_func()!=null && pessoa.getCd_pessoa_func()>0) {

			pessoa.setNm_pessoa_func(pessoa.getNm_pessoa_func().toUpperCase());
			pessoa.setUf(pessoa.getUf().toUpperCase());
			pessoaRepository.save(pessoa);
			if (pessoa.getAtivo().contains("N")) {
			//usuarioRepository.deleteById(pessoa.getCd_pessoa_func());
			}
			mv.addObject("pessoa",pessoa);
			mv.addObject("lotacao", lotacaoRepository.findAll());
			mv.addObject("todaspessoas",new Pessoa());
			mv.addObject("msg","Pessoa salva com sucesso");
		}else if(nome.isEmpty()||apelido.isEmpty()||cargo.isEmpty()||matricula.isEmpty()||cpf.isEmpty()||
				identidade.isEmpty()||ativo.isEmpty()||servidorPublico.isEmpty()||cep.isEmpty()||rua.isEmpty())
		  {
			mv.addObject("pessoa",new Pessoa());
			mv.addObject("lotacao", lotacaoRepository.findAll());
			mv.addObject("todaspessoas",new Pessoa());
			mv.addObject("msg","Nao encontrado dados da pessoa para serem salvos");
			
		}else {
			Long i = pessoaRepository.maximoCodigoPessoa();
			i=i+1;
			pessoa.setCd_pessoa_func(i);
			pessoaRepository.save(pessoa);
			mv.addObject("pessoa", pessoaRepository.findById(i));
			mv.addObject("lotacao", lotacaoRepository.findAll());
			mv.addObject("todaspessoas",new Pessoa());
			mv.addObject("msg","Gravação "+i+" efetuada com sucesso conforme dados da tela");
		}
		return mv;
	}

}
