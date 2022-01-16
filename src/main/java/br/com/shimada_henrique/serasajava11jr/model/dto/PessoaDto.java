package br.com.shimada_henrique.serasajava11jr.model.dto;

//import br.com.shimada_henrique.serasajava11jr.model.enums.ScoreEnum;
import lombok.*;
import br.com.shimada_henrique.serasajava11jr.model.Pessoa;
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
        if(item.getScore() <= 200){
            this.scoreDescricao = StringUtils.capitalize(INSUFICIENTE.toString());
        } else {
            if(item.getScore() <= 500){
                this.scoreDescricao = StringUtils.capitalize(INACEITAVEL.toString());
            } else {
                if (item.getScore() <= 700) {
                    this.scoreDescricao = StringUtils.capitalize(ACEITAVEL.toString());
                }else {
                    if (item.getScore() <= 1000) {
                        this.scoreDescricao = StringUtils.capitalize(RECOMENDAVEL.toString());
                    } else {
                        if (item.getScore() > 1000){
                            this.scoreDescricao = StringUtils.capitalize(ERRO_AO_ANALISAR.toString());
                        }
                    }
                }
            }
        }


    }

//    public ScoreEnum checkSuitability(){
//        return null;
//    }

//    public Pessoa convert(PessoaDto toConvert){
//        return Pessoa.builder()
//                .nome(this.nome)
//                .cidade(this.cidade)
//                .estado(this.estado)
//                .score(this.score)
//                .build();
//    }
}
