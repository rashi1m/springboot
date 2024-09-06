package com.smart.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.smart.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ForgotController {

	@Autowired
	private EmailService emailService;
	Random random = new Random(1000);
	//email id from open handler
	@RequestMapping("/forgot")
	public String openEmailForm() {
		
		return "forgot_email_form";
	}
	
	
	@PostMapping("/send-otp")
	public String sendOTP(@RequestParam("email") String email,HttpSession session) {
		
		System.out.println("EMAIL" +email);
		
		//generating otp of 4 digit
		
		int otp= random.nextInt(9999);
		System.out.println("OTP" +otp);
		
		
		// write code for send otp to mail
		
		String subject="OTP from SCM";
		String message="OTP = "+otp+"";
		String to=email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		if(flag) {
		
			
			return "verify_otp";
		}else {
			
	    session.setAttribute("message", "Check your email id...");
	    
		return "forgot_email_form";
		}
	}
	
}
