package com.pfms.user_management.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"severity"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalExceptionResponse {

    private String errorType;
    private List<ValidationException> validationException;
    private ApplicationException applicationException;
    private String errorLog;
    private String severity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date timestamp;

    @Override
    public String toString() {
        return "GlobalExceptionResponse{" +
                "errorType='" + errorType + '\'' +
                ", validationException=" + validationException +
                ", applicationException=" + applicationException +
                ", errorLog='" + errorLog + '\'' +
                ", severity='" + severity + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

}

