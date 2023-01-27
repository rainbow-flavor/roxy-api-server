package com.rainbowflavor.roxyapiserver.thrid_party;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Footer {
    private String text;
    @JsonProperty("icon_url")
    private String iconUrl;

    public static Footer create(@Nullable String text, @Nullable String iconUrl){
        Footer footer = new Footer();
        footer.text = text;
        footer.iconUrl = iconUrl;
        return footer;
    }
}
