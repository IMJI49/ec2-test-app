package com.example.ec2testapp.repository;

import com.example.ec2testapp.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
