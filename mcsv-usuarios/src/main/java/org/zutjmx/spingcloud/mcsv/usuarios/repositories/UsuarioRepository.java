package org.zutjmx.spingcloud.mcsv.usuarios.repositories;

import org.springframework.data.repository.CrudRepository;
import org.zutjmx.spingcloud.mcsv.usuarios.models.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
