package com.rainbowflavor.roxyapiserver.comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String content;
    private String username;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

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
