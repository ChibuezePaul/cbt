package com.lonbridge.sams.cbt.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends RuntimeException{
	
	public QuestionNotFoundException () {
		super ( "Question Not Found" );
	}
}
