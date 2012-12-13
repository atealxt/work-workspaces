package test.security.messagedigest;

/** MAC算法主要是在摘要算法基础上增加了密钥 */
public interface MACEncode {

    byte[] initHmacMD5Key() throws Exception;

    byte[] encodeHmacMD5(byte[] data, byte[] key) throws Exception;

    // MAC的其他算法，这里略去实现，实际使用时再查阅。
    // SUN:
    // SHA1     KeyGenerator.getInstance("HMacTiger")
    // SHA256   HmacSHA256
    // SHA384   HmacSHA384
    // SHA512   HmacSHA512
    // bouncycastle:
    // MD2      HmacMD2
    // MD4      HmacMD4
    // SHA224   HmacSHA224
}
