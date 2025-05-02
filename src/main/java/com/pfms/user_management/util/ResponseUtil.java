package com.pfms.user_management.util;


import com.pfms.user_management.model.UserManagementApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private ResponseUtil() {}

    public static <T> ResponseEntity<UserManagementApiResponse<T>> ok(T data) {
        UserManagementApiResponse<T> response = new UserManagementApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setCode("000");
        response.setData(data);
        response.setChecksum("");
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<UserManagementApiResponse<T>> accepted(T data) {
        UserManagementApiResponse<T> response = new UserManagementApiResponse<>();
        response.setStatus(HttpStatus.ACCEPTED.value());
        response.setCode("000");
        response.setData(data);
        response.setChecksum("");
        return ResponseEntity.accepted().body(response);
    }

    public static <T> ResponseEntity<UserManagementApiResponse<T>> badRequest(T data) {
        UserManagementApiResponse<T> response = new UserManagementApiResponse<>();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setCode("999");
        response.setData(data);
        response.setChecksum("");
        return ResponseEntity.badRequest().body(response);
    }

    public static <T> ResponseEntity<UserManagementApiResponse<T>> noContent() {
        return ResponseEntity.noContent().build();
    }

    public static <T> ResponseEntity<UserManagementApiResponse<T>> internalServerError() {
        UserManagementApiResponse<T> response = new UserManagementApiResponse<>();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setCode("999");
        response.setData(null);
        response.setChecksum("");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public static <T> ResponseEntity<UserManagementApiResponse<T>> badGateway(T data) {
        UserManagementApiResponse<T> response = new UserManagementApiResponse<>();
        response.setStatus(HttpStatus.BAD_GATEWAY.value());
        response.setCode("999");
        response.setData(data);
        response.setChecksum("");
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }

}
