package com.example.ec2testapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;
    private String author;
    
    
    public Board() {
	}

	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}
    
	public Long getId() {
        return id;
    }

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getAuthor() {
		return author;
	}
	
}
