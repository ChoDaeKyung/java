package com.example.tobi.SpringbootBasicBoard.dto;

import com.example.tobi.SpringbootBasicBoard.model.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardWriteRequestDTO {
    private String title;
    private String content;
    private String userId;

    public Board toBoard() { // Ensure this method is present
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .userId(this.userId)
                .build();
    }
}
