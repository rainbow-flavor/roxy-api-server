package com.rainbowflavor.roxyapiserver.thrid_party;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmbedMessage {
    private String content;
    private List<Embed> embeds = new ArrayList<>();
    private String username;
    @JsonProperty(value = "avatar_url")
    private String avatarUrl;
}
