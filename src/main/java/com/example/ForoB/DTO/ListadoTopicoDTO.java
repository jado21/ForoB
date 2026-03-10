package com.example.ForoB.DTO;

import com.example.ForoB.Model.Topico;

import java.util.Date;

public record ListadoTopicoDTO (
        Long id,
        String titulo,
        String mensaje,
        Date fechaCreacion,
        char estado,
        String autor,
        String curso
){
    public ListadoTopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
