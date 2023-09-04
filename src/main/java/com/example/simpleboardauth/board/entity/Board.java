package com.example.simpleboardauth.board.entity;

import com.example.simpleboardauth.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 500)
    private String title;
    @Column(name = "username", length = 500)
    private String username;
    @Column(name = "content", nullable = false, length = 500)
    private String content;

    public Board(BoardRequestDto requestDto, String subject) {
        this.title = requestDto.getTitle();
        this.username = subject;
        this.content = requestDto.getContent();
    }

    public void update(BoardRequestDto requestDto, String subject) {
        this.title = requestDto.getTitle();
        this.username = subject;
        this.content = requestDto.getContent();
    }
}
