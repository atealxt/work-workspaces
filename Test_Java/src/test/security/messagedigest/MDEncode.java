package test.security.messagedigest;

public interface MDEncode {

    byte[] encodeMD2(byte[] data) throws Exception;

    byte[] encodeMD4(byte[] data) throws Exception;

    byte[] encodeMD5(byte[] data) throws Exception;
}
