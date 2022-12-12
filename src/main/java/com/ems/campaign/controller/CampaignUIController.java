package com.ems.campaign.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.authentication.model.User;
import com.ems.campaign.model.Campaign;
import com.ems.campaign.persistent.CampaignDb;
import com.ems.campaign.persistent.ICampaignPersistent;
import com.ems.email_template.model.Template;
import com.ems.email_template.model.template_fetcher.EmailTemplateFetcher;
import com.ems.email_template.model.template_fetcher.ITemplateFetcher;
import com.ems.email_template.persistent.EmailTemplateDb;
import com.ems.email_template.persistent.ITemplatePersistent;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component()
@RequestMapping(value = "/")
public class CampaignUIController {

    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @GetMapping("/campaigns")
    public String showCampaignByUserId(Model model, HttpSession session) {
        Connection connection = null;
        try {
            connection = MySqlPersistenceConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = getLoggedInUser(session);
        String userId = user.userId;
        ICampaignPersistent campaignPersistent = new CampaignDb(connection);

        List<Campaign> campaigns = campaignPersistent.loadCampaignByUserId(userId);
        for (Campaign campaign : campaigns) {
            System.out.println(campaign.getCampaignName());
        }
        model.addAttribute("campaigns", campaigns);
        return "campaignList";
    }

    @GetMapping("/create-campaign")
    public String createCampaignForm(Model model, HttpSession session) {
        Connection connection = null;
        try {
            connection = MySqlPersistenceConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ITemplatePersistent templatePersistent = new EmailTemplateDb(connection);
        ITemplateFetcher templateFetcher = new EmailTemplateFetcher(templatePersistent);

        User user = getLoggedInUser(session);
        List<Template> templates = (List<Template>) templateFetcher.fetchAllTemplateByUserId(user).getData();

        for (Template t: templates) {
            System.out.println(t.getTemplateName());
        }

        model.addAttribute("templates", templates);
        return "campaignForm";
    }
}
