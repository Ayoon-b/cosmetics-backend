package com.teamtbd.cosmetics.config;

public enum AuthProvider {
	FACEBOOK("facebook"),
	GOOGLE("google"),
	KAKAO("kakao"),
	NAVER("naver");

	private final String ROLE_PREFIX = "ROLE_";
	private final String name;

	AuthProvider(String name) {this.name = name;}

	public String getRoleType() {return ROLE_PREFIX + name.toUpperCase();}

	public String getValue() {return name;}

	public boolean isEquals(String authority) {return this.getRoleType().equals(authority);}
}

