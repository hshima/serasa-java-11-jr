package br.com.shimada_henrique.serasajava11jr.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PESSOAS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    @NotBlank
    // Validação básica de padrão para nomes. Basicamente, não permite caracteres especiais como \[ ou \*. Ideogramas são aceitos.
    @Pattern(regexp = "^[\\p{L}'][ \\p{L}'-]*[\\p{L}]$", message = "[Nome] Erro: Inseridos caracteres não aceitos para nomes")
    private String nome;

    @Column(name = "telefone")
    @Pattern(regexp = "^[0-9]{2}\\s([0-9]|.{0})[0-9]{4}-[0-9]{4}$", message = "[Telefone] Erro: O telefone precisa respeitar o formato '01 23456-7890' ou '01 2345-6789'")
    private String telefone;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    @Size(min = 2, max = 2, message = "[Estado] Erro: Aceitos apenas 2 Dígitos para estado")
    private String estado;

    @Column(name = "score")
    @Min(value = 0, message = "[Score] Erro: Valor enviado é menor que Zero.")
    @Max(value = 1000, message = "[Score] Erro: Valor enviado é maior que Mil.")
    private Integer score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pessoa pessoa = (Pessoa) o;
        return id != null && Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
