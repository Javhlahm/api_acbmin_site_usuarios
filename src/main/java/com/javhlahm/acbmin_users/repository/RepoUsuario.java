package com.javhlahm.acbmin_users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javhlahm.acbmin_users.entity.UsuariosAcbmin;

@Repository
public interface RepoUsuario extends JpaRepository<UsuariosAcbmin, Long> {
    Optional<UsuariosAcbmin> findByUsuario(String usuario);

    void deleteByUsuario(String usuario);
}
