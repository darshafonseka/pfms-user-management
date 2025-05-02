package com.pfms.user_management.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationException {

    private String field;
    private String message;

    @Override
    public String toString() {
        return "ValidationException{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
