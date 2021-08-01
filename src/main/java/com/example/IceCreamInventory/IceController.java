package com.example.IceCreamInventory;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.IceCreamInventory.User;
import com.example.IceCreamInventory.SecurityService;
import com.example.IceCreamInventory.UserService;
import com.example.IceCreamInventory.UserValidator;


@Controller
public class IceController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	   public String index() 
        {
	      return "index";
	   }
	@PostMapping("/first")
	public String ice(User user)
	{
		System.out.println(user.getUsername());
		System.out.println(user.getEmail());
		return "ice";
	}
	@PostMapping("/first")
	public String rest(User user)
	{
		System.out.println(user.getUsername());
		System.out.println(user.getEmail());
		return "rest";
	}


	 @Autowired
	    private UserService userService;

	    @Autowired
	    private SecurityService securityService;

	    @Autowired
	    private UserValidator userValidator;

	    @GetMapping(value = "/registration")
	    public String registration(Model model) {
	        model.addAttribute("userForm", new User());
	        model.addAttribute("waktu", LocalDateTime.now());

	        return "registration";
	    }

	    @PostMapping(value = "/registration")
	    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
	        userValidator.validate(userForm, bindingResult);

	        if (bindingResult.hasErrors()) {
	            return "registration";
	        }

	        userService.save(userForm);

	        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

	        return "login";
	    }

	    @GetMapping(value = "/login")
	    public String login(Model model, String error, String logout) {
	        if (error != null)
	            model.addAttribute("error", "Your username and password is invalid.");

	        if (logout != null)
	            model.addAttribute("message", "You have been logged out successfully.");

	        return "login";
	    }


	    @GetMapping(value = {"/", "/login"})
	    public String welcome(Model model) {
	        return "first";
	    }
	}

