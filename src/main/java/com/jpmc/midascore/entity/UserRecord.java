package com.jpmc.midascore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float balance;

    protected UserRecord() {
    }

    public UserRecord(String name, float balance) {
        setName(name);
        setBalance(balance);
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s', balance=%f]", id, name, balance);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getBalance() {
        return balance;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name must not be blank");
        }
        this.name = name.trim();
    }

    public void setBalance(float balance) {
        if (!Float.isFinite(balance) || balance < 0.0f) {
            throw new IllegalArgumentException("Balance must be a finite, non-negative amount");
        }
        this.balance = balance;
    }
}
