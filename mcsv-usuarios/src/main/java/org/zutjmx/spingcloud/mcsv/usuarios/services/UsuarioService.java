package org.zutjmx.spingcloud.mcsv.usuarios.services;

import org.zutjmx.spingcloud.mcsv.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> Listar();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
}
