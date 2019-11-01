package com.use.entity;

/**
 * @Description: <br>
 * @Date: 2019/10/31 20:59 <br>
 * @Author: luoxz <br>
 * @Version: 1.0 <br>
 */
public class Transaction {
    private final int id;
    private final Integer value;
    private final Type type;

    public Transaction(int id, Integer value, Type type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public enum Type {
        A, B, C, D, GEOCERY
    }

    public int getId() {return id;}
    public Integer getValue() {return value;}
    public Type getType() {return type;}
}
