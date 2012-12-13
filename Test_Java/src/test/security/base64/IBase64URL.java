package test.security.base64;

public interface IBase64URL {

    String encode4URL(String data) throws Exception;

    String decode4URL(String data) throws Exception;
}
