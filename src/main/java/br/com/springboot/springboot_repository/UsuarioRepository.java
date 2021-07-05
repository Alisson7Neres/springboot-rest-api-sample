package br.com.springboot.springboot_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.springboot_model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	// Buscar por nome
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario> buscarPorNome(String name); 
}
