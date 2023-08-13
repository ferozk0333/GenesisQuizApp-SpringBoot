package com.genesis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.genesis.model.Questions;


public interface QuestionDao extends JpaRepository<Questions,Integer> { //Class name that maps to table, type of primary key

	
	List<Questions> findByCategory(String category);

	//If you need complex customisations -> Need to use HQL or JPQL
	//Method name should be intuitive - findBy...
	
	
	@Query(value ="SELECT * FROM QUESTIONS Q where q.category =:category order by random() limit :numQ", nativeQuery = true)
	List<Questions> findRandomQuestionsByCategory(String category, int numQ);  
	
	
	
	

	

}
