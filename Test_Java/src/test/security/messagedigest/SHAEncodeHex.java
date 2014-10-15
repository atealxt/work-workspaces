package test.security.messagedigest;

public interface SHAEncodeHex {

    String encodeSHAHex(byte[] data) throws Exception;

    String encodeSHA224Hex(byte[] data) throws Exception;

    String encodeSHA256Hex(byte[] data) throws Exception;

    String encodeSHA384Hex(byte[] data) throws Exception;

    String encodeSHA512Hex(byte[] data) throws Exception;
}
