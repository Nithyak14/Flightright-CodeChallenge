package de.flightright.visitorcount.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.eclipse.jdt.internal.compiler.batch.FileSystem.Classpath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import de.flightright.visitorcount.service.CountService;

@Controller
public class VisitorsController {

	@Autowired
	private Environment env;
	@Autowired
	CountService countService;

	@GetMapping("/view") // If we want to see output by browsing the CSV file , then use /view.
	public String view(Model model) {
		return "visitorcount";
	}

	@GetMapping("/viewVisitorCount") // Alternatively,if we want to see output by fetching the CSV inside application, then use /viewVisitorCount.
	public ModelAndView viewVisitorCount(Model model) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File filepath = new File(
					classLoader.getResource(env.getProperty("de.flightright.visitorcount.filename")).getFile());
			File files = new File(filepath.getAbsolutePath());
			countService.readCount(files);
			model.addAttribute("viewcount", countService.readCount(files));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("view_visitorcount");
	}

	@PostMapping("/viewcount")
	public ModelAndView submit(@RequestParam("visitorfile") MultipartFile visitorfile,
			RedirectAttributes redirectAttributes, Model model) {
		try {
			countService.readCount(visitorfile);
			model.addAttribute("viewcount", countService.readCount(visitorfile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("view_visitorcount");
	}

}
