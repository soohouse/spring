package com.spring.mvc.user.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {

	private String account;
	private String password;
	private String name;
	private Timestamp regDate;
}
