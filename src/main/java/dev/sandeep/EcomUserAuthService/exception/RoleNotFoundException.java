package dev.sandeep.EcomUserAuthService.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
