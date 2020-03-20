package com.salesianostriana.dam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.dto.CreateAdminDto;
import com.salesianostriana.dam.model.Admin;
import com.salesianostriana.dam.service.AdminService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody CreateAdminDto createAdminDto) {
		
		Admin user = adminService.newAdmin(createAdminDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	

	

}
