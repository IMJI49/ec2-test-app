package com.example.ec2testapp.service;

import org.springframework.stereotype.Service;

import com.example.ec2testapp.domain.Board;
import com.example.ec2testapp.repository.BoardRepository;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    // 게시글 저장
    public Board saveBoard(Board board){
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
}
