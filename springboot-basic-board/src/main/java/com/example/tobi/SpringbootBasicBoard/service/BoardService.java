package com.example.tobi.SpringbootBasicBoard.service;

import com.example.tobi.SpringbootBasicBoard.dto.BoardUpdateRequestDTO;
import com.example.tobi.SpringbootBasicBoard.dto.BoardWriteRequestDTO;
import com.example.tobi.SpringbootBasicBoard.mapper.BoardMapper;
import com.example.tobi.SpringbootBasicBoard.model.Board;
import com.example.tobi.SpringbootBasicBoard.model.Paging;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    public List<Board> getBoardList(int page, int size) {
        int offset = (page - 1) * size; // 페이지는 1부터 시작, offset 계산
        return boardMapper.selectBoardList(
                Paging.builder()
                        .offset(offset)
                        .size(size)
                        .build()
        );
    }

    public int getTotalBoards() {
        return boardMapper.countBoards(); // 총 게시글 수 반환
    }

    public Board getBoardDetail(long id) {
        return boardMapper.selectBoardDetail(id);
    }
    // 게시글 저장 메소드 추가
    public void write(BoardWriteRequestDTO boardWriteRequestDTO) {
        Board board = boardWriteRequestDTO.toBoard(); // DTO를 Board 객체로 변환
        boardMapper.write(board); // Mapper를 통해 데이터베이스에 저장
    }

    public void deleteBoard(Long id) {
        try {
            boardMapper.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Board with ID " + id + " does not exist.");
        }
    }

    public void updateBoard(Long id, BoardUpdateRequestDTO boardUpdateRequestDTO) throws NotFoundException {
//        Board existingBoard = boardMapper.selectBoardDetail(id);
//        if (existingBoard == null) {
//            throw new NotFoundException("게시글을 찾을 수 없습니다.");
//        }
//
//        // 수정할 필드만 업데이트
//        existingBoard.setTitle(boardUpdateRequestDTO.getTitle());
//        existingBoard.setContent(boardUpdateRequestDTO.getContent());
//
//        boardMapper.update(existingBoard); // 수정된 게시글을 DB에 업데이트
    }


}
