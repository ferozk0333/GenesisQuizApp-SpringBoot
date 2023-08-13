package com.genesis.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.genesis.dao.QuestionDao;
import com.genesis.dao.QuizDao;
import com.genesis.model.QuestionWrapper;
import com.genesis.model.Questions;
import com.genesis.model.Quiz;
import com.genesis.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	@Autowired                 //Took me 1 hour to find out this missing annotation to debug:(
	QuestionDao questionDao;  //Getting questions

	//It is QuizService's responsibility to create a quiz
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Questions> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		
		java.util.Optional<Quiz> quiz = quizDao.findById(id);  //null values -> Optional<>
		List<Questions> questionsfromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsforUser = new ArrayList<>();
		
		
		for(Questions q : questionsfromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getQid(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
				questionsforUser.add(qw);
		
		
		}
		return new ResponseEntity<>(questionsforUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get(); //or use optional
		List<Questions> questions = quiz.getQuestions();
		int right = 0;
		int i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i)))
				right++;
			
		}
		
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	
	
}
