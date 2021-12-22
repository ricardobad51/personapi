package br.com.belemburitiricardo.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {

	HOME("Home"),
	MOBILE("Mobile"),
	COMMERCIAL("Commercial");
	
	PhoneType(String string) {
		this.description = "";
		// TODO Auto-generated constructor stub
	}

	private final String description;
}
