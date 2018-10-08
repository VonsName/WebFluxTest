package com.exer.vons.block;

import com.google.gson.GsonBuilder;
import org.springframework.util.ObjectUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ： fjl
 * @date ： 2018/9/28/028 8:40
 */
public class Block {
    private static int difficult = 3;
    private String data;
    private String previousHash;
    private long timestamp;
    private String hash;
    private Integer nonce = 0;


    private String calauateHash() {
        String message = Long.toString(timestamp) + previousHash + data + Integer.toString(nonce);
        return digestHash(message);
    }


    private String digestHash(String input) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            byte[] hash = instance.digest(input.getBytes());
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hash.length == 1) {
                    sb.append(0);
                }
                sb.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 挖矿
     *
     * @param difficulty
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equalsIgnoreCase(target)) {
            nonce++;
            hash = calauateHash();
        }
        System.out.println("mineBlock hash finish:" + hash);
    }


    /**
     * 判断区块连是否有效
     *
     * @param list
     * @return
     */
    public static boolean chainValid(List<Block> list) {
        Block current = null;
        Block previous = null;
        String hashTarget = new String(new char[difficult]).replace('\0', '0');
        for (int i = 1; i < list.size(); i++) {
            current = list.get(i);
            previous = list.get(i - 1);
        }
        if (!ObjectUtils.isEmpty(current) && !current.getHash().equalsIgnoreCase(current.calauateHash())) {
            System.out.println("当前区块信息不匹配");
            return false;
        }
        if (current != null && previous != null) {
            if (!current.getPreviousHash().equalsIgnoreCase(previous.getHash())) {
                System.out.println("数据被修改,与前一个区块的信息不匹配");
                return false;
            }
        }
        if (!current.hash.substring(0, difficult).equals(hashTarget)) {
            System.out.println("This block hasn't been mined");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Block> blockChain = new ArrayList<>();
        Block first = new Block("first", "0");
        blockChain.add(first);
        System.out.println("try...firstHash:");
        blockChain.get(0).mineBlock(difficult);
        Block second = new Block("second", first.getHash());
        blockChain.add(second);
        System.out.println("try...secondHash:");
        blockChain.get(1).mineBlock(difficult);
        Block three = new Block("three", second.getHash());
        blockChain.add(three);
        System.out.println("try...threeHash:");
        blockChain.get(2).mineBlock(difficult);
        System.out.println("blockIsValid:" + Boolean.toString(chainValid(blockChain)));
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println(json);
    }

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.hash = calauateHash();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
