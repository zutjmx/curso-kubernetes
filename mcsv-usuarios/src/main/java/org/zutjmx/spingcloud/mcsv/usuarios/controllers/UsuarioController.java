package org.zutjmx.spingcloud.mcsv.usuarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zutjmx.spingcloud.mcsv.usuarios.models.entity.Usuario;
import org.zutjmx.spingcloud.mcsv.usuarios.services.UsuarioService;

import javax.validation.Valid;
import java.util.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ApplicationContext context;

    @GetMapping("/crash")
    public void crash() {
        ((ConfigurableApplicationContext)context).close();
    }

    @GetMapping("/")
    public Map<String,List<Usuario>> listar() {
        return Collections.singletonMap("users",usuarioService.Listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable(name = "id") Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.porId(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {

        ResponseEntity<Map<String, String>> errores = getErrores(result);
        if (errores != null) {
            return errores;
        }

        ResponseEntity<Map<String, String>> mensajeEmail = validaCorreo(usuario);
        if (mensajeEmail != null) {
            return mensajeEmail;
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario,
                                    BindingResult result,
                                    @PathVariable(name = "id") Long id) {

        ResponseEntity<Map<String, String>> errores = getErrores(result);
        if (errores != null) {
            return errores;
        }

        Optional<Usuario> usuarioOptional = usuarioService.porId(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioDb = usuarioOptional.get();

            if (!usuario.getEmail().isEmpty()
                    && !usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail())
                    && usuarioService.porEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity
                        .badRequest()
                        .body(Collections
                                .singletonMap("mensaje",":: Ya existe un usuario con el correo electrónico <"
                                        .concat(usuario.getEmail())
                                        .concat("> en la tabla usuarios ::")));
            }

            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(usuarioService.guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable(name = "id") Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.porId(id);
        if (usuarioOptional.isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios-por-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(usuarioService.listarPorIds(ids));
    }

    private ResponseEntity<Map<String, String>> getErrores(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> {
                errores.put(fieldError.getField(),
                        "::El campo "
                                .concat(fieldError.getField())
                                .concat(" ")
                                .concat(fieldError.getDefaultMessage())
                                .concat("::"));
            });
            return ResponseEntity.badRequest().body(errores);
        }
        return null;
    }

    private ResponseEntity<Map<String, String>> validaCorreo(Usuario usuario) {
        if (!usuario.getEmail().isEmpty()
                && usuarioService.existePorEmail(usuario.getEmail())) {
            Map<String, String> mensajeEmail = new HashMap<>();
            mensajeEmail.put("mensaje",":: Ya existe un usuario con el correo electrónico <"
                    .concat(usuario.getEmail())
                    .concat("> en la tabla usuarios ::"));
            return ResponseEntity.badRequest().body(mensajeEmail);
        }
        return null;
    }


}
