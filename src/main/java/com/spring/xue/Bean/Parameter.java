package com.spring.xue.Bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Getter
//@Setter
//@ToString
@Data
public class Parameter {
    @NotNull
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String message;

}
