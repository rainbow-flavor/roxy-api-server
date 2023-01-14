package com.rainbowflavor.roxyapiserver.userviews;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@CrossOrigin("*")
@Tag(name="views")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/views")
public class UserViewsController {
    private final UserViewsRepository repo;

    @Transactional
    @PostMapping
    public ResponseEntity<ViewResponse> getViewsCount(@RequestBody @Validated ViewsRequest request){
        log.info("ip:{}, path:{}", request.getIp(), request.getUrlPath());
        UserViews userViews = repo.findByIpAndUrlPath(request.getIp(), request.getUrlPath())
                .orElseGet(() -> {
                    UserViews newUserViews = new UserViews(request.getIp(), request.getUrlPath());
                    UserViews userViews1 = repo.saveAndFlush(newUserViews);
                    return newUserViews;
                });

        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        if(userViews.getUpdatedAt().isBefore(oneHourAgo)){
            userViews.getViewCountAfterIncrease();
        }

        return ResponseEntity.ok(new ViewResponse(userViews.getIp(), userViews.getUrlPath(), userViews.getViewCount()));
    }
}
