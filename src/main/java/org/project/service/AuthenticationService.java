package org.project.service;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.project.model.User;
import org.project.DTO.SignUpRequest;
import org.project.DTO.SignInRequest;
import org.project.DTO.JwtAuthenticationResponse;
import org.project.model.RefreshToken;
import org.project.service.RefreshTokenService;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    public AuthenticationService(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    
public JwtAuthenticationResponse refreshToken(String refreshToken) {
    RefreshToken token = refreshTokenService.findByToken(refreshToken)
            .orElseThrow(() -> new RuntimeException("Refresh token not found"));

    if (refreshTokenService.isRefreshTokenExpired(token)) {
        refreshTokenService.deleteByUser(token.getUser());
        throw new RuntimeException("Refresh token expired. Please login again.");
    }

    String accessToken = jwtService.generateToken(token.getUser());
    return new JwtAuthenticationResponse(accessToken, refreshToken);
}

/**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
                userService.register(user);

                var accessToken = jwtService.generateToken(user);
                var refreshToken = refreshTokenService.createRefreshToken(user).getToken();
                return new JwtAuthenticationResponse(accessToken, refreshToken);
            }

public JwtAuthenticationResponse signIn(SignInRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
    ));

    var user = userService
            .userDetailsService()
            .loadUserByUsername(request.getUsername());

    var accessToken = jwtService.generateToken(user);
    var refreshToken = refreshTokenService.createRefreshToken((User) user).getToken();
    return new JwtAuthenticationResponse(accessToken, refreshToken);
}

}
