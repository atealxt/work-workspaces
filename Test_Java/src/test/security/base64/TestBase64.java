package test.security.base64;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import test.security.TestBase;

public class TestBase64 extends TestBase {

    protected Base64Sun base64Sun;
    protected Base64Bouncycastle base64Bouncycastle;
    protected Base64CommonsCodec base64CommonsCodec;

    @Override
    @Before
    public void setUp() {
        base64Sun = new Base64Sun();
        base64Bouncycastle = new Base64Bouncycastle();
        base64CommonsCodec = new Base64CommonsCodec();
    }

    @Test
    public void testBase64() throws Exception {
        String encodeSun = base64Sun.encode(str);
        logger.debug(encodeSun);
        String decodeSun = base64Sun.decode(encodeSun);
        Assert.assertEquals(str, decodeSun);

        String encodeBouncycastle = base64Bouncycastle.encode(str);
        logger.debug(encodeBouncycastle);
        String decodeBouncycastle = base64Bouncycastle.decode(encodeBouncycastle);
        Assert.assertEquals(str, decodeBouncycastle);

        String encodeCommonsCodec = base64CommonsCodec.encode(str);
        logger.debug(encodeCommonsCodec);
        String decodeCommonsCodec = base64CommonsCodec.decode(encodeCommonsCodec);
        Assert.assertEquals(str, decodeCommonsCodec);
    }

    @Test
    public void testBase64Url() throws Exception {
        String encodeBouncycastle = base64Bouncycastle.encode4URL(p);
        logger.debug(url + encodeBouncycastle);
        String decodeBouncycastle = base64Bouncycastle.decode4URL(encodeBouncycastle);
        Assert.assertEquals(p, decodeBouncycastle);

        String encodeCommonsCodec = base64CommonsCodec.encode4URL(p);
        logger.debug(url + encodeCommonsCodec);
        String decodeCommonsCodec = base64CommonsCodec.decode4URL(encodeCommonsCodec);
        Assert.assertEquals(p, decodeCommonsCodec);
    }
}
