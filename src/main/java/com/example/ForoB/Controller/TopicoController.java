package com.example.ForoB.Controller;

import com.example.ForoB.DTO.TopicoDTO;
import com.example.ForoB.Model.Topico;
import com.example.ForoB.Service.TopicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicoController {

    TopicoService service;

    public TopicoController(TopicoService service){
        this.service=service;
    }

    @PostMapping("/registrar")
    public void registraTopico(@RequestBody TopicoDTO top){

        System.out.println(top);
        service.guardarTopico(top);

        System.out.println("Se guardaron los datos");
    }

    @GetMapping("/topicos")
    public List<TopicoDTO> mostrarTopicos(){
        return service.mostrarTopicos();
    }

    @GetMapping("/topicos/{id}")
    public Optional<Topico> consultarTopico(@PathVariable Long id){
        return service.consultarTopico(id);
    }
}
