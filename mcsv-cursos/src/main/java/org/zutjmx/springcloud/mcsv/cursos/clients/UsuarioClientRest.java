package org.zutjmx.springcloud.mcsv.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.zutjmx.springcloud.mcsv.cursos.models.Usuario;

import java.util.List;

@FeignClient(name = "mcsv-usuarios",url = "mcsv-usuarios:8001"/*url = "localhost:8001"*/)
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping("/")
    Usuario crear(@RequestBody Usuario usuario);

    @GetMapping("/usuarios-por-curso")
    List<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);

}
