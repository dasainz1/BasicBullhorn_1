package com.sainz3.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@Controller
public class HomeController {

    @Autowired
    MessagesRepository messagesRepository;

    @RequestMapping("/")
    public String listMessages(Model model) {
        model.addAttribute("Messages", messagesRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String Messagesform(Model model) {
        model.addAttribute("Messages", new Messages());
        return "Messagesform";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("Messages") Messages messages, BindingResult result) {
        if (result.hasErrors()) {
            return "Messagesform";
        }
        messagesRepository.save(messages);
        return "list";
    }

    @RequestMapping("/detail/{id}")
    public String showMessages(@PathVariable("id") long id, Model model) {
        model.addAttribute("Messages", messagesRepository.findOne(id));
        return "show";


    }
}
