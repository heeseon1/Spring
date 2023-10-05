package com.spring.board.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.board.entity.Board;
import com.spring.board.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	   private BoardRepository boardRepository;
	   
	   public void write(Board board, MultipartFile file) throws Exception {
	      
	      if (file.isEmpty()) {
	         board.setFilename(board.getFilename());
	         board.setFilepath(board.getFilepath());
	      }else {
	         
	         String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
	         
	         UUID uuid = UUID.randomUUID();
	         
	         String fileName = uuid + "_" + file.getOriginalFilename();
	         
	         File saveFile = new File(projectPath, fileName);
	         
	         file.transferTo(saveFile);
	         
	         board.setFilename(fileName);
	         board.setFilepath("/files/" + fileName);
	      }
	      
	      boardRepository.save(board);
	      
	   }

	
	public List<Board> boardList(){
		return boardRepository.findAll();
	}
	
	public Board boardView(Integer id) {
		return boardRepository.findById(id).get();
	}
	
	public void boardDelete(Integer id) {
		boardRepository.deleteById(id);
	}

}