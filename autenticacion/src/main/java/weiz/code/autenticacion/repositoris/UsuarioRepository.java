package weiz.code.autenticacion.repositoris;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import weiz.code.autenticacion.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    @Query("{'correo': ?0}")
    public Usuario getUserByEmail(String correo);

}
