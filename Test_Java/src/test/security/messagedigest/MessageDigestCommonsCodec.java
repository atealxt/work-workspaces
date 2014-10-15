package test.security.messagedigest;

import org.apache.commons.codec.digest.DigestUtils;

public class MessageDigestCommonsCodec extends MessageDigestSun implements SHAEncodeHex, SHAEncodeHexString {

    public String encodeMD5Hex(final String data) throws Exception {
        return DigestUtils.md5Hex(data);
    }

    @Override
    public String encodeSHAHex(final String data) throws Exception {
        return DigestUtils.shaHex(data);
    }

    @Override
    @Deprecated
    public String encodeSHA224Hex(final String data) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String encodeSHA256Hex(final String data) throws Exception {
        return DigestUtils.sha256Hex(data);
    }

    @Override
    public String encodeSHA384Hex(final String data) throws Exception {
        return DigestUtils.sha384Hex(data);
    }

    @Override
    public String encodeSHA512Hex(final String data) throws Exception {
        return DigestUtils.sha512Hex(data);
    }

    @Override
    public String encodeSHAHex(final byte[] data) throws Exception {
        return DigestUtils.shaHex(data);
    }

    @Override
    @Deprecated
    public String encodeSHA224Hex(final byte[] data) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String encodeSHA256Hex(final byte[] data) throws Exception {
        return DigestUtils.sha256Hex(data);
    }

    @Override
    public String encodeSHA384Hex(final byte[] data) throws Exception {
        return DigestUtils.sha384Hex(data);
    }

    @Override
    public String encodeSHA512Hex(final byte[] data) throws Exception {
        return DigestUtils.sha512Hex(data);
    }
}
