package test.security.messagedigest;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MessageDigestSun implements MDEncode, SHAEncode, MACEncode {

    @Override
    public byte[] encodeMD2(final byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD2");
        return md.digest(data);
    }

    @Override
    @Deprecated
    public byte[] encodeMD4(final byte[] data) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] encodeMD5(final byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(data);
    }

    @Override
    public byte[] encodeSHA(final byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA");
        return md.digest(data);
    }

    @Override
    @Deprecated
    public byte[] encodeSHA224(final byte[] data) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] encodeSHA256(final byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(data);
    }

    @Override
    public byte[] encodeSHA384(final byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        return md.digest(data);
    }

    @Override
    public byte[] encodeSHA512(final byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        return md.digest(data);
    }

    @Override
    public byte[] initHmacMD5Key() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    @Override
    public byte[] encodeHmacMD5(final byte[] data, final byte[] key) throws NoSuchAlgorithmException,
            InvalidKeyException {
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }
}
