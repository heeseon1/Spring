package com.spring.board.entity;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Data

public class Board {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		private String title;
		private String content;
		private String filename;
		private String filepath;
		private Integer viewcount;
		private Boolean heart;
		
		@CreationTimestamp
			@Column(name = "create_date", nullable = false)
			private LocalDateTime createDate;
		
			@UpdateTimestamp
			@Column(name = "modify_date", nullable = false)
			private LocalDateTime modifyDate;
		
}
