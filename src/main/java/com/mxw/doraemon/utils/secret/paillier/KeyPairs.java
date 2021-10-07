package com.mxw.doraemon.utils.secret.paillier;

/**
 * @description:
 * @author: Kevin Zhang
 * @time: 2020/11/25 5:04 下午
 */
public class KeyPairs implements java.io.Serializable{
    private String privateKey;
    private String publicKey;

    public KeyPairs(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * Returns a reference to the public key component of this key pair.
     *
     * @return a reference to the public key.
     */
    public String getPublic() {
        return publicKey;
    }

    /**
     * Returns a reference to the private key component of this key pair.
     *
     * @return a reference to the private key.
     */
    public String getPrivate() {
        return privateKey;
    }
}
