package online.u148.domain.exception;

/**
 * @author: dk
 * @date:2016/4/20
 * @email:dk-26@163.com
 */
public class JsonParseException extends RuntimeException {
    public JsonParseException(String detailMessage) {
        super(detailMessage);
    }

    public JsonParseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public JsonParseException(Throwable throwable) {
        super(throwable);
    }
    public JsonParseException(){}
}
