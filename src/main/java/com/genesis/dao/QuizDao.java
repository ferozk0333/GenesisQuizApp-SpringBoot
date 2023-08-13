package com.genesis.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genesis.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
