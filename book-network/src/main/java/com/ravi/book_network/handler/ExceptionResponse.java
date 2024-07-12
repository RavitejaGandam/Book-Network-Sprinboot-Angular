package com.ravi.book_network.handler;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    private Integer businessErrorCodes;
    private String businessErrorDescription;
    private String error;
    private Set<String> validationError;
    private Map<String,String>errors;
}