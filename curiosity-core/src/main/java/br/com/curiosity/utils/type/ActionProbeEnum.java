package br.com.curiosity.utils.type;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


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
    	if (StringUtils.isEmpty(action) || !Pattern.matches("^[LRM]?$", action))
    		throw new IllegalArgumentException("Not found enum for a action: " + action);
    	
        for (ActionProbeEnum actionProbe: ActionProbeEnum.values()) {
            if (actionProbe.getAction().equalsIgnoreCase(action)) {
                return actionProbe;
            }
        }
        return null;
    }
}
