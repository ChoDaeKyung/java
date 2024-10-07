package com.example.tobi.SpringbootBasicBoard.mapper;

import com.example.tobi.SpringbootBasicBoard.model.Board;
import com.example.tobi.SpringbootBasicBoard.model.Paging;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> selectBoardList(Paging page);
    int countBoards();
    Board selectBoardDetail(long id);
    void write(Board board); // 성공 시 1, 실패 시 0을 반환
    void delete(Long id);
    void update(Board board);
}
