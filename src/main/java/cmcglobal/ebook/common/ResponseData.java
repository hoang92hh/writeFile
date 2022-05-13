package cmcglobal.ebook.common;

public class ResponseData {
    private Object data;
    private String message;
    private String code;
    private String status;

    public ResponseData() {
    }

    public ResponseData(Object data, String message, String code, String status) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
