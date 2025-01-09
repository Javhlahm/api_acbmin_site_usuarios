package com.javhlahm.acbmin_users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javhlahm.acbmin_users.entity.UsuariosAcbmin;
import com.javhlahm.acbmin_users.repository.RepoUsuario;

@Service
public class ServiceUsuariosAcbmin {

    @Autowired
    RepoUsuario repoUsuario;

    public List<UsuariosAcbmin> obtenerUsuariosAcbmins() {
        return repoUsuario.findAll();
    }

    public Optional<UsuariosAcbmin> obtenerUsuarioUserName(String usuario) {
        return repoUsuario.findByUsuario(usuario);
    }

    public UsuariosAcbmin guardarActualizarUsuariosAcbmin(UsuariosAcbmin usuariosAcbmin) {
        return repoUsuario.save(usuariosAcbmin);
    }

    public void eliminarUsuario(String usuario) {
        repoUsuario.deleteByUsuario(usuario);
    }

}
