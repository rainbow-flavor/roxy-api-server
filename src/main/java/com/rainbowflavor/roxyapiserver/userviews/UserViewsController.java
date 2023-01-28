package com.rainbowflavor.roxyapiserver.userviews;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Tag(name="views")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/views")
public class UserViewsController {
    private final UserViewsRepository repo;
    @Transactional
    @PostMapping
    public ResponseEntity<ViewResponse> getViewsCount(@RequestBody @Validated ViewsRequest request){

        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = servletRequest.getHeader("CF-Connecting-IP");
        if (StringUtils.hasText(ip) == false) {
            ip = "";
        }
        log.info("ip:{}, path:{}", ip, request.getUrlPath());

        UserViews userViews = repo.findByUrlPath(request.getUrlPath())
                .orElseGet(() -> repo.save(new UserViews(request.getUrlPath())));

        final String finalIp = ip;

        UserIp userIp = userViews.getContainIp(finalIp).orElseGet(() ->{
                    UserIp newUserIp = new UserIp(finalIp);
                    userViews.getUserIps().add(newUserIp);
                    userViews.increaseCount();
                    return newUserIp;
        });

        userViews.increaseCount();
        userIp.updateDateToNow(LocalDateTime.now());

        return ResponseEntity.ok(new ViewResponse(userIp.getIp(), userViews.getUrlPath(), userViews.getViewCount()));
    }
}
