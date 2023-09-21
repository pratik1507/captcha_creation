package com.klu.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.CaptchaManager;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
	@Autowired
	CaptchaManager cm;
	@GetMapping("/cap/{length}")
	public String generateCaptcha(@PathVariable("length") int length) {
		String txt=cm.randomText(length);
		return Base64.getEncoder().encodeToString(cm.getCaptcha(txt));
	}
}
