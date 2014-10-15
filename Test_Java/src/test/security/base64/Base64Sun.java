package test.security.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Sun implements IBase64 {

    @Override
    public String encode(final String data) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] b = data.getBytes(ENCODING);
        return encoder.encodeBuffer(b);
    }

    @Override
    public String decode(final String data) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(data);
        return new String(b, ENCODING);
    }
}
