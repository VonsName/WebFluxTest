package com.exer.vons.block;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ： fjl
 * @date ： 2018/9/28/028 10:27
 */
public class NoobChain {
    public static List<Block> blockchain = new ArrayList<>();
    public static int difficulty = 3;
    public static Wallet walletA;
    public static Wallet walletB;

    public static void main(String[] args) {
        //调用Bouncey castle作为安全性的提供类
        Security.addProvider(new BouncyCastleProvider());
        walletA = new Wallet();
        walletB = new Wallet();
        //测试公钥和私钥
        System.out.println("Private and public keys:");
        System.out.println(StringUtil.getStringFromKey(walletA.getPrivateKey()));
        System.out.println(StringUtil.getStringFromKey(walletA.getPublicKey()));
        Transcation transcation = new Transcation(walletA.getPublicKey(), walletB.getPublicKey(), 3, null);
        //使用A的私钥进行签名
        transcation.generateSignature(walletA.getPrivateKey());
        //通过wallectA的公钥验证签名是否工作
        System.out.println("Is signature verified");
        System.out.println(transcation.verifiySignature());
    }
}
