package weiz.code.autenticacion.repositoris;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import weiz.code.autenticacion.models.PermisoRol;

public interface PermisoRolRepository extends MongoRepository<PermisoRol, String> {
    @Query("{'rol.$id': ObjectId(?0),'permiso.$id': ObjectId(?1)}")
    PermisoRol getPermisoRol(String id_rol,String id_permiso);
}
