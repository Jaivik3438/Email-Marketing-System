package com.ems.email_template.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.email_template.model.EmailTemplate;
import com.ems.email_template.model.EmailTemplateFactory;
import com.ems.email_template.model.SimpleEmailTemplate;
import com.ems.email_template.model.Template;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @RequestMapping(value = "/email-template", method = RequestMethod.POST)
    public void createEmailTemplate(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        ITemplateGeneratorFactory generatorFactory = new EmailTemplateGeneratorFactory();
        ModelAndView mv = new ModelAndView();

        List<String> fieldNamesList = new ArrayList<>(Arrays.asList("name", "subject", "description", "landingPageLink"));
        Map<String, String> usersDataMap = extractDataFromJson(fieldNamesList, request);

        EmailTemplate emailTemplate = emailTemplateFactory.createSimpleEmailTemplate(usersDataMap.get("name"));
        emailTemplate.setTemplateSubject(usersDataMap.get("subject"));
        emailTemplate.setTemplateDescription(usersDataMap.get("description"));
        emailTemplate.setLandingPageLink(usersDataMap.get("landingPageLink"));

        TemplateGenerator generator = generatorFactory.createTemplateGenerator(emailPersistent);
        TemplateState state = generator.createNewTemplate(emailTemplate);
        ResponseGenerator<JsonNode> response = state.getResponse();
        response.setDataLabel("rows_inserted");

        if ((Integer) response.getData() > 0) {
            httpServletResponse.sendRedirect("/email-template");
        } else {
            httpServletResponse.sendRedirect("/create-email-template-error");
        }
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
    public JsonNode updateEmailTemplate(@PathVariable String id, @RequestBody JsonNode body, HttpServletRequest request) {
        ITemplateManipulatorFactory manipulatorFactory = new EmailTemplateManipulatorFactory();
        ITemplateManipulator templateManipulator = manipulatorFactory.createTemplateManipulator(emailPersistent);

        String templateName = request.getParameter("name");
        String templateSubject = request.getParameter("subject");
        String templateDescription = request.getParameter("description");
        String landingPageLink = request.getParameter("landingPageLink");

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

    private Map<String, String> extractDataFromJson(List<String> fieldNames, HttpServletRequest body) {
        Map<String, String> dataMap = new HashMap<>();
        ListIterator<String> iterator = fieldNames.listIterator();
        while (iterator.hasNext()) {
            String columnName = iterator.next();
            dataMap.put(columnName, body.getParameter(columnName));
        }
        return dataMap;
    }
}
