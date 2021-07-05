package br.com.springboot.springboot_rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.springboot_model.Usuario;
import br.com.springboot.springboot_repository.UsuarioRepository;

@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
    
    // Listar usuários
    @GetMapping(path = "**/listar")
    public ResponseEntity<List<Usuario>> list(){
    	
    	List<Usuario> list = usuarioRepository.findAll();
		
    	return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
    }
    
    // Cadastra usuários
	@PostMapping(value = "**/salvar")
    @ResponseBody
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
    	
    	Usuario usuarioSalvar = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(usuarioSalvar, HttpStatus.CREATED);
    }
    
    // Deletar usuários
    @DeleteMapping(value = "**/deletar")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long iduser){

    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("Deletado!", HttpStatus.OK);
    }
    
    // Atualizar usuários
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario, @PathVariable Long id){
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity("Informe o ID", HttpStatus.BAD_GATEWAY);
    	}
    	Usuario usuarioAtualizar = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity(usuarioAtualizar, HttpStatus.OK);
    }
    
    // Consultar Usuário por id
	@GetMapping(value = "**/bucaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscar(@RequestParam (name = "id") Long id){

    	 Usuario usuarioBuscar = usuarioRepository.findById(id).get();
    	
    	return new ResponseEntity<Usuario>(usuarioBuscar, HttpStatus.OK);
    }
    
	@GetMapping(value = "**/buscarPorNome", produces = "application/json")
	@ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String nome){

    	 List<Usuario> usuarioBuscar = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuarioBuscar, HttpStatus.OK);
    } 
}
