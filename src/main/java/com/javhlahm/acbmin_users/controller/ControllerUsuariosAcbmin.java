package com.javhlahm.acbmin_users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javhlahm.acbmin_users.entity.UsuariosAcbmin;
import com.javhlahm.acbmin_users.service.ServiceUsuariosAcbmin;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class ControllerUsuariosAcbmin {

    @Autowired
    ServiceUsuariosAcbmin serviceUsuariosAcbmin;

    @GetMapping("/usuarios")
    public List<UsuariosAcbmin> obtenerUsuariosAcbmins() {
        return serviceUsuariosAcbmin.obtenerUsuariosAcbmins();
    }

    @GetMapping("/usuarios/{usuario}")
    public Optional<UsuariosAcbmin> obtenerUsuarioID(@PathVariable("usuario") String usuario) {
        return serviceUsuariosAcbmin.obtenerUsuarioUserName(usuario);
    }

    @PostMapping("/usuarios")
    public UsuariosAcbmin guardarUsuario(@RequestBody UsuariosAcbmin usuariosAcbmin) {
        return serviceUsuariosAcbmin.guardarActualizarUsuariosAcbmin(usuariosAcbmin);
    }

    @PutMapping("/usuarios")
    public UsuariosAcbmin actualizarItem(@RequestBody UsuariosAcbmin usuariosAcbmin) {
        UsuariosAcbmin usuariosAcbmin2;
        usuariosAcbmin2 = usuariosAcbmin.clone();
        usuariosAcbmin2.setUsuario(null);
        serviceUsuariosAcbmin.guardarActualizarUsuariosAcbmin(usuariosAcbmin2);
        return serviceUsuariosAcbmin.guardarActualizarUsuariosAcbmin(usuariosAcbmin);
    }

    @DeleteMapping("/usuarios/{usuario}")
    public void eliminarItemItem(@PathVariable("usuario") String usuario) {
        serviceUsuariosAcbmin.eliminarUsuario(usuario);
    }
}
