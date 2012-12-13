package test.security.base64;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;

public class Base64Bouncycastle implements IBase64, IBase64URL {

    @Override
    public String encode(final String data) throws Exception {
        byte[] b = Base64.encode(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

    @Override
    public String decode(final String data) throws Exception {
        byte[] b = Base64.decode(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

    @Override
    public String encode4URL(final String data) throws Exception {
        byte[] b = UrlBase64.encode(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

    @Override
    public String decode4URL(final String data) throws Exception {
        byte[] b = UrlBase64.decode(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }
}
