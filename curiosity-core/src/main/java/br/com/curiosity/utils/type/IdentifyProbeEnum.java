package br.com.curiosity.utils.type;


public enum IdentifyProbeEnum {

	POSEIDON("poseidon"),ATENAS("atenas");
	
	private String name;

	IdentifyProbeEnum(final String action) {
        this.name = action;
    }

    public String getName() {
        return name;
    }

}
