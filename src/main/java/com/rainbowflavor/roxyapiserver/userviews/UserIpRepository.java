package com.rainbowflavor.roxyapiserver.userviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserIpRepository extends JpaRepository<UserIp, Long> {
}
