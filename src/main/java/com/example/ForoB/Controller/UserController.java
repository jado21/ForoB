package com.example.ForoB.Controller;

import com.example.ForoB.DTO.AutenticacionUsuarioDTO;
import com.example.ForoB.DTO.DatosRegistroUsuario;
import com.example.ForoB.DTO.DatosToken;
import com.example.ForoB.Model.Usuario;
import com.example.ForoB.Repository.UsuarioRepository;
import com.example.ForoB.Service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/login")
    public ResponseEntity<DatosToken> autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO datosAutenticacion) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.login(), datosAutenticacion.clave());

        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        String JWTtoken = tokenService.generarToken(usuarioAutenticado.getName());

        return ResponseEntity.ok(new DatosToken(JWTtoken));
    }


    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {

        if (usuarioRepository.findByLogin(datos.login()) != null) {
            return ResponseEntity.badRequest().body("El usuario ya está registrado.");
        }

        String claveEncriptada = passwordEncoder.encode(datos.clave());

        Usuario nuevoUsuario = new Usuario(datos.login(), claveEncriptada);

        usuarioRepository.save(nuevoUsuario);

        return ResponseEntity.ok("Usuario registrado exitosamente. Ya puedes iniciar sesión.");
    }

}