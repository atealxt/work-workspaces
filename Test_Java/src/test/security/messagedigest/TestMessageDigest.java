package test.security.messagedigest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import test.security.TestBase;

public class TestMessageDigest extends TestBase {

    protected MessageDigestSun messageDigestSun;
    protected MessageDigestBouncycastle messageDigestBouncycastle;
    protected MessageDigestCommonsCodec messageDigestCommonsCodec;

    @Override
    @Before
    public void setUp() {
        messageDigestSun = new MessageDigestSun();
        messageDigestBouncycastle = new MessageDigestBouncycastle();
        messageDigestCommonsCodec = new MessageDigestCommonsCodec();
    }

    @Test
    public void testMD() throws Exception {
        byte[] encodeMD2 = messageDigestSun.encodeMD2(str.getBytes());
        Assert.assertArrayEquals(encodeMD2, messageDigestSun.encodeMD2(str.getBytes()));
        byte[] encodeMD4 = messageDigestBouncycastle.encodeMD4(str.getBytes());
        Assert.assertArrayEquals(encodeMD4, messageDigestBouncycastle.encodeMD4(str.getBytes()));
        byte[] encodeMD5 = messageDigestSun.encodeMD5(str.getBytes());
        Assert.assertArrayEquals(encodeMD5, messageDigestSun.encodeMD5(str.getBytes()));

        String encodeMD2Hex = messageDigestBouncycastle.encodeMD2Hex(str.getBytes());
        logger.debug(encodeMD2Hex);
        Assert.assertEquals(encodeMD2Hex, messageDigestBouncycastle.encodeMD2Hex(str.getBytes()));
        String encodeMD4Hex = messageDigestBouncycastle.encodeMD4Hex(str.getBytes());
        logger.debug(encodeMD4Hex);
        Assert.assertEquals(encodeMD4Hex, messageDigestBouncycastle.encodeMD4Hex(str.getBytes()));
        String encodeMD5HexBouncycastle = messageDigestBouncycastle.encodeMD5Hex(str.getBytes());
        logger.debug(encodeMD5HexBouncycastle);
        Assert.assertEquals(encodeMD5HexBouncycastle, messageDigestBouncycastle.encodeMD5Hex(str.getBytes()));
        String encodeMD5HexCommonsCodec = messageDigestCommonsCodec.encodeMD5Hex(str);
        logger.debug(encodeMD5HexCommonsCodec);
        Assert.assertEquals(encodeMD5HexCommonsCodec, messageDigestCommonsCodec.encodeMD5Hex(str));
    }

    @Test
    public void testSHA() throws Exception {
        byte[] encodeSHA = messageDigestSun.encodeSHA(str.getBytes());
        Assert.assertArrayEquals(encodeSHA, messageDigestSun.encodeSHA(str.getBytes()));
        byte[] encodeSHA224 = messageDigestBouncycastle.encodeSHA224(str.getBytes());
        Assert.assertArrayEquals(encodeSHA224, messageDigestBouncycastle.encodeSHA224(str.getBytes()));
        byte[] encodeSHA256 = messageDigestSun.encodeSHA256(str.getBytes());
        Assert.assertArrayEquals(encodeSHA256, messageDigestSun.encodeSHA256(str.getBytes()));
        byte[] encodeSHA384 = messageDigestSun.encodeSHA384(str.getBytes());
        Assert.assertArrayEquals(encodeSHA384, messageDigestSun.encodeSHA384(str.getBytes()));
        byte[] encodeSHA512 = messageDigestSun.encodeSHA512(str.getBytes());
        Assert.assertArrayEquals(encodeSHA512, messageDigestSun.encodeSHA512(str.getBytes()));

        String encodeSHAHex = messageDigestBouncycastle.encodeSHAHex(str.getBytes());
        logger.debug(encodeSHAHex);
        Assert.assertEquals(encodeSHAHex, messageDigestBouncycastle.encodeSHAHex(str.getBytes()));
        String encodeSHA224Hex = messageDigestBouncycastle.encodeSHA224Hex(str.getBytes());
        logger.debug(encodeSHA224Hex);
        Assert.assertEquals(encodeSHA224Hex, messageDigestBouncycastle.encodeSHA224Hex(str.getBytes()));
        String encodeSHA256Hex = messageDigestBouncycastle.encodeSHA256Hex(str.getBytes());
        logger.debug(encodeSHA256Hex);
        Assert.assertEquals(encodeSHA256Hex, messageDigestBouncycastle.encodeSHA256Hex(str.getBytes()));
        String encodeSHA384Hex = messageDigestBouncycastle.encodeSHA384Hex(str.getBytes());
        logger.debug(encodeSHA384Hex);
        Assert.assertEquals(encodeSHA384Hex, messageDigestBouncycastle.encodeSHA384Hex(str.getBytes()));
        String encodeSHA512Hex = messageDigestBouncycastle.encodeSHA512Hex(str.getBytes());
        logger.debug(encodeSHA512Hex);
        Assert.assertEquals(encodeSHA512Hex, messageDigestBouncycastle.encodeSHA512Hex(str.getBytes()));

        String encodeSHAHexCommonsCodec1 = messageDigestCommonsCodec.encodeSHAHex(str.getBytes());
        logger.debug(encodeSHAHexCommonsCodec1);
        Assert.assertEquals(encodeSHAHexCommonsCodec1, messageDigestCommonsCodec.encodeSHAHex(str.getBytes()));
        String encodeSHA256HexCommonsCodec1 = messageDigestCommonsCodec.encodeSHA256Hex(str.getBytes());
        logger.debug(encodeSHA256HexCommonsCodec1);
        Assert.assertEquals(encodeSHA256HexCommonsCodec1, messageDigestCommonsCodec.encodeSHA256Hex(str.getBytes()));
        String encodeSHA384HexCommonsCodec1 = messageDigestCommonsCodec.encodeSHA384Hex(str.getBytes());
        logger.debug(encodeSHA384HexCommonsCodec1);
        Assert.assertEquals(encodeSHA384HexCommonsCodec1, messageDigestCommonsCodec.encodeSHA384Hex(str.getBytes()));
        String encodeSHA512HexCommonsCodec1 = messageDigestCommonsCodec.encodeSHA512Hex(str.getBytes());
        logger.debug(encodeSHA512HexCommonsCodec1);
        Assert.assertEquals(encodeSHA512HexCommonsCodec1, messageDigestCommonsCodec.encodeSHA512Hex(str.getBytes()));

        String encodeSHAHexCommonsCodec2 = messageDigestCommonsCodec.encodeSHAHex(str);
        logger.debug(encodeSHAHexCommonsCodec2);
        Assert.assertEquals(encodeSHAHexCommonsCodec2, messageDigestCommonsCodec.encodeSHAHex(str));
        String encodeSHA256HexCommonsCodec2 = messageDigestCommonsCodec.encodeSHA256Hex(str);
        logger.debug(encodeSHA256HexCommonsCodec2);
        Assert.assertEquals(encodeSHA256HexCommonsCodec2, messageDigestCommonsCodec.encodeSHA256Hex(str));
        String encodeSHA384HexCommonsCodec2 = messageDigestCommonsCodec.encodeSHA384Hex(str);
        logger.debug(encodeSHA384HexCommonsCodec2);
        Assert.assertEquals(encodeSHA384HexCommonsCodec2, messageDigestCommonsCodec.encodeSHA384Hex(str));
        String encodeSHA512HexCommonsCodec2 = messageDigestCommonsCodec.encodeSHA512Hex(str);
        logger.debug(encodeSHA512HexCommonsCodec2);
        Assert.assertEquals(encodeSHA512HexCommonsCodec2, messageDigestCommonsCodec.encodeSHA512Hex(str));
    }

    @Test
    public void testMAC() throws Exception {
        byte[] key = messageDigestSun.initHmacMD5Key();
        Assert.assertArrayEquals(messageDigestSun.encodeHmacMD5(str.getBytes(), key),
                                 messageDigestSun.encodeHmacMD5(str.getBytes(), key));
        String encode = messageDigestBouncycastle.encodeHmacMD5Hex(str.getBytes(), key);
        logger.debug(encode);
        Assert.assertEquals(encode, messageDigestBouncycastle.encodeHmacMD5Hex(str.getBytes(), key));
    }
}
