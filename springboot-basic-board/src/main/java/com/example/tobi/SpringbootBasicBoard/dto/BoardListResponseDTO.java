package com.example.tobi.SpringbootBasicBoard.dto;

import com.example.tobi.SpringbootBasicBoard.model.Board;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardListResponseDTO {
    List<Board> boards;
    boolean last;
}
