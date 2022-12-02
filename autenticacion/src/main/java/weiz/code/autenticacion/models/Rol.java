package weiz.code.autenticacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor()
@AllArgsConstructor()
public class Rol {
    @Id
    private String _id;
    private String nombre;
    private String descripcion;
}
