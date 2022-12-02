package weiz.code.autenticacion.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor()
@AllArgsConstructor()
public class Usuario {
    @Id
    private String _id;
    private String seudonimo;
    private String correo;
    private String clave;
    @DBRef
    private Rol rol;
}
