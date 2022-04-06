package com.fatesg.ads4.projetoMirror.enumeradores;

public enum Tipo {
	
	JURIDICA("CNPJ"),FISICA("CPF");
	
	private String descricao;

    Tipo(String descricaoFuncioario) {
        descricao = descricaoFuncioario;
    }

    public String getDescricao() {
        return descricao;
    }
	
}
