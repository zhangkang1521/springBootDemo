package org.zk.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.zk.BaseTest;

import static org.junit.Assert.*;

public class UserManagerTest extends BaseTest {

    @Autowired
    private UserManager userManager;

    @Test
    public void findByUsername() {
        userManager.findByUsername(null);
    }

    @Test
    public void findByUsernameByWeekend() {
        userManager.findByUsernameByWeekend(null);
    }

    @Test
    public void findByUsernameByWeekend2() {
        userManager.findByUsernameByWeekend2(null);
    }

    @Test
    public void update() {
        userManager.updateByWeekend(null);
    }
}