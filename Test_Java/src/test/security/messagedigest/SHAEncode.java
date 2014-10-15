package test.security.messagedigest;

public interface SHAEncode {

    byte[] encodeSHA(byte[] data) throws Exception;

    byte[] encodeSHA224(byte[] data) throws Exception;

    byte[] encodeSHA256(byte[] data) throws Exception;

    byte[] encodeSHA384(byte[] data) throws Exception;

    byte[] encodeSHA512(byte[] data) throws Exception;
}
