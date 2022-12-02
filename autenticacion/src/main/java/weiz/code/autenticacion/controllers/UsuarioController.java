package weiz.code.autenticacion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import weiz.code.autenticacion.models.Rol;
import weiz.code.autenticacion.models.Usuario;
import weiz.code.autenticacion.repositoris.RolRepository;
import weiz.code.autenticacion.repositoris.UsuarioRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public List<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario dataUsuario){
        dataUsuario.setClave(convertirSHA256(dataUsuario.getClave()));
        return this.usuarioRepository.save(dataUsuario);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable String id, @RequestBody Usuario dataUsuario){
        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
        if (Objects.nonNull(usuario)){
            usuario.setSeudonimo(dataUsuario.getSeudonimo());
            usuario.setCorreo(dataUsuario.getCorreo());
            usuario.setClave(convertirSHA256(dataUsuario.getClave()));
            return this.usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    @PutMapping("/asignar_rol/{id}/rol/{id_rol}")
    public Usuario updateRol(@PathVariable String id, @PathVariable String id_rol){
        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
        Rol rol = this.rolRepository.findById(id_rol).orElse(null);
        if(Objects.nonNull(usuario) && Objects.nonNull(rol)){
            usuario.setRol(rol);
            return this.usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }
    @PutMapping("{id}/rol/{id_rol}")
    public Usuario asignarRolAUsuario(@PathVariable String id,@PathVariable String id_rol){
        Usuario usuario=this.usuarioRepository
                .findById(id).orElse(null);
        Rol rol=this.rolRepository
                .findById(id_rol).orElse(null);
        if(usuario != null && rol != null){
            usuario.setRol(rol);
        return this.usuarioRepository.save(usuario);
    }else{
            return null;
        }
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable String id){
        return this.usuarioRepository.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        Usuario us = this.usuarioRepository.findById(id).orElse(null);
        if(Objects.nonNull(us)){
            this.usuarioRepository.delete(us);
        }
    }
    @PostMapping("/validar")
    public Usuario validate(@RequestBody Usuario dataUsuario,
                           final HttpServletResponse response) throws IOException {
        Usuario usuario=this.usuarioRepository
                .getUserByEmail(dataUsuario.getCorreo());
        if (usuario !=null &&
            usuario.getClave().equals(convertirSHA256(dataUsuario.getClave()))) {
            usuario.setClave("");
            return usuario;
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    private String convertirSHA256(String clave) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(clave.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
