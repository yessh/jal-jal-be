package com.example.demo.member.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

	MEMBER("MEMBER"), SOCIAL("SOCIAL");

	private final String role;

}
