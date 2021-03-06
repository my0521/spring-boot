package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.entity.User;

@RestController
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// 无参数
	@GetMapping("/")
	public String index() {
		return "this is index.";
	}

	// 通过@PathVariable获取参数
	// http://localhost:8080/hello/name
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	public String hello(@PathVariable String name) {
		return "hello " + name;
	}

	// http://localhost:8080/serviceTime?local=haha
	@RequestMapping("/serviceTime")
	public String time(@RequestParam(value = "local", required = true) String local) {
		System.out.println("local:" + local);
		return "2018-8-8 18:36:00";
	}

	//http://localhost:8080/register?username=haha&password=haha
	@RequestMapping("/register")
	public String register(String username, String password) {
		System.out.println(username);
		System.out.println(password);
		return username + "&" + password;
	}

	//http://localhost:8080/formatDate/2020-10-22
	@RequestMapping(value = "/formatDate/{year}-{month}-{day}", method = RequestMethod.POST)
	public String formatDate(@PathVariable("year") int year, @PathVariable("month") int month,
			@PathVariable("day") int day) {
		return year + "年" + month + "月" + day + "日";
	}

	// http://localhost:8080/addUser
	// {"name":"name123","pass":"pass456"}
	// json实体对象
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public User addAccount(@RequestBody User user) {
		System.out.print(user.getName());
		return user;
	}
}
