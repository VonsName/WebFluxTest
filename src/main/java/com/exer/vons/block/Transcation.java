package com.exer.vons.block;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ： fjl
 * @date ： 2018/9/28/028 10:04
 */
public class Transcation {
    private String transcationId;
    private PublicKey sendKey;
    private PublicKey receiveKey;
    private float value;
    /**
     * 数字签名
     */
    private byte[] signature;

    private List<TranscationInput> inputList = new ArrayList<>();
    private List<TranscationOutput> outputList = new ArrayList<>();
    private static int sequence = 0;


    /**
     * 对数据使用私钥加密
     * @param privateKey
     */
    public void generateSignature(PrivateKey privateKey) {
        String hash = StringUtil.digestHash(StringUtil.getStringFromKey(sendKey) + StringUtil.getStringFromKey(receiveKey) + Float.toString(value));
        signature = StringUtil.applyECDSASig(privateKey, hash);
    }

    /**
     * 验证签名
     * @return
     */
    public boolean verifiySignature()
    {
        String hash = StringUtil.digestHash(StringUtil.getStringFromKey(sendKey) + StringUtil.getStringFromKey(receiveKey) + Float.toString(value));
        return StringUtil.verifyECDSASig(sendKey,hash,signature);
    }

    private String calulateHash() {
        sequence++;
        String hash = StringUtil.digestHash(StringUtil.getStringFromKey(sendKey) + StringUtil.getStringFromKey(receiveKey) + Float.toString(value) + sequence);
        return hash;
    }

    public Transcation(PublicKey from, PublicKey to, float value, List<TranscationInput> inputList) {
        this.sendKey = from;
        this.receiveKey = to;
        this.value = value;
        this.inputList = inputList;
    }

    public String getTranscationId() {
        return transcationId;
    }

    public void setTranscationId(String transcationId) {
        this.transcationId = transcationId;
    }

    public PublicKey getSendKey() {
        return sendKey;
    }

    public void setSendKey(PublicKey sendKey) {
        this.sendKey = sendKey;
    }

    public PublicKey getReceiveKey() {
        return receiveKey;
    }

    public void setReceiveKey(PublicKey receiveKey) {
        this.receiveKey = receiveKey;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public List<TranscationInput> getInputList() {
        return inputList;
    }

    public void setInputList(List<TranscationInput> inputList) {
        this.inputList = inputList;
    }

    public List<TranscationOutput> getOutputList() {
        return outputList;
    }

    public void setOutputList(List<TranscationOutput> outputList) {
        this.outputList = outputList;
    }

    public static int getSequence() {
        return sequence;
    }

    public static void setSequence(int sequence) {
        Transcation.sequence = sequence;
    }
}
