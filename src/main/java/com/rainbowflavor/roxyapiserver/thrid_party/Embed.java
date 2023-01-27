package com.rainbowflavor.roxyapiserver.thrid_party;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Embed {
    private String title;
    private String description;
    private String url;
    private Integer color;
    private String timestamp;
    private List<Field> fields = new ArrayList<>();
    private Author author;
    private Footer footer;
    private Image image;
    private Thumbnail thumbnail;
}
