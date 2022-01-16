package br.com.shimada_henrique.serasajava11jr.model.enums;

import lombok.Getter;

@Getter
public enum ScoreEnum {
    INSUFICIENTE(200),
    INACEITAVEL(500),
    ACEITAVEL(700),
    RECOMENDAVEL(1000),
    ERRO_AO_ANALISAR(1001);

    ScoreEnum(int i) {}
}