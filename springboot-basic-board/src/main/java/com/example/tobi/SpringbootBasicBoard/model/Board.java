package com.example.tobi.SpringbootBasicBoard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.ErrorResponse;

import java.time.LocalDateTime;
@Builder
@Getter
@Setter
public class Board {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime created;

}
