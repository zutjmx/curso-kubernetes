package org.zutjmx.spingcloud.mcsv.usuarios.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zutjmx.spingcloud.mcsv.usuarios.models.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query("select u from Usuario u where u.email = ?1")
    Optional<Usuario> porEmail(String email);

    boolean existsByEmail(String email);
}
