package com.api.bank.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BaseModel {
	
	@Valid
	@Size(min=3, max=3)
	@NotBlank
	private String code;
	
	@Nullable
	private String description;
}
