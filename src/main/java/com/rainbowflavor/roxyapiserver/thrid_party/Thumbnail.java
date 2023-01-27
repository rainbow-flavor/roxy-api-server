package com.rainbowflavor.roxyapiserver.thrid_party;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Thumbnail {
    private String url;
}
