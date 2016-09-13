package org.springframework.samples.mvc.simple;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

	@RequestMapping("/simple")
	public @ResponseBody String simple(HttpServletRequest request) {
		Map<String, String> ps = getHeadersInfo(request);
		for(String parameter : ps.keySet()) {
			System.out.println("header : "+parameter+", value : "+ps.get(parameter));
		}

		return "Hello world!";
	}
	//get request headers
		private Map<String, String> getHeadersInfo(HttpServletRequest request) {

			Map<String, String> map = new HashMap<String, String>();

			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = request.getHeader(key);
				map.put(key, value);
			}

			return map;
		}

}
