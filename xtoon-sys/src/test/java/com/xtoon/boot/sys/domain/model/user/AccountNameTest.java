package com.xtoon.boot.sys.domain.model.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 类描述
 *
 * @author haoxin
 * @date 2021-02-25
 **/
class AccountNameTest {

    @Test
    void sameValueAs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AccountName("");
        });
        assertFalse(new AccountName("test").sameValueAs(new AccountName("test1")));
        assertTrue(new AccountName("test").sameValueAs(new AccountName("test")));
    }
}