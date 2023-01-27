package com.rainbowflavor.roxyapiserver.thrid_party;

import com.rainbowflavor.roxyapiserver.config.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiscordService {
    private final AppConfig appConfig;
    private final RestTemplate restTemplate;

    public boolean send(EmbedMessage embedMessage){
        ResponseEntity<String> result = restTemplate
                .postForEntity(appConfig.getDiscord().getUrl(), embedMessage, String.class);
        if(result.getStatusCode().is2xxSuccessful() == false){
            log.error("fail to send discord hook message,request embed message={},response message={}",embedMessage, result.getBody());
            return false;
        }
        return true;
    }

}
