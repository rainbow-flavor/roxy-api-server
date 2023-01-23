package com.rainbowflavor.roxyapiserver.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment mapToEntity(CommentRequest commentRequest);

    @Mapping(target="createdAt", source="comment.createdAt")
    CommentResponse mapToResponse(Comment comment);
}
