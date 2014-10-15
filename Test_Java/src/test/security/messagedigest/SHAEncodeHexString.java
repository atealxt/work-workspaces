package test.security.messagedigest;

public interface SHAEncodeHexString {

    String encodeSHAHex(String data) throws Exception;

    String encodeSHA224Hex(String data) throws Exception;

    String encodeSHA256Hex(String data) throws Exception;

    String encodeSHA384Hex(String data) throws Exception;

    String encodeSHA512Hex(String data) throws Exception;
}
