package com.javhlahm.acbmin_users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javhlahm.acbmin_users.entity.UsuariosAcbmin;
import com.javhlahm.acbmin_users.repository.RepoUsuario;

@Service
public class ServiceUsuariosAcbmin {

    @Autowired
    RepoUsuario usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UsuariosAcbmin> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuariosAcbmin> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuariosAcbmin createUsuario(UsuariosAcbmin usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena())); // Encriptar contraseña
        return usuarioRepository.save(usuario);
    }

    public UsuariosAcbmin updateUsuario(String email, UsuariosAcbmin usuarioActualizado) {
        return usuarioRepository.findByEmail(email)
                .map(usuario -> {
                    usuario.setNombre(usuarioActualizado.getNombre());
                    usuario.setEmail(usuarioActualizado.getEmail());
                    usuario.setContrasena(passwordEncoder.encode(usuarioActualizado.getContrasena())); // Encriptar
                                                                                                       // nueva
                                                                                                       // contraseña
                    usuario.setRoles(usuarioActualizado.getRoles());
                    usuario.setStatus(usuarioActualizado.getStatus());
                    return usuarioRepository.save(usuario);
                }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void deleteUsuario(String email) {
        Optional<UsuariosAcbmin> usuariosAcbmin = usuarioRepository.findByEmail(email);
        if (usuariosAcbmin.isPresent()) {
            usuarioRepository.delete(usuariosAcbmin.get());
        }

    }

    public Optional<UsuariosAcbmin> login(String email, String contrasena) {
        return usuarioRepository.findByEmail(email)
                .filter(usuario -> passwordEncoder.matches(contrasena, usuario.getContrasena()));
    }
}
