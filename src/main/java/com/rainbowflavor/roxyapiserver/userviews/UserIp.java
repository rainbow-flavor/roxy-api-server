package com.rainbowflavor.roxyapiserver.userviews;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserIp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ip = "";
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void updateDateToNow(LocalDateTime now){
        this.updatedAt = now;
    }
    public UserIp(String ip) {
        LocalDateTime now = LocalDateTime.now();
        this.ip = ip;
        createdAt = now;
        updatedAt = now;
    }
}
