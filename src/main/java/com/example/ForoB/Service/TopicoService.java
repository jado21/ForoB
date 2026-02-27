package com.example.ForoB.Service;

import com.example.ForoB.DTO.TopicoDTO;
import com.example.ForoB.Model.Topico;
import com.example.ForoB.Repository.TopicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    TopicoRepository repository;

    public TopicoService(TopicoRepository repository){
        this.repository=repository;
    }

    public void guardarTopico(TopicoDTO datos){
        Topico dato = new Topico(datos.titulo(), datos.mensaje(), datos.autor(), datos.curso());
        repository.save(dato);
    }

    public List<TopicoDTO> mostrarTopicos(){
        List<Topico> topicos = repository.findAll();
        return topicos.stream()
                .map(top ->
                new TopicoDTO(top.getTitulo(),top.getMensaje(),top.getAutor(),top.getCurso()))
                .collect(Collectors.toList());
    }

    public Optional<Topico> consultarTopico(long id){
        return repository.findById(id);
    }

}
