package com.fatesg.ads4.projetoMirror.enumeradores;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	AVALIADOR(2, "ROLE_AVALIADOR"),
	CLIENTE(3, "ROLE_CLIENTE");
	
	
	int cod;
	private String descricao;

    Perfil(int cod, String descricaoFuncioario) {
    	this.cod = cod;
        this.descricao = descricaoFuncioario;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
    public int getCod() {
		return cod;
	}

	public static Perfil toEnum(Integer cod) {
    	
    	if(cod == null) {
    		return null;
    	}
    	
    	for(Perfil x: Perfil.values()) {
    		
    		if(cod.equals(x.getCod())) {
    			return x;
    		}
    		
    	}
    	
    	throw new IllegalArgumentException("Id inválido: " + cod);
    	
    }
	
}