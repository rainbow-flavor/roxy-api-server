package com.rainbowflavor.roxyapiserver.comment;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment mapToEntity(CommentRequest commentRequest);

    CommentResponse mapToResponse(Comment comment);
}
