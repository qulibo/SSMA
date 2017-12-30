package cn.et.food.controller;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/test")
	public String test(OutputStream os) throws IOException {
		os.write("<font color=red>is my pages</font>".getBytes());
		return null;
	}
}
