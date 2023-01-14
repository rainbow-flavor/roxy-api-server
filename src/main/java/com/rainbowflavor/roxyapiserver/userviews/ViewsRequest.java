package com.rainbowflavor.roxyapiserver.userviews;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ViewsRequest {
    @NotEmpty
    @Schema(defaultValue = "/blog/post")
    private String urlPath;
}
