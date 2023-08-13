package com.genesis.controller;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.model.Questions;
import com.genesis.service.QuestionService;

@RestController
@RequestMapping(path = "/questions")
public class QuestionController{
	@Autowired
	QuestionService questionService;   //No need to create object by 'new QuestionService(). @Autowired
	
	
	
	@GetMapping(path = "/allQuestions")    // localhost:8080/questions/allQuestions
	public ResponseEntity<List<Questions>> getAllQuestions() {
		return questionService.getAllQuestions(); //return object of the service layer.
	}
	
	@GetMapping(path = "/category/{category}")
	public ResponseEntity<List<Questions>> findByCategory(@PathVariable("category") String category){
		return questionService.findByCategory(category);
	}
	
	@PostMapping(path = "/addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Questions question) { //Receive JSON from Client and add to DB
		return questionService.addQuestion(question);
	}
	
	@DeleteMapping(path = "/deleteQuestion/{qid}")
	public String deleteQuestion(Questions question) {
		return questionService.deleteQuestion(question.getQid());
	}
	
	@PutMapping(path = "/updateQuestion")
	public String updateQuestion(@RequestBody Questions question) {
		return questionService.updateQuestion(question);
	}
	}
