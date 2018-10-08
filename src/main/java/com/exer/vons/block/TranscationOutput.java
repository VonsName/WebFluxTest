package com.exer.vons.block;

import java.security.PublicKey;

/**
 * @author ： fjl
 * @date ： 2018/9/28/028 10:07
 */
public class TranscationOutput {

    private String id;
    /**
     * 持有者的公钥
     */
    private PublicKey reciepient;
    /**
     * 持有者的金额
     */
    private float value;
    /**
     * 交易编号
     */
    private String parentTransactionId;


    public TranscationOutput(PublicKey reciepient, float value, String parentTransactionId) {
        this.reciepient = reciepient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.digestHash(StringUtil.getStringFromKey(reciepient) + Float.toString(value) + parentTransactionId);
    }

    /**
     * 用来验证是否属于你
     */
    public boolean isMine(PublicKey publicKey) {
        return (publicKey == reciepient);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PublicKey getReciepient() {
        return reciepient;
    }

    public void setReciepient(PublicKey reciepient) {
        this.reciepient = reciepient;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }
}
