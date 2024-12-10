package org.uniupo.it.Model;

public class MessageFormat {
    private boolean isError;
    private String message;

    public MessageFormat(boolean isError, String message) {
        this.isError = isError;
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "MessageFormat{isError=" + isError + ", message=" + message + "}";
    }
}
