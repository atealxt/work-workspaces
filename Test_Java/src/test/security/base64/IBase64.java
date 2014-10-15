package test.security.base64;

public interface IBase64 {

    final static String ENCODING = "UTF-8";

    String encode(String data) throws Exception;

    String decode(String data) throws Exception;
}
