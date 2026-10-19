package com.jpmc.midascore.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRecordTest {

    @Test
    void trimsUserName() {
        UserRecord user = new UserRecord("  Alice  ", 12.5f);
        assertEquals("Alice", user.getName());
    }

    @Test
    void rejectsNullOrBlankUserName() {
        assertThrows(IllegalArgumentException.class, () -> new UserRecord(null, 1.0f));
        assertThrows(IllegalArgumentException.class, () -> new UserRecord("   ", 1.0f));
        UserRecord user = new UserRecord("Alice", 1.0f);
        assertThrows(IllegalArgumentException.class, () -> user.setName(""));
    }

    @Test
    void rejectsNegativeAndNonFiniteBalances() {
        assertThrows(IllegalArgumentException.class, () -> new UserRecord("Alice", -0.01f));
        assertThrows(IllegalArgumentException.class, () -> new UserRecord("Alice", Float.NaN));
        assertThrows(IllegalArgumentException.class, () -> new UserRecord("Alice", Float.POSITIVE_INFINITY));
        UserRecord user = new UserRecord("Alice", 0.0f);
        assertThrows(IllegalArgumentException.class, () -> user.setBalance(Float.NEGATIVE_INFINITY));
    }

    @Test
    void allowsZeroAndPositiveBalances() {
        assertEquals(0.0f, new UserRecord("Alice", 0.0f).getBalance());
        assertEquals(45.25f, new UserRecord("Bob", 45.25f).getBalance());
    }

    @Test
    void stringRepresentationIsWellFormed() {
        assertEquals("User[id=0, name='Alice', balance=12.500000]", new UserRecord("Alice", 12.5f).toString());
    }
}
