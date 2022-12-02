package weiz.code.autenticacion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import weiz.code.autenticacion.models.Permiso;
import weiz.code.autenticacion.models.PermisoRol;
import weiz.code.autenticacion.models.Rol;
import weiz.code.autenticacion.repositoris.PermisoRepository;
import weiz.code.autenticacion.repositoris.PermisoRolRepository;
import weiz.code.autenticacion.repositoris.RolRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/asignar_permiso_a_rol")
public class PermisoRolController {
    @Autowired
    private PermisoRolRepository permisoRolRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    @GetMapping
    public List<PermisoRol> findAll(){
        return this.permisoRolRepository.findAll();
    }

    @PostMapping("/rol/{id_rol}/permiso/{id_permiso}")
    public PermisoRol createByRol(@PathVariable String id_rol, @PathVariable String id_permiso){
        Rol rol = rolRepository.findById(id_rol).orElse(null);
        Permiso permiso = permisoRepository.findById(id_permiso).orElse(null);
        if (Objects.nonNull(rol) && Objects.nonNull(permiso)){
            PermisoRol permisoRol = new PermisoRol();
            permisoRol.setRol(rol);
            permisoRol.setPermiso(permiso);
            return this.permisoRolRepository.save(permisoRol);
        } else {
            return null;
        }
    }

    @PutMapping("/{id_permiso_rol}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisoRol updateByRol(@PathVariable String id_permiso_rol, @PathVariable String id_rol,
                                  @PathVariable String id_permiso){
        PermisoRol permisoRol = permisoRolRepository.findById(id_permiso_rol).orElse(null);
        if (Objects.nonNull(permisoRol)){
            Rol rol = rolRepository.findById(id_rol).orElse(null);
            Permiso permiso = permisoRepository.findById(id_permiso).orElse(null);
            if (Objects.nonNull(rol) && Objects.nonNull(permiso)){
                permisoRol.setRol(rol);
                permisoRol.setPermiso(permiso);
                return this.permisoRolRepository.save(permisoRol);
            } else {
                return null;
            }
        }
        else {
            return null;
        }
    }
    
    @GetMapping("/{id}")
    public PermisoRol findById(@PathVariable String id){ return this.permisoRolRepository.findById(id).orElse(null);}

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        PermisoRol permisoRol = this.permisoRolRepository.findById(id).orElse(null);
        if(Objects.nonNull(permisoRol)){
            this.permisoRolRepository.delete(permisoRol);
        }
    }
    @GetMapping("validar-permiso/rol/{id_rol}")
    public PermisoRol getPermiso(@PathVariable String id_rol,
                                 @RequestBody Permiso dataPermiso){
        Permiso permiso=this.permisoRepository
                .getPermiso(dataPermiso.getUrl(),
                        dataPermiso.getMetodo());
        Rol rol = this.rolRepository.findById(id_rol).get();
        if (permiso != null && rol != null) {
            return this.permisoRolRepository.getPermisoRol(rol.get_id(),permiso.get_id());
        }else{
            return null;
        }
    }
}
