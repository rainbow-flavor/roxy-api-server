package com.rainbowflavor.roxyapiserver.comment;

import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private String content;
    private String username;
    private String path;
}
