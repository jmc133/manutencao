package br.ufg.dlog.controller;



import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufg.dlog.classes.Pessoa;
import br.ufg.dlog.classes.Usuario;
import br.ufg.dlog.classes.UsuariosEmpresas;
import br.ufg.dlog.classes.pessoa_juridica;
import br.ufg.dlog.classes.usuarios_role;
import br.ufg.dlog.repository.PessoaJuridicaRepository;
import br.ufg.dlog.repository.PessoaRepository;
import br.ufg.dlog.repository.SexoRepository;
import br.ufg.dlog.repository.Tipo_manutencaoRespository;
import br.ufg.dlog.repository.UsuarioRepository;
import br.ufg.dlog.repository.Usuario_RoleRepository;
import br.ufg.dlog.repository.UsuariosEmpresasRepository;

@Controller
public class UsuarioController {
	Integer contaUsuario;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private Usuario_RoleRepository usuario_roleRepository;
	@Autowired
	private Tipo_manutencaoRespository tipoManutencaoRepository;
	@Autowired
	private SexoRepository sexoRepository;
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	@Autowired
	private UsuariosEmpresasRepository usuarioEmpresaRepository;
	@Autowired
	private Usuario_RoleRepository usuarioRoleRepository;

	
	@GetMapping("**/usuarios")
	public ModelAndView iniciarUsuario() {
		ModelAndView mv = new ModelAndView("usuario.html");
		mv.addObject("pessoa",new Pessoa());
		mv.addObject("role", usuarioRepository.listaDeRoles());
		mv.addObject("msg","Bem vindo ao formulario de usuarios");
		mv.addObject("usuario",new Usuario());
		
		return mv;
		
	}
	
	@PostMapping("**/pesquisausuario")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa, @PageableDefault(size=10,sort= {"nm_pessoa_func"})Pageable pageable) {
	
		ModelAndView mv = new ModelAndView("usuario.html");
		if (nomepesquisa!=null && !nomepesquisa.isEmpty()) {
			mv.addObject("pesquisapessoa",  pessoaRepository.pesquisaPorNome(nomepesquisa));
			mv.addObject("msg","Lista de pessoas com "+nomepesquisa+" no nome, ativos ou inativos");
			mv.addObject("pessoa",new Pessoa());
			mv.addObject("role", usuarioRepository.listaDeRoles());
		}else {
			mv.addObject("pesquisapessoa",new Pessoa());
			mv.addObject("pessoa",new Pessoa());
			mv.addObject("role", usuarioRepository.listaDeRoles());
			mv.addObject("msg","Nenhuma pessoa encontrada para a pesquisa ");
			
		}
			
		
		return mv;
	}
	@GetMapping("/editarusuario/{cd_pessoa_func}")
	public ModelAndView editar(@PathVariable("cd_pessoa_func") Long cd_pessoa_func) {
		Pessoa pessoa = new Pessoa();
		Usuario usuario = new Usuario();
		usuario = usuarioRepository.pesquisaPorId(cd_pessoa_func);
		
		
		if (Objects.isNull(usuario)) {
			contaUsuario=0;
		}else {
			contaUsuario=1;
		}
		ModelAndView mv = new ModelAndView("usuario.html");
		if (cd_pessoa_func!=null ) {
		pessoa=pessoaRepository.pesquisaId(cd_pessoa_func);	
		if (pessoa.getCd_pessoa_func()!=null && pessoa.getCd_pessoa_func()>0) {
		mv.addObject("pessoa",pessoa);
		mv.addObject("role", usuarioRepository.listaDeRoles());
		mv.addObject("pesquisapessoa",new Pessoa());
		  if (contaUsuario==1) {
			  mv.addObject("msg","Usuario existente");
		  }else {
	         	mv.addObject("msg","Usuario nao existente");
		  }
		}	else {
				
			mv.addObject("pessoa",new Pessoa());
			mv.addObject("role", usuarioRepository.listaDeRoles());
			mv.addObject("pesquisapessoa",new Pessoa());
			mv.addObject("msg","Nenhum usuario encontrado para pesquisa");			
			
		}
		}
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarusuario")
	public ModelAndView salvarUsuario(@RequestParam("cd_pessoa_func")String cd_pessoa_func,@RequestParam("pri_nome")String pri_nome,
			@RequestParam("senha")String senha, @RequestParam("role")String role) {
		Usuario usuario = new Usuario();
		usuarios_role usuariorole = new usuarios_role();
		
		ModelAndView mv = new ModelAndView("usuario.html");
		 if (cd_pessoa_func!=null && !cd_pessoa_func.isEmpty()) {
			 if (pri_nome!=null && !pri_nome.isEmpty()) {
				 if(senha!=null && !senha.isEmpty()) {
					
					 if (role!=null && !role.isEmpty()) {
						 usuario.setId(Long.parseLong(cd_pessoa_func));
						 usuario.setLogin(pri_nome);
						  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
						  String result = encoder.encode(senha);
						 usuario.setSenha(result);
						 usuarioRepository.save(usuario);
						 usuariorole.setUsuario_id(Long.parseLong(cd_pessoa_func));
						 usuariorole.setRole_id(Long.parseLong(role));
						 usuario_roleRepository.save(usuariorole);
						 pessoaRepository.alteraSenhaNoPessoa(senha,Long.valueOf(cd_pessoa_func));
						 mv.addObject("msg","Usuario "+pri_nome+" salvo com sucesso");
						 mv.addObject("pessoa",new Pessoa());
						 mv.addObject("role", usuarioRepository.listaDeRoles());
						 mv.addObject("pesquisapessoa",new Pessoa());
					 }else { mv.addObject("pessoa", usuarioRepository.pesquisaPorId(Long.parseLong(cd_pessoa_func)));
					         mv.addObject("role", usuarioRepository.listaDeRoles());
					         mv.addObject("msg","Nao foi escolhido a regra de usuario, por favor insira uma regra");
					         mv.addObject("pesquisapessoa",new Pessoa());
					 }
				 }else {mv.addObject("pessoa", new Pessoa());
		                mv.addObject("role", usuarioRepository.listaDeRoles());
		                mv.addObject("msg","Nao foi inserida a senha, para usuario e obrigatorio");
		                mv.addObject("pesquisapessoa",new Pessoa());
		               
				 }
			 }else {mv.addObject("pessoa", usuarioRepository.pesquisaPorId(Long.parseLong(cd_pessoa_func)));
                    mv.addObject("role", usuarioRepository.listaDeRoles());
                    mv.addObject("msg","Nao foi inserida o nome de usuario, isso e obrigatorio");
                    mv.addObject("pesquisapessoa",new Pessoa());
				 
			 }
		 }else {mv.addObject("pessoa", new Pessoa());
                mv.addObject("role", usuarioRepository.listaDeRoles());
                mv.addObject("msg","Nao foi inserido um usuario e necessario escolher uma pessoa");
                mv.addObject("pesquisapessoa",new Pessoa());
			 
		 }
		 return mv;
	}
	@RequestMapping("/logincadastro")
	public ModelAndView LoginCadastro() {
		ModelAndView mv = new ModelAndView("logincadastro");
		mv.addObject("tipoman", tipoManutencaoRepository.findAll());
		mv.addObject("sexo", sexoRepository.findAll());
		mv.addObject("ue", new UsuariosEmpresas());
		mv.addObject("msg", "Bem vindo ao formulário de cadastro de usuario e empresa!");
		
		return mv;
	}
	
	@RequestMapping("/salvaruserempresa")
	public ModelAndView SalvarUserEmpresa(@RequestParam("nome")String nome,@RequestParam("email")String email,
			@RequestParam("senha")String senha,@RequestParam("repitasenha")String repitasenha, @RequestParam("sexo")String sexo,
			@RequestParam("nomefantasia")String nomefantasia,@RequestParam("nomeempresa")String nomeempresa,
			@RequestParam("endereco")String endereco,@RequestParam("bairro")String bairro,
			@RequestParam("cep")String cep,@RequestParam("cidade")String cidade,@RequestParam("pais")String pais,
			@RequestParam("uf")String uf,@RequestParam("ddd")String ddd,@RequestParam("fone")String fone,
			@RequestParam("cnpj")String cnpj,@RequestParam("tipomanutencao")String tipomanutencao) {
		
		ModelAndView mv = new ModelAndView("logincadastro");
		
		nome=nome.trim();email=email.trim();senha=senha.trim();repitasenha=repitasenha.trim();
		nomefantasia=nomefantasia.trim();nomeempresa=nomeempresa.trim();endereco=endereco.trim();bairro=bairro.trim();
		cep=cep.trim();cidade=cidade.trim();pais=pais.trim();uf=uf.trim();cnpj=cnpj.trim();
		Long maxIdPj = pessoaJuridicaRepository.maximoIdPessoaJuridica()+1;
		Long maxIdUsuario = usuarioRepository.maximoIdUsuario()+9999000;
		Long idUsuarioEmpresa;
		if(usuarioEmpresaRepository.maximoIdUsuarioEmpresa()==null) {
			idUsuarioEmpresa=(long) 1;
		}else {
			idUsuarioEmpresa=usuarioEmpresaRepository.maximoIdUsuarioEmpresa()+1;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		  String senhaCripto = encoder.encode(senha);
		  System.out.println(endereco+" "+bairro+" "+cep+" "+cidade+" "+pais+" "+uf+" Vendo endereco em cima");
		  
		
		if(Objects.equals(senha, repitasenha)) {
			System.out.println(senha+" "+repitasenha);
		if(!Objects.isNull(nome)&&nome!=null&&nome!=""&&!Objects.isNull(email)&&email!=null&&email!=""&&
				!Objects.isNull(senha)&&senha!=null&&senha!="") {
			System.out.println(nome+" "+email);
			if(!Objects.isNull(nomefantasia)&&nomefantasia!=null&&nomefantasia!=""&&!Objects.isNull(nomeempresa)&&
					nomeempresa!=null&&nomeempresa!="") {
				System.out.println(nomefantasia+" "+nomeempresa);
				if(!Objects.isNull(endereco)) {
					System.out.println(endereco+" "+bairro+" "+cep+" "+cidade+" "+pais+" "+uf);
					if(!Objects.isNull(ddd)&&ddd!=null&&ddd!=""&&!Objects.isNull(fone)&&fone!=null&&fone!=""&&cnpj!=null&&cnpj!="") {
						System.out.println(ddd+" "+fone+" "+cnpj+" final...." );
						final UsuariosEmpresas ue = new UsuariosEmpresas();
						final Usuario u = new Usuario();
						final usuarios_role ur = new usuarios_role();
						final pessoa_juridica pj = new pessoa_juridica();
						ue.setNome(nome);
						ue.setEmail(email);
						ue.setSenha(senhaCripto);
						ue.setSexo(sexo);
						ue.setVinculo(maxIdPj);
						ue.setId_ue(idUsuarioEmpresa);
						
						u.setId(maxIdUsuario);
						u.setLogin(email);
						u.setSenha(senhaCripto);
						//u.setRoles(5);
						ur.setRole_id((long) 5);
						ur.setUsuario_id(maxIdUsuario);
						
						pj.setAtivo("S");
						pj.setCd_pessoa_jur(maxIdPj);
						pj.setCidade(cidade);
						pj.setCnpj(cnpj);
						pj.setEmail(email);
						pj.setNm_fantasia(nomefantasia);
						pj.setNm_logradouro(endereco);
						pj.setNm_pessoa_jur(nomeempresa);
						pj.setNum_cep(cep);
						pj.setNum_ddd(ddd);
						pj.setNum_telefone(fone);
						pj.setPais(pais);
						pj.setTipo_manutencao(Long.parseLong(tipomanutencao));
						pj.setUf(uf);
						
						usuarioEmpresaRepository.save(ue);
						usuarioRepository.save(u);
						usuario_roleRepository.save(ur);
						pessoaJuridicaRepository.save(pj);
						
						mv.addObject("msg", "Usuario e empresa salvo com sucesso! pode voltar a tela de Login");
						mv.addObject("ue", usuarioEmpresaRepository.findById(maxIdUsuario));
						
						
					}else {
						mv.addObject("msg","O DDD, Telefone ou CNPJ não foram preenchidos!");
						mv.addObject("ue", new UsuariosEmpresas());
					}
					
				}else {
					mv.addObject("msg","O endereço, bairro, cep, cidade, país ou UF, não foi ou foram preenchidos!");
					mv.addObject("ue", new UsuariosEmpresas());
				}
				
			}else {
				mv.addObject("msg", "Nome fantasia ou da empresa não estão preenchidos!");
				mv.addObject("ue", new UsuariosEmpresas());
			}
		}else {
			mv.addObject("msg","Usuário, email ou senha, não estão preenchidos!");
			mv.addObject("ue", new UsuariosEmpresas());
		}
		}else {
			mv.addObject("msg","Senha comparada com Repetir senha, não confere");
			mv.addObject("ue", new UsuariosEmpresas());
		}
		mv.addObject("tipoman", tipoManutencaoRepository.findAll());
		mv.addObject("sexo", sexoRepository.findAll());
		
		
		return mv;
	}

}
