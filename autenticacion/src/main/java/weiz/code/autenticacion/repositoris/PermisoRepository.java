package weiz.code.autenticacion.repositoris;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import weiz.code.autenticacion.models.Permiso;

public interface PermisoRepository extends MongoRepository<Permiso, String> {
    @Query("{'url':?0,'metodo':?1}")
    Permiso getPermiso(String url, String metodo);

}
