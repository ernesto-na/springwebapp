package com.novellius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.novellius.pojo.Admin;
import com.novellius.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
	@RequestMapping("/admin")
	public String showAdmin(Model model,
			@ModelAttribute("resultado")String resultado) {
		
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		model.addAttribute("resultado",resultado);
		return "admin";
	}

	@RequestMapping(value = "/admin/save", method = RequestMethod.POST)
	public String handleAdmin(@ModelAttribute("admin") Admin adminForm, Model model, RedirectAttributes ra) {

		if (adminService.save(adminForm)) {
			ra.addFlashAttribute("resultado", "se guarda correctamente");
		}else {
			ra.addFlashAttribute("resultado", "error al guardar");
		}
		return "redirect:/admin";
	}

}
