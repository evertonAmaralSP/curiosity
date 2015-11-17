package br.com.curiosity.utils;


public enum ActionProbeEnum {
	
	L("L"),R("R"),M("M");
	
	private String action;

	ActionProbeEnum(final String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public static final ActionProbeEnum getEnumByAction(final String action) {
        for (ActionProbeEnum actionProbe: ActionProbeEnum.values()) {
            if (actionProbe.getAction().equalsIgnoreCase(action)) {
                return actionProbe;
            }
        }
        throw new IllegalArgumentException("Not found enum for a action: " + action);
    }
}
