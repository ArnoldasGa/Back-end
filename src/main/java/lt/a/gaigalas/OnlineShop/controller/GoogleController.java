package lt.a.gaigalas.OnlineShop.controller;

import lt.a.gaigalas.OnlineShop.dtos.LoginUserDto;
import lt.a.gaigalas.OnlineShop.dtos.RegisterUserDto;
import lt.a.gaigalas.OnlineShop.model.User;
import lt.a.gaigalas.OnlineShop.responses.LoginResponse;
import lt.a.gaigalas.OnlineShop.services.AuthenticationService;
import lt.a.gaigalas.OnlineShop.services.JwtService;
import lt.a.gaigalas.OnlineShop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/google")
@RestController
public class GoogleController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    public static String password = "VeryHardPassword1234";
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public GoogleController(UserService userService, AuthenticationService authenticationService, JwtService jwtService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping
    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal OidcUser user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        // If the user is not registered yet, we register them
        if (userService.findUserByEmail(user.getEmail()).isEmpty()) {
            if (userService.findUserByUsername(user.getEmail()).isEmpty()) {
                String hashedPassword = passwordEncoder.encode(password);  // Use password encoder for security
                RegisterUserDto registerUserDto = new RegisterUserDto(user.getEmail(), password, user.getFullName(), "1");

                // Register the user
                User registeredUser = authenticationService.signup(registerUserDto);

                // Return registered user details
                return ResponseEntity.ok(registeredUser);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
            }
        } else {
            // If the user already exists, authenticate them
            LoginUserDto loginUserDto = new LoginUserDto(user.getFullName(), password);  // Default password handling should be improved
            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            // Generate JWT token
            String jwtToken = jwtService.generateToken(authenticatedUser);

            // Create and return LoginResponse
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());

            return ResponseEntity.ok(loginResponse);
        }
    }

}
