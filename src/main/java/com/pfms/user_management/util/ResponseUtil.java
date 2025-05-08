package com.pfms.user_management.util;


import com.pfms.user_management.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private ResponseUtil() {}

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setCode("000");
        response.setData(data);
        response.setChecksum("");
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> accepted(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.ACCEPTED.value());
        response.setCode("000");
        response.setData(data);
        response.setChecksum("");
        return ResponseEntity.accepted().body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setCode("999");
        response.setData(data);
        response.setChecksum("");
        return ResponseEntity.badRequest().body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> noContent() {
        return ResponseEntity.noContent().build();
    }

    public static <T> ResponseEntity<ApiResponse<T>> internalServerError(String s) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setCode("999");
        response.setData(null);
        response.setChecksum("");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> badGateway(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.BAD_GATEWAY.value());
        response.setCode("999");
        response.setData(data);
        response.setChecksum("");
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }

}
