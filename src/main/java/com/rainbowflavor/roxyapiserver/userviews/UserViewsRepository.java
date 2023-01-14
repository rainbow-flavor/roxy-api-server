package com.rainbowflavor.roxyapiserver.userviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserViewsRepository extends JpaRepository<UserViews, Long> {
    Optional<UserViews> findByIpAndUrlPath(String ip, String urlPath);
}
