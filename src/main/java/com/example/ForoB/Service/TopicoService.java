package com.example.ForoB.Service;

import com.example.ForoB.DTO.DetalleTopicoDTO;
import com.example.ForoB.DTO.ListadoTopicoDTO;
import com.example.ForoB.DTO.TopicoDTO;
import com.example.ForoB.Model.Topico;
import com.example.ForoB.Repository.TopicoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new IllegalArgumentException("No se permite registrar tópicos duplicados (mismo título y mensaje).");
        }

        Topico dato = new Topico(datos.titulo(), datos.mensaje(), datos.autor(), datos.curso());
        dato.setFechaCreacion(new Date());

        repository.save(dato);
    }

    public List<ListadoTopicoDTO> mostrarTopicos(){
        return repository.findAll().stream()
                .map(ListadoTopicoDTO::new) // Usamos el constructor del Record
                .collect(Collectors.toList());
    }

    public DetalleTopicoDTO consultarTopico(Long id) {
        // Buscamos el tópico por ID
        Optional<Topico> topicoOptional = repository.findById(id);

        // Si existe, lo mapeamos al DTO; si no, lanzamos una excepción
        if (topicoOptional.isPresent()) {
            return new DetalleTopicoDTO(topicoOptional.get());
        }

        throw new RuntimeException("Tópico no encontrado con el ID: " + id);
    }

    public void eliminarTopico(long id){
        Optional<Topico> topicoOptional = repository.findById(id);

        // 2. Verificamos usando isPresent()
        if (topicoOptional.isPresent()) {
            // 3. Si existe, lo eliminamos usando deleteById
            repository.deleteById(id);
        } else {
            // Si no existe, lanzamos un error
            throw new RuntimeException("Tópico no encontrado para eliminar.");
        }
    }

    public DetalleTopicoDTO actualizarTopico(Long id, TopicoDTO datos) {
        Optional<Topico> topicoOptional = repository.findById(id);

        if (topicoOptional.isPresent()) {
            if (repository.existsByTituloAndMensajeAndIdNot(datos.titulo(), datos.mensaje(), id)) {
                throw new IllegalArgumentException("Ya existe otro tópico diferente con ese mismo título y mensaje.");
            }

            Topico topico = topicoOptional.get();
            topico.setTitulo(datos.titulo());
            topico.setMensaje(datos.mensaje());
            topico.setAutor(datos.autor());
            topico.setCurso(datos.curso());

            repository.save(topico);

            return new DetalleTopicoDTO(topico);
        } else {
            throw new RuntimeException("Tópico no encontrado para actualizar.");
        }
    }

}
