package com.example.spring.controller;

import com.example.spring.models.Usuario;
import com.example.spring.repository.UsuarioRepository;
import com.example.spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/user")
@RestController
public class UserController {

	//Importando minha camada de serviço
	@Autowired
	private UsuarioService usuarioService;

	//Criando as rotas do CRUD:

	//Rota para retornar tudo
	@GetMapping
	public List<Usuario> findAll(){
		return usuarioService.findAll();
	}

	//Entre chaves: será passado um id na url
	//@PathVariable usa o id vindo da url e coloca dentro da minha função.
	@GetMapping(value="/{id}")
	public Usuario findById(@PathVariable Long id){

		//Optional retorna usuario.
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if (!usuarioOptional.isPresent()){
			//Tratamento de erro abaixo, usando HttpStatus e chamado o tipo de retorno, na frente a frase que
			//desejo implementar nesse retorno
			//Throw, palavra reservada para lançar exceção. Cria
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
		}
		//Pega o proprio usuario dentro da classe optional.
		return usuarioOptional.get();
	}

	//Corpo da requisição: necessario quando a gente encaminha dados do front pro back.
	@PostMapping
	public Usuario save(@RequestBody Usuario usuario){
		return usuarioService.save(usuario);
	}

	//Para atualizar
	@PutMapping(value="/{id}")
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id){
		//Optional retorna usuario.
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if (!usuarioOptional.isPresent()){
			//Tratamento de erro abaixo, usando HttpStatus e chamado o tipo de retorno, na frente a frase que
			//desejo implementar nesse retorno
			//Throw, palavra reservada para lançar exceção. Cria
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
		}
		//Comando abaixo para settar o id no corpo. Pega id da url e seta no usuario para evitar atualizar
		//outro
		usuario.setId(id);
		return usuarioService.save(usuario);
	}

	//Igual o get, so que se chama delete e serve para apagar.
	@DeleteMapping(value="/{id}")
	public String delete(@PathVariable Long id){
		//Verificando se o usuario existe.
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if (!usuarioOptional.isPresent()){
			//Tratamento de erro abaixo, usando HttpStatus e chamado o tipo de retorno, na frente a frase que
			//desejo implementar nesse retorno
			//Throw, palavra reservada para lançar exceção. Cria
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
		}
		//Chamando um metodo da classe.
		usuarioService.delete(id);
		return "Usuario deletado";
	}

	@GetMapping(value="/find-name/{nome}")
	public Usuario findByName(@PathVariable String nome){
		return usuarioService.findByName(nome);
	}

	@GetMapping(value="/find-name-jpql/{nome}")
	public Usuario findByNameJpql(@PathVariable String nome){
		return usuarioService.findByNameJqpl(nome);
	}
}
