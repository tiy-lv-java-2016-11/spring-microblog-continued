package com.theironyard.controllers;

import com.theironyard.models.Message;
import com.theironyard.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by sparatan117 on 12/28/16.
 */
@Controller
public class MicroblogSpringController {

    @Autowired
    MessageRepository messageRepository;

    public static final String SESSION_USER_NAME = "username";

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String username = (String) session.getAttribute(SESSION_USER_NAME);
        model.addAttribute("name", username);
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username) {
        session.setAttribute(SESSION_USER_NAME, username);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(String message) {
        Message message1 = new Message(message);
        messageRepository.save(message1);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage(int id) {
        messageRepository.delete(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/update-message", method = RequestMethod.POST)
    public String updateMessage(int id, String message){
        Message message1 = messageRepository.findOne(id);
        message1.setMessage(message);
        messageRepository.save(message1);
        return "redirect:/";
    }
}
