package com.fatesg.ads4.projetoMirror.enumeradores;

public enum Status {

	ATIVO("ATIVO"),INATIVO("INATIVO"),BLOQUEADO("BLOQUEADO");
	
	private String descricao;
	
	Status(String descricaoFuncioario) {
        descricao = descricaoFuncioario;
    }

    public String getDescricao() {
        return descricao;
    }
	
}