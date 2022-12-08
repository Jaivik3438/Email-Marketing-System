package com.ems.email_template.model.template_state;

import com.ems.response_generator.IResponseGeneratorFactory;
import com.ems.response_generator.JsonResponseGeneratorFactory;
import com.ems.response_generator.ResponseGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;

public class TemplateNotFoundState extends TemplateState {
    public TemplateNotFoundState(Object data) {
        super(data);
        this.status = HttpStatus.NOT_FOUND;
    }

    public TemplateNotFoundState(HttpStatus status, Object data) {
        super(status, data);
    }

    @Override
    public ResponseGenerator<JsonNode> getResponse() {
        IResponseGeneratorFactory generatorFactory = new JsonResponseGeneratorFactory();
        ResponseGenerator responseGenerator = generatorFactory.createResponseGenerator(status, data);
        responseGenerator.setDataLabel("message");
        return responseGenerator;
    }
}
