package com.crunchdroid.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crunchdroid.model.Customer;

@Controller
public class SimpleWebController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
    
	//------------------------------------------------
	
	//SOLO PERMITE ENVIAR ESOS 2 ATRIBUTOS DEL FORMULARIO, LA IDEA ES QUE SOLO ESTE LIMITADO
	//A LOS CAMPOS DE LOS FORMULARIOS CORRESPONDIENTES
	private static final String[] ALLOWED_FIELDS = {"id", "firstname"};//, "firstname", "lastname"};
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
	    dataBinder.setAllowedFields(ALLOWED_FIELDS);
	}
	
	//------------------------------------------------
	
	
	//GET - MOSTRAR
    @RequestMapping(value="/form", method=RequestMethod.GET)
    public String customerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "form";
    }
    
    //POST - ENVIAR
    @RequestMapping(value="/form", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute Customer customer, Model model) {
         
        model.addAttribute("customer", customer);
        String info = String.format("Customer Submission: id = %d, firstname = %s, lastname = %s", 
                                        customer.getId(), customer.getFirstname(), customer.getLastname());
        log.info(info);
         
        return "result";
    }

}
