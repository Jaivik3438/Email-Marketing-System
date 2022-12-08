package com.ems.response_generator;

import org.springframework.http.HttpStatus;

public interface IResponseGeneratorFactory {
    ResponseGenerator createResponseGenerator(HttpStatus status, Object data);
}
