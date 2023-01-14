package com.rainbowflavor.roxyapiserver.userviews;

import lombok.Data;

@Data
public class ViewResponse {
    private String ip;
    private String urlPath;
    private int viewCount;

    public ViewResponse(String ip, String urlPath, int viewCount) {
        this.ip = ip;
        this.urlPath = urlPath;
        this.viewCount = viewCount;
    }
}
