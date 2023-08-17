package com.cpan252.tekkenreborn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/about")
public class TekkenrebornApplication {

	@GetMapping
	public ModelAndView aboutPage() {
		ModelAndView modelAndView = new ModelAndView("about");
		modelAndView.addObject("Tekken Reborn");
		modelAndView.addObject("<p>Welcome to Tekken Reborn! That game is a revolutionary fighting game, you can have a amazing experience of fightining with many different person.</p>");
		return modelAndView;
	}
}

