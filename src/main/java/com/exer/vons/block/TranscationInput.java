package com.exer.vons.block;

/**
 * @author ： fjl
 * @date ： 2018/9/28/028 10:07
 */
public class TranscationInput {
    /**
     * 指向交易输出类 -> transactionId
     */
    public String transactionOutputId;
    /**
     * 包含所有未使用的交易输出
     */
    public TranscationOutput UTXO;

    public TranscationInput(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }
}
