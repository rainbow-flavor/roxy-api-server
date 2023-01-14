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
@Table(uniqueConstraints =
@UniqueConstraint(
        name = "UNIQUE_KEY_IP_AND_URL_PATH",
        columnNames = {"ip", "urlPath"}))
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserViews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String ip;
    @Column(nullable = false)
    private String urlPath;

    private Integer viewCount = 1;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public UserViews(String ip, String urlPath) {
        this.ip = ip;
        this.urlPath = urlPath;
    }

    public Integer getViewCountAfterIncrease(){
        return this.viewCount++;
    }
}
