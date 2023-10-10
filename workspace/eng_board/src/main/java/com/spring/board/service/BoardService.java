package com.spring.board.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	
	public Page<Board> boardList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	public Board boardView(Integer id) {
		return boardRepository.findById(id).get();
	}
	
	public void boardDelete(Integer id) {
		boardRepository.deleteById(id);
	}
	
	
    public void boardViewCount(Integer id) {
		
        Board board = boardRepository.findById(id).orElse(null);
        
        if (board != null) {
            board.setViewCount(board.getViewCount() + 1);
            boardRepository.save(board);
        }
    }
    
    
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
    	return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

}