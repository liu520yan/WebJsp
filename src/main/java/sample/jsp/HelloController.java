package sample.jsp;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.jsp.utils.MyGlobalMessage;

@Controller
public class HelloController {

	@RequestMapping(method = RequestMethod.GET, value = "/hello1", name = "hello1")
	public ModelAndView hello(final RedirectAttributes redirectAttributes, Model model) {
		MyGlobalMessage.addFlashMessage("accErrorMsgs","reorder.msg");
		return new ModelAndView("redirect:/hello2");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello3", name = "hello3")
	public ModelAndView hello3(final RedirectAttributes redirectAttributes, Model model) {
		MyGlobalMessage.addConfMessage("reorder.msg");
		return new ModelAndView();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello2", name = "hello2")
	public ModelAndView hello() {
		return new ModelAndView();
	}

}
