package com.ems.email_template.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.email_template.model.EmailTemplate;
import com.ems.email_template.model.EmailTemplateFactory;
import com.ems.email_template.model.template_fetcher.EmailTemplateFetcherFactory;
import com.ems.email_template.model.template_fetcher.ITemplateFetcher;
import com.ems.email_template.model.template_fetcher.ITemplateFetcherFactory;
import com.ems.email_template.model.template_generator.EmailTemplateGeneratorFactory;
import com.ems.email_template.model.template_generator.ITemplateGeneratorFactory;
import com.ems.email_template.model.template_generator.TemplateGenerator;
import com.ems.email_template.model.template_manipulator.EmailTemplateManipulatorFactory;
import com.ems.email_template.model.template_manipulator.ITemplateManipulator;
import com.ems.email_template.model.template_manipulator.ITemplateManipulatorFactory;
import com.ems.email_template.model.template_state.TemplateState;
import com.ems.email_template.persistent.EmailTemplateDb;
import com.ems.email_template.persistent.ITemplatePersistent;
import com.ems.response_generator.ResponseGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class EmailTemplateController {

    private EmailTemplateFactory emailTemplateFactory;
    private ITemplatePersistent emailPersistent;

    public EmailTemplateController() {
        emailTemplateFactory = new EmailTemplateFactory();
        emailPersistent = new EmailTemplateDb(getConnectionObject());
    }

    private Connection getConnectionObject() {
        Connection connection = null;
        try {
            connection = MySqlPersistenceConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @PostMapping("/email-template")
    public JsonNode createEmailTemplate(@RequestBody JsonNode body) {
        ITemplateGeneratorFactory generatorFactory = new EmailTemplateGeneratorFactory();

        List<String> fieldNamesList = new ArrayList<>(Arrays.asList("name", "subject", "description", "landingPageLink"));
        Map<String, JsonNode> usersDataMap = extractDataFromJson(fieldNamesList, body);


        EmailTemplate emailTemplate = emailTemplateFactory.createSimpleEmailTemplate(usersDataMap.get("name").asText());
        emailTemplate.setTemplateSubject(usersDataMap.get("subject").asText());
        emailTemplate.setTemplateDescription(usersDataMap.get("description").asText());
        emailTemplate.setLandingPageLink(usersDataMap.get("landingPageLink").asText());

        TemplateGenerator generator = generatorFactory.createTemplateGenerator(emailPersistent);
        TemplateState state = generator.createNewTemplate(emailTemplate);
        ResponseGenerator<JsonNode> response = state.getResponse();
        response.setDataLabel("rows_inserted");
        return response.sendResponse();
    }

    @GetMapping("/email-template")
    public JsonNode getAllEmailTemplate() {
        ITemplateFetcherFactory fetcherFactory = new EmailTemplateFetcherFactory();
        ITemplateFetcher templateFetcher = fetcherFactory.createTemplateFetcher(emailPersistent);

        TemplateState state = templateFetcher.fetchAllTemplates();
        ResponseGenerator<JsonNode> response = state.getResponse();
        return response.sendResponse();
    }

    @GetMapping("/email-template/{id}")
    public JsonNode getTemplate(@PathVariable String id) {
        ITemplateFetcherFactory fetcherFactory = new EmailTemplateFetcherFactory();
        ITemplateFetcher templateFetcher = fetcherFactory.createTemplateFetcher(emailPersistent);

        TemplateState state = templateFetcher.fetchTemplate(id);
        ResponseGenerator<JsonNode> response = state.getResponse();
        return response.sendResponse();
    }

    @PutMapping("/email-template/{id}")
    public JsonNode updateEmailTemplate(@PathVariable String id, @RequestBody JsonNode body) {
        ITemplateManipulatorFactory manipulatorFactory = new EmailTemplateManipulatorFactory();
        ITemplateManipulator templateManipulator = manipulatorFactory.createTemplateManipulator(emailPersistent);

        String templateName = body.get("name").asText();
        String templateSubject = body.get("subject").asText();
        String templateDescription = body.get("description").asText();
        String landingPageLink = body.get("landingPageLink").asText();

        EmailTemplate templateToUpdate = emailTemplateFactory.createSimpleEmailTemplate(templateName);
        templateToUpdate.setTemplateSubject(templateSubject);
        templateToUpdate.setTemplateDescription(templateDescription);
        templateToUpdate.setLandingPageLink(landingPageLink);

        TemplateState state = templateManipulator.updateTemplate(id, templateToUpdate);
        ResponseGenerator<JsonNode> responseGenerator = state.getResponse();
        responseGenerator.setDataLabel("rows_updated");
        return responseGenerator.sendResponse();
    }

    @DeleteMapping("/email-template/{id}")
    public JsonNode deleteEmailTemplate(@PathVariable String id) {
        ITemplateManipulatorFactory manipulatorFactory = new EmailTemplateManipulatorFactory();
        ITemplateManipulator templateManipulator = manipulatorFactory.createTemplateManipulator(emailPersistent);

        TemplateState state = templateManipulator.deleteTemplate(id);
        ResponseGenerator<JsonNode> responseGenerator = state.getResponse();
        responseGenerator.setDataLabel("rows_deleted");
        return responseGenerator.sendResponse();
    }

    private Map<String, JsonNode> extractDataFromJson(List<String> fieldNames, JsonNode body) {
        Map<String, JsonNode> dataMap = new HashMap<>();
        ListIterator<String> iterator = fieldNames.listIterator();
        while (iterator.hasNext()) {
            String columnName = iterator.next();
            dataMap.put(columnName, body.get(columnName));
        }
        return dataMap;
    }
}
