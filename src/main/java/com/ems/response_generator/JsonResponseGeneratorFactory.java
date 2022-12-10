package com.ems.response_generator;

import org.springframework.http.HttpStatus;

public class JsonResponseGeneratorFactory implements IResponseGeneratorFactory {
    @Override
    public ResponseGenerator createResponseGenerator(HttpStatus status, Object data) {
        JsonResponseGenerator jsonResponseGenerator = new JsonResponseGenerator(status, data);
        return jsonResponseGenerator;
    }
}
