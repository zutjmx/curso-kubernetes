package org.zutjmx.springcloud.mcsv.cursos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.zutjmx.springcloud.mcsv.cursos.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}