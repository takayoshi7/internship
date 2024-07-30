package com.example.internship.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
@Component
@SessionScope
public class LoginForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String selectJob;

	@NotNull
	@Min(3)
	private Integer userId;

	@NotBlank
	private String password;
}
