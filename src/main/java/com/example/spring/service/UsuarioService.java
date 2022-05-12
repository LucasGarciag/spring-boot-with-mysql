//MINHA CAMADA DE SERVIÇO

package com.example.spring.service;

import com.example.spring.models.Usuario;
import com.example.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Indica que esta classe será injetada pelo spring boot e classe de serviço
@Service
public class UsuarioService {

    //Chamei o repositorio pra ca
    //@Autowired: Injeta uma instancia do repositorio na minha classe.
    @Autowired
    private UsuarioRepository usuarioRepository;

    //Salva um usuario. Se for atribuido um id ele atualiza o existente. se não, ele adiciona um novo.
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //Retorna uma lista de usuario
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    //Por id retorna optional: isset
    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

    //Metodo void, somente para excluir uma pessoa do db pelo id.
    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario findByName(String nome){
        return usuarioRepository.findByName(nome);
    }

    public Usuario findByNameJqpl(String nome){
        return usuarioRepository.findByNameJPQL(nome);
    }
}
