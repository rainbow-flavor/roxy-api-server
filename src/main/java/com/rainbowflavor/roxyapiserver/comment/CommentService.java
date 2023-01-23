package com.rainbowflavor.roxyapiserver.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;
    @Transactional
    public CommentResponse saveComment(CommentRequest commentRequest) {
        Comment comment = mapper.mapToEntity(commentRequest);
        return mapper.mapToResponse(commentRepository.save(comment));
    }

    public CommentResponse findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
        return mapper.mapToResponse(comment);
    }

    public List<CommentResponse> findByPath(String path) {
        return commentRepository.findByPath(path).stream()
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public CommentResponse updateComment(Long id, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        Comment updateComment = mapper.mapToEntity(commentRequest);
        if (StringUtils.hasText(commentRequest.getPassword())) {
            comment.updatePassword(commentRequest.getPassword());
        }
        return mapper.mapToResponse(comment.updateComment(updateComment));
    }
}
