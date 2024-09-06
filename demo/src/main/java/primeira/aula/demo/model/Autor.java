package primeira.aula.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity //Para definir como entidade
@Data 
@NoArgsConstructor
@AllArgsConstructor
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
