package com.nidhish.surveyapi.surveyapi;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class SurveyResources {
	
	private SurveyService surveyService;
	
	public SurveyResources(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}

	// /surveys
	@RequestMapping("/surveys")
	public List<Survey> retrieveAllSurveys(){
		System.out.println(surveyService.retrieveAllSurveys());
		return surveyService.retrieveAllSurveys();
	}
	@RequestMapping("/surveys/{surveyId}")
	public Survey retrieveSurveyById(@PathVariable String surveyId){
		Survey survey = surveyService.retrieveSurveyById(surveyId);
		
		if(survey==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return survey;
	}
	@RequestMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveAllSurveyQuestions(@PathVariable String surveyId){
		List<Question> questions = surveyService.retrieveAllSurveyQuestions(surveyId);
		
		if(questions==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return questions;
	}
	@RequestMapping(value="/surveys/{surveyId}/questions",method = RequestMethod.POST)
	public ResponseEntity<Object> addNewSurveyQuestion(@PathVariable String surveyId,
			@RequestBody Question question){
		String questionId =  surveyService.addNewSurveyQuestion(surveyId,question);
		//  /surveys/{surveyId}/questions/{questionId}
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{questionId}")
						.buildAndExpand(questionId)
						.toUri();
		return ResponseEntity.created(location ).build();
	}
	
	@RequestMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveSurveyQuestionById(@PathVariable String surveyId,
			@PathVariable String questionId){
		Question question = surveyService.retrieveSurveyQuestionById(surveyId,questionId);
		
		if(question==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return question;
	}
	@RequestMapping(value="/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteSurveyQuestionById(@PathVariable String surveyId,
			@PathVariable String questionId){
		surveyService.deleteSurveyQuestionById(surveyId,questionId);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateSurveyQuestionById(
			@PathVariable String surveyId,
			@PathVariable String questionId,
			@RequestBody Question question){
		surveyService.updateSurveyQuestionById(surveyId,questionId,question);
		return ResponseEntity.noContent().build();
	}
}
