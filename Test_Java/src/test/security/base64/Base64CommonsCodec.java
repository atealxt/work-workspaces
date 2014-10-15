package test.security.base64;

import org.apache.commons.codec.binary.Base64;

public class Base64CommonsCodec implements IBase64, IBase64URL {

    @Override
    public String encode(final String data) throws Exception {
        byte[] b = Base64.encodeBase64(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

    @Override
    public String decode(final String data) throws Exception {
        byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

    @Override
    public String encode4URL(final String data) throws Exception {
        byte[] b = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

    @Override
    public String decode4URL(final String data) throws Exception {
        byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }
}
