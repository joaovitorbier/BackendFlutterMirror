package com.fatesg.ads4.projetoMirror.enumeradores;

public enum FeedBackStatus {


	APLICADO("APLICADO"),ATRASADO("ATRASADO"),PENDENTE("PENDENTE");
	
	private String descricao;
	
	FeedBackStatus(String descricaoFuncioario) {
        descricao = descricaoFuncioario;
    }

    public String getDescricao() {
        return descricao;
    }
	
}
