package com.example.demo.controllers.daret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.daret.daret;
import com.example.demo.services.daret.daretService;

@Controller
public class daretController {
	
	@Autowired
	private daretService daretService;
	/*
	 * // show list des daret
	 * 
	 * @GetMapping("/daret") public String viewHomePage(Model model) {
	 * model.addAttribute("listdarets", daretService.getAlldaret()); return
	 * "daret/index"; }
	 */
	
	/*
	 * @GetMapping("/daret/daretajoutform") public String daretajoutform(Model
	 * model) { daret daret = new daret(); model.addAttribute("daret", daret);
	 * return "daret/ajoutdaret"; }
	 */

	@PostMapping("/daret/savedaret")
	public String savedaret(@ModelAttribute("daret") daret daret) {
		daretService.savedaret(daret);
		return "redirect:/redirectByRole";
	}
	
	@GetMapping("/daret/daretmodifform/{id}")
	public String daretmodifform(@PathVariable (value = "id") Long id,Model model) {
		daret daret = daretService.getDaretById(id);
		
		model.addAttribute("daret", daret);
		return "Rdaret/modifdaret";
	}
	
	@GetMapping("/daret/deletedaret/{id}")
	public String deletedaret(@PathVariable (value = "id") Long id) {
		this.daretService.deletedaretparid(id);
		return "redirect:/daret/";
	}
	
}
