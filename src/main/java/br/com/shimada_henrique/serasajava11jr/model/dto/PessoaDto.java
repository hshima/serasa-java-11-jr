package br.com.shimada_henrique.serasajava11jr.model.dto;

import br.com.shimada_henrique.serasajava11jr.model.Pessoa;
import lombok.*;
import org.springframework.util.StringUtils;

import static br.com.shimada_henrique.serasajava11jr.model.enums.ScoreEnum.*;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDto {
    private String nome;
    private String cidade;
    private String estado;
    private String scoreDescricao;

    public PessoaDto(Pessoa item) {
        this.nome = item.getNome();
        this.cidade = item.getCidade();
        this.estado = item.getEstado();
        // TODO: Melhorar o funcionamento dessa decisão através do pattern chain of responsibility.
        if (item.getScore() >= 701){
            this.scoreDescricao = StringUtils.capitalize(RECOMENDAVEL.toString());
        } else if (item.getScore() >= 501) {
            this.scoreDescricao = StringUtils.capitalize(ACEITAVEL.toString());
        } else if(item.getScore() >= 201){
            this.scoreDescricao = StringUtils.capitalize(INACEITAVEL.toString());
        } else if(item.getScore() >= 0){
            this.scoreDescricao = StringUtils.capitalize(INSUFICIENTE.toString());
        }
    }

    public static Pessoa convert(Pessoa pessoa){
        return Pessoa.builder()
                .idade(pessoa.getIdade())
                .estado(pessoa.getEstado())
                .cidade(pessoa.getCidade())
                .score(pessoa.getScore())
                .telefone(pessoa.getTelefone())
                .nome(pessoa.getNome())
                .build();

    }

}
