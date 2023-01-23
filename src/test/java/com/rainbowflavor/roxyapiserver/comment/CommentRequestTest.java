package com.rainbowflavor.roxyapiserver.comment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class CommentRequestTest {
    @Test
    @DisplayName("toPojoObjectTest")
    void toPojoObjectTest() throws JsonProcessingException {
        ObjectMapper ob = new ObjectMapper();

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setUsername("irostub");
        commentRequest.setContent("test content");
        commentRequest.setCheckingPassword("1234qwer");
        commentRequest.setPath("/");
        commentRequest.setPassword("qwer1234");

        String json = ob.writeValueAsString(commentRequest);

        CommentRequest testObject = ob.readValue(json, CommentRequest.class);

        assertNotEquals("qwer1234", testObject.getPassword());
        assertEquals("irostub", testObject.getUsername());
        assertEquals("test content", testObject.getContent());
        assertEquals("/", testObject.getPath());
        assertEquals("1234qwer", testObject.getCheckingPassword());
    }

    @Test
    @DisplayName("encode and match test")
    void encodeAndMatchTest() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode("qwer1234");

        Assertions.assertTrue(encoder.matches("qwer1234", encoded));
        Assertions.assertNotEquals("qwer1234", encoded);

        String encoded2 = encoder.encode("qwer1234");
        Assertions.assertNotEquals(encoded, encoded2);
    }
}