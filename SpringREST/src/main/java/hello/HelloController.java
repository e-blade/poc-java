package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/olaMundoSpring")
	public String execute() {
		System.out.println("Executando a l�gica com Spring MVC");
		return "ok";
	}
	
}
