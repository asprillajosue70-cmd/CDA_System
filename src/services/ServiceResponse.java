
package services;

public class ServiceResponse {

    private boolean success;
    private String message;

    public ServiceResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ServiceResponse success(String message) {
        return new ServiceResponse(true, message);
    }

    public static ServiceResponse error(String message) {
        return new ServiceResponse(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

