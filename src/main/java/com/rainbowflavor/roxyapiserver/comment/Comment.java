package com.rainbowflavor.roxyapiserver.comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String content;
    private String username;
    private String password;

    public Comment updateComment(Comment comment) {
        this.content = comment.getContent();
        this.username = comment.getUsername();
        return this;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public Comment(String path, String content, String username, String password) {
        this.path = path;
        this.content = content;
        this.username = username;
        this.password = password;
    }
}
