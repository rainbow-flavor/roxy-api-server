package com.rainbowflavor.roxyapiserver.userviews;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserViewsRepository extends JpaRepository<UserViews, Long> {
    @EntityGraph(attributePaths = "userIps")
    Optional<UserViews> findByUrlPath(String urlPath);
}
