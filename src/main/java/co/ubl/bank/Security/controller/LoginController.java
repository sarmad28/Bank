package co.ubl.bank.Security.controller;

import co.ubl.bank.DTO.AuthDTO;
import co.ubl.bank.Entity.AuthEntity;
import co.ubl.bank.ExceptionHandler.CustomResponse;
import co.ubl.bank.Repository.AuthRepository;
import co.ubl.bank.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;

    private final AuthService authService;

    public LoginController(PasswordEncoder passwordEncoder, AuthRepository authRepository, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.authRepository = authRepository;
        this.authService = authService;
    }

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO authDTO) {
        AuthDTO byUserName = authService.findByUserName(authDTO);
        throw new CustomResponse("Success", HttpStatus.OK, byUserName);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        authRepository.save(user);
        throw new CustomResponse("User Has been created successfully", HttpStatus.OK, user);
    }
}
