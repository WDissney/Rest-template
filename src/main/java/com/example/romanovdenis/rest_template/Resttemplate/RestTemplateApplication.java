package com.example.romanovdenis.rest_template.Resttemplate;

import model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class RestTemplateApplication {

	public static void main(String[] args) {
//		SpringApplication.run(RestTemplateApplication.class, args);
		//1
		String url = "http://94.198.50.185:7081/api/users/";
		RestTemplate template = new RestTemplate();
		User user1 = new User();
		User user2 = new User();
		HttpHeaders headers1 = new HttpHeaders();
		HttpEntity <String> entity = new HttpEntity<String>(headers1);
		ResponseEntity<String> responseEntity = template.exchange(url, HttpMethod.GET, entity, String.class);
		HttpHeaders headers11 = responseEntity.getHeaders();
		String set_cookie = headers11.getFirst(HttpHeaders.SET_COOKIE);
		System.out.println(set_cookie);
		//2
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Cookie", set_cookie);
		User user3 = new User();
		user3.setId(3L);
		user3.setName("James");
		user3.setLastName("Brown");
		user3.setAge((byte)23);
		HttpEntity<User> httpEntity = new HttpEntity<>(user3, headers2);
		ResponseEntity<String> response = template
				.exchange(url, HttpMethod.POST, httpEntity, String.class);
		String two = response.getBody();
		System.out.println(two);
		//3
		user3.setName("Thomas");
		user3.setLastName("Shelby");
		HttpHeaders headers3 = new HttpHeaders();
		headers3.add("Cookie", set_cookie);
		HttpEntity<User> httpEntity3 = new HttpEntity<>(user3, headers3);
		ResponseEntity<String> response3 = template.exchange(url, HttpMethod.PUT, httpEntity3, String.class);
		String tree = response3.getBody();
		System.out.println(tree);

		//4
		HttpEntity<User> entity4 = new HttpEntity<>(headers3);
		ResponseEntity<String> response4 = template.exchange(url+user3.getId(), HttpMethod.DELETE, entity4, String.class);
		String four = response4.getBody();
		String all = two+tree+four;
		System.out.println(all);
 	}

}
