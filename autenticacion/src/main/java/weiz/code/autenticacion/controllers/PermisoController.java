package weiz.code.autenticacion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import weiz.code.autenticacion.models.Permiso;
import weiz.code.autenticacion.models.Rol;
import weiz.code.autenticacion.repositoris.PermisoRepository;
import weiz.code.autenticacion.repositoris.RolRepository;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/permiso")
public class PermisoController {

    @Autowired
    private PermisoRepository permisoRepository;

    @GetMapping
    public List<Permiso> findAll(){
        return this.permisoRepository.findAll();
    }

    @PostMapping
    public Permiso create(@RequestBody Permiso dataPermiso){
        return this.permisoRepository.save(dataPermiso);
    }

    @PutMapping("/{id}")
    public Permiso update(@PathVariable String id, @RequestBody Permiso dataPermiso){
        Permiso permiso = this.permisoRepository.findById(id).orElse(null);
        if (Objects.nonNull(permiso)){
            permiso.setUrl(dataPermiso.getUrl());
            permiso.setMetodo(dataPermiso.getMetodo());
            permiso.setPermiso_padre(dataPermiso.getPermiso_padre());
            return this.permisoRepository.save(permiso);
        } else {
            return null;
        }
    }

    @GetMapping("/{id}")
    public Permiso findById(@PathVariable String id){
        return this.permisoRepository.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        Permiso permiso = this.permisoRepository.findById(id).orElse(null);
        if(Objects.nonNull(permiso)){
            this.permisoRepository.delete(permiso);
        }
    }

}
