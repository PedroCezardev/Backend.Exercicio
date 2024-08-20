package primeira.aula.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

@Entity //Para definir como entidade
@Data 
public class Autor {
    
    @Id //Indica que é chave primária
    @GeneratedValue //Gera novos valores de ID automaticamente
    private Long id;

    @NonNull
    private String nome;

    @NonNull
    private String cpf;

    private Short idade;
}
