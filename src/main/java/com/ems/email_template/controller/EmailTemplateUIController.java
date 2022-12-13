package com.ems.email_template.controller;

import com.ems.email_template.model.EmailTemplate;
import com.ems.email_template.model.SimpleEmailTemplate;
import com.ems.email_template.model.Template;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping(value = "/")
public class EmailTemplateUIController {

    @GetMapping("/email-template")
    public String emailTemplateList(Model model, HttpSession session) {
        System.out.println(session.getAttribute("user").toString());
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
            String id = template.get("templateId").asText();
            String name = template.get("templateName").asText();
            String subject = template.get("templateSubject").asText();
            String description = template.get("templateDescription").asText();
            String landingPageLink = template.get("landingPageLink").asText();
            templatesList.add(new SimpleEmailTemplate(id, name, subject, description, landingPageLink));
        }

        model.addAttribute("something", "dynamic value");
        model.addAttribute("emailTemplates", templatesList);
        return "emailTemplate";
    }

    @GetMapping("/create-email-template")
    public String emailTemplateForm(Model model) {
        return "emailTemplateForm";
    }

    @GetMapping("/create-email-template-error")
    public String createEmailTemplateError() {
        return "emailTemplateFormError";
    }

    @GetMapping("/update-email-template/{id}")
    public String updateEmailTemplate(Model model, @PathVariable String id) throws JsonProcessingException {
        EmailTemplateController controller = new EmailTemplateController();
        String jsonString = controller.getTemplate(id).toString();

        JsonNode node = new ObjectMapper().readTree(jsonString).get("data");
        String templateId = node.get("templateId").asText();
        String name = node.get("templateName").asText();
        String subject = node.get("templateSubject").asText();
        String description = node.get("templateDescription").asText();
        String landingPageLink = node.get("landingPageLink").asText();
        EmailTemplate template = new SimpleEmailTemplate(templateId, name, subject, description, landingPageLink);

        List<EmailTemplate> list = new ArrayList<>();
        list.add(template);

        model.addAttribute("templateList", list);
        return "emailTemplateUpdateForm";
    }
}
