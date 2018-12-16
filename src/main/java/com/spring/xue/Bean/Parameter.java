package com.spring.xue.Bean;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Parameter {
    @NotNull
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String message;

}
