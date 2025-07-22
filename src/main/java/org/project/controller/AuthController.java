package org.project.controller;

import org.project.service.AuthenticationService;
import org.project.DTO.SignUpRequest;
import org.project.DTO.SignInRequest;
import org.project.DTO.JwtAuthenticationResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
    @PostMapping("/log-out")
    public String logOut(HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(null);
        ResponseCookie cookie = ResponseCookie.from("Authorization", "")
      .httpOnly(true)
      .path("/")
      .maxAge(0)
      .build();
  response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
  response.setStatus(HttpStatus.OK.value());
        return "Logged out successfully";
    }
}
