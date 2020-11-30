package com.example.bakerytwo.web;

import com.example.bakerytwo.domain.Bread;
import com.example.bakerytwo.domain.BreadRepository;
import com.example.bakerytwo.domain.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BakeryController {

    @Autowired
    private BreadRepository breadRepository;

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping(value = "/breadlist")
    public String breadList(Model modle){
        modle.addAttribute("breads", breadRepository.findAll());
        return "breadlist";
    }

    @GetMapping(value = "/add")
    public String addBread(Model model){
        model.addAttribute("bread", new Bread());
        model.addAttribute("types", typeRepository.findAll());
        return "addbread";
    }

    @PostMapping(value = "/save")
    public String save(Bread bread){
        breadRepository.save(bread);
        return "redirect:breadlist";
    }

    @GetMapping(value = "/edit/{id}")
    public String editBread(@PathVariable("id") Long id, Model model){
        model.addAttribute("bread", breadRepository.findAllById(id));
        model.addAttribute("types", typeRepository.findAll());
        return "editbread";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBread(@PathVariable("id") Long id){
        breadRepository.deleteById(id);
        return "redirect:../breadlist";
    }

    //RESTful service to get all breads
    @RequestMapping(value = "/breads")
    public @ResponseBody Iterable<Bread> getBreads(){
        return breadRepository.findAll();
    }

    //Restful service to find bread by id using path variable
    @GetMapping(value = "/bread/{id}")
    public @ResponseBody Bread findByIdRest(@PathVariable("id") Long breadId){
        return breadRepository.findAllById(breadId);
    }

    //Security-login
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    //home page
    @GetMapping("/")
    public String home(){
        return "breadlist";
    }
}
