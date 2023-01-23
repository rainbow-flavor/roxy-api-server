package com.rainbowflavor.roxyapiserver.comment;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("https://roxy.iro.ooo")
@Tag(name = "comment")
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final PasswordEncoder passwordEncoder;
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @PostMapping("/create")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.saveComment(commentRequest);
        return ResponseEntity.created(URI.create("/api/v1/comment/" + commentResponse.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getCommentByPath(@RequestParam String path) {
        List<CommentResponse> commentResponses = commentService.findByPath(path);
        return ResponseEntity.ok(commentResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommendById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id,
                                           @RequestBody CommentRequest commentRequest) {
        if (validate(commentRequest.getCheckingPassword(), id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id,
                                                         @RequestBody CommentRequest commentRequest) {
        if (validate(commentRequest.getCheckingPassword(), id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        CommentResponse response = commentService.updateComment(id, commentRequest);
        return ResponseEntity.ok(response);
    }

    public boolean validate(String inputPassword, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
        return !passwordEncoder.matches(inputPassword, comment.getPassword());
    }
}
