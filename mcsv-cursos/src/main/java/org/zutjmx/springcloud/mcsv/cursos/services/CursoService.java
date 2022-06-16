package org.zutjmx.springcloud.mcsv.cursos.services;

import org.zutjmx.springcloud.mcsv.cursos.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);
}
