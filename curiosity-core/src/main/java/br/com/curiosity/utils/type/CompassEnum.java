package br.com.curiosity.utils.type;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public enum CompassEnum {
	
	N("N", "W", "E"),
    W("W", "S", "N"),
    S("S", "E", "W"),
    E("E", "N", "S");

    private String direction;
    private String left;
    private String right;

    CompassEnum(final String direction, final String left,
                          final String right) {
        this.direction = direction;
        this.left = left;
        this.right = right;
    }

    public String getDirection() {
        return direction;
    }

    public CompassEnum getLeft() {
        return CompassEnum.getEnumByDirection(left);
    }

    public CompassEnum getRight() {
        return CompassEnum.getEnumByDirection(right);
    }

    public static final CompassEnum getEnumByDirection(final String direction) {
    	if (StringUtils.isEmpty(direction) || !Pattern.matches("^[NESW]?$", direction))
    		throw new IllegalArgumentException("Not found enum for a direction: " + direction);
    	
        for (CompassEnum compass : CompassEnum.values()) {
            if (compass.getDirection().equalsIgnoreCase(direction)) {
                return compass;
            }
        }
        return null;
    }
	
}
