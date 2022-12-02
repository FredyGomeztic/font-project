package weiz.code.autenticacion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import weiz.code.autenticacion.models.Rol;
import weiz.code.autenticacion.models.Usuario;
import weiz.code.autenticacion.repositoris.RolRepository;
import weiz.code.autenticacion.repositoris.UsuarioRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public List<Rol> findAll(){
        return this.rolRepository.findAll();
    }

    @PostMapping
    public Rol create(@RequestBody Rol dataRol){
        return this.rolRepository.save(dataRol);
    }

    @PutMapping("/{id}")
    public Rol update(@PathVariable String id, @RequestBody Rol dataRol){
        Rol rol = this.rolRepository.findById(id).orElse(null);
        if (Objects.nonNull(rol)){
            rol.setNombre(dataRol.getNombre());
            rol.setDescripcion(dataRol.getDescripcion());
            return this.rolRepository.save(rol);
        } else {
            return null;
        }
    }

    @GetMapping("/{id}")
    public Rol findById(@PathVariable String id){
        return this.rolRepository.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        Rol rol = this.rolRepository.findById(id).orElse(null);
        if(Objects.nonNull(rol)){
            this.rolRepository.delete(rol);
        }
    }

}
