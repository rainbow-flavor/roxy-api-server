package com.rainbowflavor.roxyapiserver.userviews;

import com.rainbowflavor.roxyapiserver.config.TestJpaConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@DataJpaTest
@ContextConfiguration(classes = TestJpaConfig.class)
class UserViewsTest {
    private static final String IP = "127.0.0.1";
    private static final String PATH = "/";
    @Autowired
    UserViewsRepository repo;

    @Test
    @Transactional
    void insertTest(){
        UserViews userViews = repo.findByUrlPath(PATH)
                .orElseGet(() -> {
                    UserViews newUserViews = new UserViews(PATH);
                    repo.saveAndFlush(newUserViews);
                    return newUserViews;
                });

        Assertions.assertNotNull(userViews.getId());
        Assertions.assertEquals(PATH,userViews.getUrlPath());
    }

    @Test
    @Transactional
    void emptyIpInsert(){
        UserViews userViews = repo.findByUrlPath(PATH)
                .orElseGet(() -> {
                    UserViews newUserViews = new UserViews(PATH);
                    repo.saveAndFlush(newUserViews);
                    return newUserViews;
                });

        Assertions.assertNotNull(userViews.getId());
        Assertions.assertEquals(PATH,userViews.getUrlPath());
    }
}