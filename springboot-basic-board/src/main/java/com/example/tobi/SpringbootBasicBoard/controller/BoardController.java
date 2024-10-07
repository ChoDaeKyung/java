package com.example.tobi.SpringbootBasicBoard.controller;

import com.example.tobi.SpringbootBasicBoard.model.Board;
import com.example.tobi.SpringbootBasicBoard.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService; // BoardService 선언

    @GetMapping("/")
    public String boardList(HttpSession session, Model model) {
        setSession(session, model);
        return "board-list";
    }

    @GetMapping("/detail")
    public String detail(
            @RequestParam("id") Long id,
            HttpSession session,
            Model model
    ) {
        setSession(session, model);
        model.addAttribute("id", id);
        return "board-detail";
    }

    @GetMapping("/edit/{id}")
    public String editBoard(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardDetail(id); // 게시글 정보 가져오기
        if (board == null) {
            // 게시글이 존재하지 않으면 오류 처리
            model.addAttribute("error", "게시글을 찾을 수 없습니다.");
            return "error"; // 오류 페이지로 리턴 (error.html을 만들면 좋습니다)
        }
        model.addAttribute("id", board.getId());
        model.addAttribute("title", board.getTitle());
        model.addAttribute("content", board.getContent());
        model.addAttribute("userId", board.getUserId());
        model.addAttribute("created", board.getCreated());
        return "board-edit"; // board-edit.html로 이동
    }

    @GetMapping("/write")
    public String write(HttpSession session, Model model) {
        setSession(session, model);
        return "board-write";
    }

    private void setSession(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");

        model.addAttribute("userName", userName);
        model.addAttribute("userId", userId);
    }
}