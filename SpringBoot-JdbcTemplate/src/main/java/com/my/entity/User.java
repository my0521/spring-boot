package com.my.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class User {
	private int id;
	private String name;
	private int age;
}
