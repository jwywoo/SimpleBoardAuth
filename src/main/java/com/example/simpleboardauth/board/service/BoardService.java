package com.example.simpleboardauth.board.service;

import com.example.simpleboardauth.board.dto.BoardRequestDto;
import com.example.simpleboardauth.board.dto.BoardResponseDto;
import com.example.simpleboardauth.board.entity.Board;
import com.example.simpleboardauth.board.repository.BoardRepository;
import com.example.simpleboardauth.board.dto.StringResponseDto;
import com.example.simpleboardauth.user.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;

    public BoardService(BoardRepository boardRepository, JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.boardRepository = boardRepository;
    }

    // Find all
    public List<BoardResponseDto> boardList() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    // Find one
    public BoardResponseDto boardDetail(Long id) {
        return new BoardResponseDto(findBoard(id));
    }

    // Create -> authorization required (Request Header)
    public BoardResponseDto boardCreate(BoardRequestDto requestDto, HttpServletRequest req) {
        Claims userInfo = tokenValidation(req);
        Board board = new Board(requestDto, userInfo.getSubject());
        return new BoardResponseDto(boardRepository.save(board));
    }

    // Update -> authorization required (Request Header)
    @Transactional
    public BoardResponseDto boardUpdate(Long id, BoardRequestDto requestDto,HttpServletRequest req) {
        Claims userInfo = tokenValidation(req);
        Board board = findBoard(id);
        if (userInfo.getSubject().equals(board.getUsername())) {
            board.update(requestDto, userInfo.getSubject());
            return new BoardResponseDto(board);
        } else {
            throw new IllegalArgumentException("Invalid User Credentials");
        }
    }

    // Delete -> authorization required (Request Header)
    public StringResponseDto boardDelete(Long id, HttpServletRequest req) {
        Claims userInfo = tokenValidation(req);
        Board board = findBoard(id);
        if (userInfo.getSubject().equals(board.getUsername())) {
            boardRepository.delete(board);
            return new StringResponseDto("Success", "200");
        } else {
            throw new IllegalArgumentException("Invalid User Credentials");
        }
    }

    // Find board by id
    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid id"));
    }

    private Claims tokenValidation(HttpServletRequest req) {
        String givenToken = jwtUtil.getTokenFromRequest(req);
        givenToken = jwtUtil.substringToken(givenToken);
        if (!jwtUtil.validateToken(givenToken)) throw new IllegalArgumentException("Invalid User Credentials");
        return jwtUtil.getUserInfoFromToken(givenToken);
    }

}
