package io.github.jkmthiago.travelandaccommodationapi.enumeration;

public enum AccommodationTypeEnum {
	
	RETURN("RETURN"), ONE_WAY("ONE-WAY"), MULTI_CITY("MULTI-CITY");
	
	private String value;
	
	private AccommodationTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
    
	public static AccommodationTypeEnum getEnum(String value) {
		
		for(AccommodationTypeEnum t : values()) {
			if(value.equals(t.getValue())) {
				return t;
			}
		}
		
		throw new RuntimeException("Type not foundo.");
	}

}
