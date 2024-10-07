package com.example.tobi.SpringbootBasicBoard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardWriteResponseDTO {
    private String url;
}
