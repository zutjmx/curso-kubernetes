package org.zutjmx.springcloud.mcsv.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zutjmx.springcloud.mcsv.cursos.clients.UsuarioClientRest;
import org.zutjmx.springcloud.mcsv.cursos.models.Usuario;
import org.zutjmx.springcloud.mcsv.cursos.models.entity.Curso;
import org.zutjmx.springcloud.mcsv.cursos.models.entity.CursoUsuario;
import org.zutjmx.springcloud.mcsv.cursos.repositories.CursoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioClientRest usuarioClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Usuario usuarioMcsv = usuarioClientRest.detalle(usuario.getId());

            Curso curso = cursoOptional.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMcsv.getId());

            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioMcsv);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Usuario usuarioMcsv = usuarioClientRest.crear(usuario);

            Curso curso = cursoOptional.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMcsv.getId());

            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioMcsv);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Usuario usuarioMcsv = usuarioClientRest.detalle(usuario.getId());

            Curso curso = cursoOptional.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMcsv.getId());

            curso.removeCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioMcsv);
        }
        return Optional.empty();
    }
}
