package com.rainbowflavor.roxyapiserver.thrid_party;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Field {
    private String name;
    private String value;

    public void setValue(String value) {
        if (StringUtils.hasText(name) && StringUtils.hasText(value) == false) {
            this.value = "기준 임계치가 없습니다.";
        }else{
            this.value = value;
        }
    }
}
