package com.rainbowflavor.roxyapiserver.userviews;

import com.rainbowflavor.roxyapiserver.utils.StringConvertUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
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
    public ResponseEntity<ViewResponse> getViewsCount(HttpServletRequest servletRequest, @RequestBody @Validated ViewsRequest request){
        String ip = servletRequest.getHeader("CF-Connecting-IP");

        byte[] macAddress = getMacAddressBytes(ip);
        String macAddressString = StringConvertUtils.bytesToHex(macAddress);

        if (StringUtils.hasText(macAddressString) == false) {
            macAddressString = "";
        }

        log.info("macAddressString:{}, path:{}", macAddressString, request.getUrlPath());

        UserViews userViews = repo.findByUrlPath(request.getUrlPath())
                .orElseGet(() -> repo.save(new UserViews(request.getUrlPath())));

        final String finalIp = macAddressString;

        UserIp userIp = userViews.getContainIp(finalIp).orElseGet(() ->{
                    UserIp newUserIp = new UserIp(finalIp);
                    userViews.getUserIps().add(newUserIp);
                    userViews.increaseCount();
                    return newUserIp;
        });

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourAgo = now.minusHours(1);

        if(!userIp.getIp().equals("") && userIp.getUpdatedAt().isBefore(oneHourAgo)){
            userViews.increaseCount();
            userIp.updateDateToNow(LocalDateTime.now());
        }else{
            if (userIp.getIp().equals("")) {
                userViews.increaseCount();
                userIp.updateDateToNow(LocalDateTime.now());
            }
        }

        return ResponseEntity.ok(new ViewResponse(userIp.getIp(), userViews.getUrlPath(), userViews.getViewCount()));
    }

    private static byte[] getMacAddressBytes(String ip) {
        byte[] macAddress = null;
        try {
            InetAddress addr = null;
            addr = InetAddress.getByName(ip);
            macAddress = NetworkInterface.getByInetAddress(addr).getHardwareAddress();
        } catch (SecurityException | UnknownHostException | NullPointerException | SocketException e) {
            log.error("view count get mac address error ip={}, exception=", ip, e);
        }finally{
            if(macAddress == null){
                macAddress = new byte[0];
            }
        }
        return macAddress;
    }
}
