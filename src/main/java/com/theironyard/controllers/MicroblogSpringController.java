package com.theironyard.controllers;

import com.theironyard.models.Message;
import com.theironyard.models.User;
import com.theironyard.repositories.MessageRepository;
import com.theironyard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MicroblogSpringController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    public static final String SESSION_USERNAME = "username";

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        String currentUser = (String)session.getAttribute(SESSION_USERNAME);
        List<Message> messages = messageRepository.findAllByOrderByCreatedOnDesc();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String password) throws Exception {
        User user = userRepository.findFirstByUsername(username);
        if (user==null){
            user = new User(username, password);
            userRepository.save(user);
        }
        else if (!password.equals(user.getPassword())){
            throw new Exception("Username or Password is incorrect");
        }
        session.setAttribute(SESSION_USERNAME, username);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(HttpSession session, String content){
        String currentUser = (String)session.getAttribute(SESSION_USERNAME);
        User user = userRepository.findFirstByUsername(currentUser);
        Message m = new Message(content, user);
        messageRepository.save(m);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage(int id){
        messageRepository.delete(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/message", method = RequestMethod.GET)
    public String messageDetail(Model model, HttpSession session, int id){
        boolean access = false;
        String currentUser = (String)session.getAttribute(SESSION_USERNAME);
        Message message = messageRepository.findOne(id);
        if (currentUser.equals(message.getUser().getUsername())){
            access = true;
        }
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("message", message);
        model.addAttribute("access", access);
        return "message";
    }

    @RequestMapping(path = "/update-message", method = RequestMethod.POST)
    public String updateMessage(int id, String content){
        Message message = messageRepository.findOne(id);
        message.setContent(content);
        messageRepository.save(message);
        return "redirect:/message?id="+id;
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public String userMessages(Model model, HttpSession session, int id){
        boolean access = false;
        String currentUser = (String)session.getAttribute(SESSION_USERNAME);
        User user = userRepository.findOne(id);
        List<Message> messages = messageRepository.findByUser(user);
        if (currentUser.equals(user.getUsername())){
            access = true;
        }
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("user", user);
        model.addAttribute("messages", messages);
        model.addAttribute("access", access);
        return "user";
    }
}
