package com.rainbowflavor.roxyapiserver.userviews;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ViewsRequest {
    @NotNull
    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")
    private String ip;

    @NotEmpty
    @Schema(defaultValue = "/blog/post")
    private String urlPath;

    public String getIp(){
        if (StringUtils.hasText(ip)) {
            return ip;
        }
        return "";
    }
}
