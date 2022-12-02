package weiz.code.autenticacion.repositoris;

import org.springframework.data.mongodb.repository.MongoRepository;
import weiz.code.autenticacion.models.Rol;

public interface RolRepository extends MongoRepository<Rol, String> {
}
