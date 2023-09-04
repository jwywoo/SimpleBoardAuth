package com.example.simpleboardauth.board.controller;


import com.example.simpleboardauth.board.dto.BoardRequestDto;
import com.example.simpleboardauth.board.dto.BoardResponseDto;
import com.example.simpleboardauth.board.service.BoardService;
import com.example.simpleboardauth.board.dto.StringResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // Get
    // Get all
    @GetMapping("/board")
    public ResponseEntity<List<BoardResponseDto>> boardList() {
        return ResponseEntity.ok(boardService.boardList());
    }

    // Get single
    @GetMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> boardDetail(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.boardDetail(id));
    }

    // Create -> authorization required (Request Header)
    @PostMapping("/board")
    public ResponseEntity<BoardResponseDto> boardCreate(@RequestBody BoardRequestDto requestDto, HttpServletRequest req) {
        return ResponseEntity.ok(boardService.boardCreate(requestDto, req));
    }

    // Update -> authorization required (Request Header)
    @PutMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> boardUpdate(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest req) {
        return ResponseEntity.ok(boardService.boardUpdate(id,requestDto, req));
    }

    // Delete -> authorization required (Request Header)
    @DeleteMapping("/board/{id}")
    public ResponseEntity<StringResponseDto> boardDelete(@PathVariable Long id, HttpServletRequest req) {
        return ResponseEntity.ok(boardService.boardDelete(id, req));
    }
}
