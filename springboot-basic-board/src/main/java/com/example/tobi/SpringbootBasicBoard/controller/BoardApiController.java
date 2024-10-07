package com.example.tobi.SpringbootBasicBoard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import com.example.tobi.SpringbootBasicBoard.dto.*;
import com.example.tobi.SpringbootBasicBoard.model.Board;
import com.example.tobi.SpringbootBasicBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping
    public BoardListResponseDTO getBoardList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // 게시글 목록 가져오기
        List<Board> boards = boardService.getBoardList(page, size);

        // 전체 게시글 수 가져오기
        int totalBoards = boardService.getTotalBoards();

        // 마지막 페이지 여부 계산
        boolean last = (page * size) >= totalBoards;

        return BoardListResponseDTO.builder()
                .boards(boards)
                .last(last)
                .build();
    }


    @GetMapping("/{id}")
    public BoardDetailResponseDTO getBoardDetail(@PathVariable long id) {
        Board boardDetail = boardService.getBoardDetail(id);
        return BoardDetailResponseDTO.builder()
                .title(boardDetail.getTitle())
                .content(boardDetail.getContent())
                .created(boardDetail.getCreated())
                .userId(boardDetail.getUserId())
                .build();
    }

    // 게시글 수정 요청 처리
    @PutMapping("/{id}")
    public ResponseEntity<BoardUpdateResponseDTO> updateBoard(@PathVariable long id, @RequestBody BoardUpdateRequestDTO boardUpdateRequestDTO) {
        try {
            boardService.updateBoard(id, boardUpdateRequestDTO);
            return ResponseEntity.ok(BoardUpdateResponseDTO.builder()
                    .message("게시글 수정 성공")
                    .build());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BoardUpdateResponseDTO.builder()
                    .message("게시글을 찾을 수 없습니다.")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BoardUpdateResponseDTO.builder()
                    .message("게시글 수정에 실패했습니다.")
                    .build());
        }
    }

    // 게시글 작성 요청 처리
    @PostMapping("/write")
    public ResponseEntity<BoardWriteResponseDTO> write(@RequestBody BoardWriteRequestDTO boardWriteRequestDTO) {
        boardService.write(boardWriteRequestDTO);
        return ResponseEntity.ok(
                BoardWriteResponseDTO.builder()
                        .url("/") // Redirect URL after successful write
                        .build()
        );
    }

    // 게시글 삭제 요청 처리
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardDeleteResponseDTO> deleteBoard(@PathVariable long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok(BoardDeleteResponseDTO.builder()
                .message("Board deleted successfully.")
                .build());
    }
}