package com.ems.subscriberList.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.authentication.buisness.HttpSessionManager;
import com.ems.authentication.buisness.ISessionManager;
import com.ems.authentication.model.User;
import com.ems.subscriberList.model.SimpleSubscriberList;
import com.ems.subscriberList.model.Subscriber;
import com.ems.subscriberList.persistence.SubscriberDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SubscriberListUIController {

    @GetMapping("/add-subscriber")
    public String addSubscriberList(){
        return "subscriberForm";
    }
    @GetMapping("/subscriber-list")
    public ModelAndView subscriberDetails(HttpServletRequest request, Model model){
        ModelAndView mv = new ModelAndView("subscriberList");
        SimpleSubscriberList subscriberList = new SimpleSubscriberList();
        List<Subscriber> subscribers= new ArrayList<>();
        ISessionManager sessionManager= new HttpSessionManager();
        User user= (User)sessionManager.getUserFromSession(request.getSession());
        try {
            subscribers = subscriberList.getSubscriberByUserID(user.userId,new SubscriberDB(MySqlPersistenceConnection.getInstance().getConnection()));
            mv.addObject("subscribers",subscribers);
        } catch (Exception e) {
            e.printStackTrace();

            return mv;
        }
        return mv;
    }
}