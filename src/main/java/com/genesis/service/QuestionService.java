package com.genesis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.genesis.dao.QuestionDao;
import com.genesis.model.Questions;



@Service                        				//Can also use @Component
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Questions>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);  //returns a list of all questions
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST) ;
			
	}

	public ResponseEntity<List<Questions>> findByCategory(String category) {
		try {
		return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Questions question) {
		questionDao.save(question);
		try{
			return new ResponseEntity<>("Success",HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new String(),HttpStatus.BAD_GATEWAY);
		
	}

	public String deleteQuestion(int qid) {
		questionDao.deleteById(qid);
		return "Delete Success";
	}

	public String updateQuestion(Questions question) {
		questionDao.save(question);
		return "Update Success";
	}

	 
}
