package com.example.ForoB.Controller;

import com.example.ForoB.DTO.DetalleTopicoDTO;
import com.example.ForoB.DTO.ListadoTopicoDTO;
import com.example.ForoB.DTO.TopicoDTO;
import com.example.ForoB.Model.Topico;
import com.example.ForoB.Service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicoController {

    TopicoService service;

    public TopicoController(TopicoService service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<String> registraTopico(@RequestBody @Valid TopicoDTO top){
        try {
            service.guardarTopico(top);
            return ResponseEntity.ok("Tópico registrado con éxito.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/topicos")
    public List<ListadoTopicoDTO> mostrarTopicos(){
        return service.mostrarTopicos();
    }

    @GetMapping("/topicos/{id}")
    public ResponseEntity<DetalleTopicoDTO> consultarTopico(@PathVariable Long id){
        try {
            var datosTopico = service.consultarTopico(id);
            return ResponseEntity.ok(datosTopico);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}") // Acepta solicitudes PUT en /topicos/{id}
    public ResponseEntity<DetalleTopicoDTO> actualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoDTO datos) {
        try {
            var topicoActualizado = service.actualizarTopico(id, datos);
            return ResponseEntity.ok(topicoActualizado);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // Acepta DELETE en /topicos/{id}
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        try {
            service.eliminarTopico(id);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
