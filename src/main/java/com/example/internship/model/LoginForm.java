package com.example.internship.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginForm {
	@NotBlank
	private String selectJob;

	@NotNull
	@Min(3)
	private Integer userId;

	@NotBlank
	private String password;
}
