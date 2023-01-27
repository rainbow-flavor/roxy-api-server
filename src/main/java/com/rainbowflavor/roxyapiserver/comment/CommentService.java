package com.rainbowflavor.roxyapiserver.comment;

import com.rainbowflavor.roxyapiserver.thrid_party.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;
    private final DiscordService discordService;
    private final static int ROXY_BLUE = 9484536;

    @Transactional
    public CommentResponse saveComment(CommentRequest commentRequest) {
        Comment comment = mapper.mapToEntity(commentRequest);
        CommentResponse commentResponse = mapper.mapToResponse(commentRepository.save(comment));

        Embed embed = Embed.builder()
                .author(Author.builder().name(commentRequest.getUsername()).build())
                .title("Roxy Blog 에 덧글이 등록되었어요! ^^7")
                .description("[덧글 내용] : " + commentRequest.getContent())
                .color(ROXY_BLUE)
                .url("https://roxy.iro.ooo" + commentRequest.getPath())
                .footer(Footer.create("https://roxy.iro.ooo" + commentRequest.getPath(), null))
                .build();
        EmbedMessage embedMessage = EmbedMessage.builder()
                .embeds(List.of(embed))
                .username("Roxy Create Hooker")
                .build();

        discordService.send(embedMessage);

        return commentResponse;
    }

    public CommentResponse findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
        return mapper.mapToResponse(comment);
    }

    public List<CommentResponse> findByPath(String path) {
        return commentRepository.findByPath(path).stream()
                .sorted(Comparator.comparing(Comment::getCreatedAt).reversed())
                .map(mapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);

        Embed embed = Embed.builder()
                .author(Author.builder().name(comment.getUsername()).build())
                .title("Roxy Blog 이 삭제되었어요! T^T")
                .description("[덧글 내용] : " + comment.getContent())
                .color(ROXY_BLUE)
                .url("https://roxy.iro.ooo" + comment.getPath())
                .footer(Footer.create("https://roxy.iro.ooo" + comment.getPath(), null))
                .build();
        EmbedMessage embedMessage = EmbedMessage.builder()
                .embeds(List.of(embed))
                .username("Roxy Delete Hooker")
                .build();

        discordService.send(embedMessage);
    }

    @Transactional
    public CommentResponse updateComment(Long id, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        Comment updateComment = mapper.mapToEntity(commentRequest);

        if (StringUtils.hasText(commentRequest.getPassword())) {
            comment.updatePassword(commentRequest.getPassword());
        }
        CommentResponse commentResponse = mapper.mapToResponse(comment.updateComment(updateComment));

        Embed embed = Embed.builder()
                .author(Author.builder().name(commentResponse.getUsername()).build())
                .title("Roxy Blog 이 수정되었어요!")
                .description("[덧글 내용] : " + commentResponse.getContent())
                .color(ROXY_BLUE)
                .url("https://roxy.iro.ooo" + commentResponse.getPath())
                .footer(Footer.create("https://roxy.iro.ooo" + commentResponse.getPath(), null))
                .build();
        EmbedMessage embedMessage = EmbedMessage.builder()
                .embeds(List.of(embed))
                .username("Roxy Update Hooker")
                .build();

        discordService.send(embedMessage);

        return commentResponse;
    }
}
