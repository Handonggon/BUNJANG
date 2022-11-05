package kr.co.study.bunjang.component.util;

import java.time.LocalDateTime;

import groovy.transform.builder.Builder;
import lombok.Data;

@Data
@Builder
public class AzureToken {
    
    LocalDateTime issue;

    Long expires;

    String token;
}