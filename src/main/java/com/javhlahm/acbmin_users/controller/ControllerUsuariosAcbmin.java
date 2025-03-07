package com.javhlahm.acbmin_users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javhlahm.acbmin_users.entity.UsuariosAcbmin;
import com.javhlahm.acbmin_users.service.ServiceUsuariosAcbmin;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class ControllerUsuariosAcbmin {

    @Autowired
    private ServiceUsuariosAcbmin usuarioService;

    @GetMapping
    public List<UsuariosAcbmin> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UsuariosAcbmin> getUsuarioById(@PathVariable String email) {
        return usuarioService.getUsuarioByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UsuariosAcbmin> createUsuario(@RequestBody UsuariosAcbmin usuario) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuario));
    }

    @PutMapping("/{email}")
    public ResponseEntity<UsuariosAcbmin> updateUsuario(@PathVariable String email,
            @RequestBody UsuariosAcbmin usuario) {
        return ResponseEntity.ok(usuarioService.updateUsuario(email, usuario));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String email) {
        usuarioService.deleteUsuario(email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String contrasena) {
        return usuarioService.login(email, contrasena)
                .map(_ -> ResponseEntity.ok("Login exitoso"))
                .orElse(ResponseEntity.status(401).body("Credenciales incorrectas"));
    }
}
