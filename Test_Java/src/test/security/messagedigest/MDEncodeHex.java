package test.security.messagedigest;

public interface MDEncodeHex {

    String encodeMD2Hex(byte[] data) throws Exception;

    String encodeMD4Hex(byte[] data) throws Exception;

    String encodeMD5Hex(byte[] data) throws Exception;
}
