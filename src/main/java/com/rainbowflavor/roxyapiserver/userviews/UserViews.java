package com.rainbowflavor.roxyapiserver.userviews;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Table(uniqueConstraints =
@UniqueConstraint(
        name = "UNIQUE_KEY_URL_PATH",
        columnNames = {"urlPath"}))
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserViews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String urlPath;
    private Integer viewCount = 1;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<UserIp> userIps = new ArrayList<>();

    public UserViews(String urlPath) {
        this.urlPath = urlPath;
    }
    public Optional<UserIp> getContainIp(String ip){
        return this.userIps.stream().filter(i -> i.getIp().equals(ip)).findAny();
    }
    public void increaseCount(){
        this.viewCount++;
    }
}
