package com.ems.email_template.controller;

import com.ems.email_template.model.SimpleEmailTemplate;
import com.ems.email_template.model.Template;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping(value = "/")
public class EmailTemplateUIController {

    @GetMapping("/email-template")
    public String emailTemplateList(Model model) {
        EmailTemplateController controller = new EmailTemplateController();
        String jsonString = controller.getAllEmailTemplate().toString();
        ArrayNode templates;
        List<Template> templatesList = new ArrayList<>();
        try {
            templates = (ArrayNode) new ObjectMapper().readTree(jsonString).get("data");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        for (JsonNode template: templates) {
            String name = template.get("templateName").asText();
            String subject = template.get("templateSubject").asText();
            String description = template.get("templateDescription").asText();
            String landingPageLink = template.get("landingPageLink").asText();
            templatesList.add(new SimpleEmailTemplate(name, subject, description, landingPageLink));
        }

        model.addAttribute("something", "dynamic value");
        model.addAttribute("emailTemplates", templatesList);
        return "emailtemplate";
    }

    @GetMapping("/create-email-template")
    public String emailTemplateForm(Model model) {
        return "createEmailTemplate";
    }

    @GetMapping("/create-email-template-error")
    public String createEmailTemplateError() {
        return "createEmailTemplateError";
    }
}
