package com.rainbowflavor.roxyapiserver.comment;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentRequest {
    @NotEmpty
    private String content;
    @NotEmpty
    private String path;
    private String username;
    @NotEmpty
    @JsonIgnore
    private String password;
    private String checkingPassword;

    @JsonSetter
    public void setPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    @JsonGetter
    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        if (StringUtils.hasText(this.username)) {
            return this.username;
        }
        return "anonymous";
    }
}
