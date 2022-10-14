package com.npv.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.npv.dto.JwtResponseDTO;
import com.npv.dto.SigninDTO;
import com.npv.dto.SignupDTO;
import com.npv.entity.Account;
import com.npv.repository.IAccountRepository;
import com.npv.service.IAccountService;
import com.npv.utils.JwtUtils;

@RestController
@RequestMapping(value = "api/auth")
@Validated
@CrossOrigin("*")
public class AuthController {
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IAccountService acService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private IAccountRepository acRepository;
	
	@GetMapping("/active_account")
	public void activeAccount(@RequestParam(name = "id") int accountId) {
		acService.activeAccount(accountId);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody SigninDTO signinDTO){
		System.out.println("SigninDTO: ");
		System.out.println(signinDTO);
		
		Account ac = acRepository.findByUsername(signinDTO.getUsername());
		
		System.out.println(ac);
		
		if (ac == null) throw new UsernameNotFoundException(signinDTO.getUsername());
		
		if (ac.getStatus().toString().equals("NOT_ACTIVE")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error: User not actived");
		}
		
		try {
			Authentication auth = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(signinDTO.getUsername(), signinDTO.getPassword())
			);
		
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			String jwtToken = jwtUtils.generateJwtToken(auth);
			
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			
			return ResponseEntity.ok(new JwtResponseDTO(
				jwtToken, userDetails.getUsername(), userDetails.getAuthorities().toString()
			));
		}catch (Exception ex) {
			System.out.println("Exception authentication: ");
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password invalid!");
		}
	}
	
	@PostMapping("/signup") 
	@Transactional
	public ResponseEntity<?> registerUser(@RequestBody @Valid SignupDTO signupDTO) {
		System.out.println(signupDTO.toString());
		if (acRepository.existsByUsername(signupDTO.getUsername())) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}
		
		if (acRepository.existsByEmail(signupDTO.getEmail())) {
			return ResponseEntity.badRequest().body("Error: Email is already taken!");
		}
		
		Account ac = modelMapper.map(signupDTO, Account.class);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String enCryptPassword = passwordEncoder.encode(signupDTO.getPassword());
		ac.setPassword(enCryptPassword);
		
		ac.setStatus(Account.AccountStatus.NOT_ACTIVE);
		
		//acRepository.save(ac);
		
		acService.createAccount(ac);//Send mail active
		
		return ResponseEntity.ok().body("User registered successfully!");
	}
	
}
