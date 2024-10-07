package com.example.tobi.SpringbootBasicBoard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class BoardUpdateRequestDTO {
    private Long id;  // id 추가
    private String title;
    private String content;

    @Builder
    public BoardUpdateRequestDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
