package com.example.ec2testapp.controller;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import com.example.ec2testapp.domain.Board;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec2testapp.service.BoardService;

@Controller
@RequestMapping("/")
public class HomeController {
    private final BoardService boardService;
    private final DataSource dataSource;
    
    public HomeController(BoardService boardService, DataSource dataSource) {
		this.boardService = boardService;
		this.dataSource = dataSource;
	}

	@GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "EC2 배포 테스트 성공!");
        model.addAttribute("currentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("serverInfo", System.getProperty("os.name") + "/ JAVA "+System.getProperty("java.version") + " " + System.getProperty("java.vendor") );
        String dbStatus = "연결실패";
        String dbURL= "알 수 없음";
        String dbName = "알 수 없음";
        try(Connection connection = dataSource.getConnection()) {
			if (connection != null &&  !connection.isClosed()) {
                dbStatus = "연결 설공 ✅";
                dbURL = connection.getMetaData().getURL();
                dbName = connection.getMetaData().getDatabaseProductName() + " " + connection.getMetaData().getDatabaseProductVersion();
				connection.setAutoCommit(false);
            }
		} catch (Exception e) {
            dbStatus = "연결 실패❌ : "+e.getMessage();
		}
        model.addAttribute("dbStatus", dbStatus);
        model.addAttribute("dbURL", dbURL);
        model.addAttribute("dbName", dbName);
        return "index";
    }
    @GetMapping("/boards")
    public ResponseEntity<List<Board>> getList(Model model) {
        Board board = new Board("테스트 게시글 " + System.currentTimeMillis(), "DB 연동 테스트용 게시글입니다");
        boardService.saveBoard(board);
        // 게시글 조회
        List<Board> boards = boardService.getAllBoards();
        return ResponseEntity.ok().body(boards);
    }
}
