package com.teamtbd.cosmetics.config.auth;

import com.teamtbd.cosmetics.member.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
	private final String name;
	private final String email;
	private final String picture;

	public SessionUser(Member member) {
		this.name = member.getUserName();
		this.email = member.getEmail();
		this.picture = member.getPicture();
	}
}
