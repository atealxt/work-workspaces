package test.security.messagedigest;

import java.security.MessageDigest;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class MessageDigestBouncycastle extends MessageDigestSun implements MDEncodeHex, SHAEncodeHex, MACEncode {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Override
    public byte[] encodeMD4(final byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD4");
        return md.digest(data);
    }

    @Override
    public String encodeMD2Hex(final byte[] data) throws Exception {
        byte[] b = encodeMD2(data);
        return new String(Hex.encode(b));
    }

    @Override
    public String encodeMD4Hex(final byte[] data) throws Exception {
        byte[] b = encodeMD4(data);
        return new String(Hex.encode(b));
    }

    @Override
    public String encodeMD5Hex(final byte[] data) throws Exception {
        byte[] b = encodeMD5(data);
        return new String(Hex.encode(b));
    }

    @Override
    public byte[] encodeSHA224(final byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-224");
        return md.digest(data);
    }

    @Override
    public String encodeSHAHex(final byte[] data) throws Exception {
        byte[] b = encodeSHA(data);
        return new String(Hex.encode(b));
    }

    @Override
    public String encodeSHA224Hex(final byte[] data) throws Exception {
        byte[] b = encodeSHA224(data);
        return new String(Hex.encode(b));
    }

    @Override
    public String encodeSHA256Hex(final byte[] data) throws Exception {
        byte[] b = encodeSHA256(data);
        return new String(Hex.encode(b));
    }

    @Override
    public String encodeSHA384Hex(final byte[] data) throws Exception {
        byte[] b = encodeSHA384(data);
        return new String(Hex.encode(b));
    }

    @Override
    public String encodeSHA512Hex(final byte[] data) throws Exception {
        byte[] b = encodeSHA512(data);
        return new String(Hex.encode(b));
    }

    public String encodeHmacMD5Hex(final byte[] data, final byte[] key) throws Exception {
        byte[] b = encodeHmacMD5(data, key);
        return new String(Hex.encode(b));
    }
}
