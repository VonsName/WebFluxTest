package com.exer.vons.block;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

/**
 * @author ： fjl
 * @date ： 2018/9/28/028 9:58
 */
public class Wallet {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public Wallet()
    {
        generateKey();
    }

    private void generateKey()
    {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom sha1PRNG = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec prime192v1 = new ECGenParameterSpec("prime192v1");
            keyGen.initialize(prime192v1,sha1PRNG);
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
