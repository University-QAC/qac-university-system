package com.effortcure.qac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effortcure.qac.dto.request.LoginRequestDTO;
import com.effortcure.qac.dto.request.RegisterRequestDTO;
import com.effortcure.qac.dto.request.VerificationCodeRequestDTO;
import com.effortcure.qac.dto.response.LoginResponseDTO;
import com.effortcure.qac.payload.ApiResponse;
import com.effortcure.qac.service.interfaces.AuthServiceInterface;
import com.effortcure.qac.swagger.EmptyResponse;
import com.effortcure.qac.swagger.LoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthServiceInterface authServiceInterface;

    @Autowired
    public AuthController(AuthServiceInterface authServiceInterface) {
        this.authServiceInterface = authServiceInterface;
    }

    // #region == API Doc ==
    // @formatter:off
    @Operation(
        summary = "Check email existance", 
        description = "Check if the email is registered before", 
        parameters = {
            @Parameter(
                name = "email",
                description = "Email must must match a-z, A-Z, 0-9, [._%+-] @gmail.com",
                required = true,
                example = "mostafanahas777@gmail.com"
            ),
            @Parameter(
                name = "User-Agent",
                description = "Client info",
                schema = @Schema(
                    type = "string",
                    example = "JavaFxApp: OS-Windows10 OS-VERSION-10 ARCH-x64"
                )
            )
        },
        responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "Email doesn't exist",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "409",
                description = "Email already exist",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            )
        }
    )
    // @formatter:on
    // #endregion
    @GetMapping("/email-exists/{email}")
    public ResponseEntity<ApiResponse<Void>> checkEmailExistance(@PathVariable String email) {
        authServiceInterface.checkEmailExistance(email);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200,
                "Email '" + email + "' not exist", null));
    }

    // #region == API Doc ==
    // @formatter:off
    @Operation(
        summary = "Create new account", 
        description = "Create a new account", 
        parameters = {
            @Parameter(
                name = "User-Agent",
                description = "Client info",
                schema = @Schema(
                    type = "string",
                    example = "JavaFxApp: OS-Windows10 OS-VERSION-10 ARCH-x64"
                )
            )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Account creation payload",
            required = true, 
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = RegisterRequestDTO.class)
            )
        ),
        responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "201",
                description = "Account created successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "409",
                description = "Email already exist",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "500",
                description = "Email sending error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            )
        }
    )
    // @formatter:on
    // #endregion
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> registerUser(
            @RequestBody RegisterRequestDTO registerRequestDTO) {
        authServiceInterface.createNewAccount(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201,
                        "Verification code is sent to " + registerRequestDTO.getEmail(), null));
    }

    // #region == API Doc ==
    // @formatter:off
    @Operation(
        summary = "Remove unverified account", 
        description = "Omit unverifired account after register from the database", 
        parameters = {
            @Parameter(
                name = "email",
                description = "Email must must match a-z, A-Z, 0-9, [._%+-] @gmail.com",
                required = true,
                example = "mostafanahas777@gmail.com"
            ),
            @Parameter(
                name = "User-Agent",
                description = "Client info",
                schema = @Schema(
                    type = "string",
                    example = "JavaFxApp: OS-Windows10 OS-VERSION-10 ARCH-x64"
                )
            )
        },
        responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "204",
                description = "Account removed successfully",
                content = @Content(
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "Email doesn't exist",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            )
        }
    )
    // @formatter:on
    // #endregion
    @DeleteMapping("/remove-unverified-account/{email}")
    public ResponseEntity<ApiResponse<Void>> removeUnverifiedAccount(@PathVariable String email) {
        authServiceInterface.removeUnverifiedAccount(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // #region == API Doc ==
    // @formatter:off
    @Operation(
        summary = "Verify email", 
        description = "Verify email ownership using sent verification code", 
        parameters = {
            @Parameter(
                name = "User-Agent",
                description = "Client info",
                schema = @Schema(
                    type = "string",
                    example = "JavaFxApp: OS-Windows10 OS-VERSION-10 ARCH-x64"
                )
            )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Email verification payload",
            required = true, 
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = VerificationCodeRequestDTO.class)
            )
        ),
        responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "Email is verified successfully",
                headers = {
                    @Header(
                        name = "Set-Cookie",
                        description = "HTTP-only cookie containing refresh token",
                        schema = @Schema(
                            type = "string",
                            example = "refresh-token=eyJhbGciOiJIUzI1Ni...; HttpOnly; Path=/; Max-Age=3600; Secure; SameSite=Strict"
                        )
                    )
                },
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = LoginResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "Email doesn't exist",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "401",
                description = "Wrong verification code",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "Expired verification code",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            )
        }
    )
    // @formatter:on
    // #endregion
    @PostMapping("/verify-email")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> verifyEmail(
            @RequestBody VerificationCodeRequestDTO verificationCodeRequestDTO,
            HttpServletRequest request) {

        LoginResponseDTO loginResponseDTO = authServiceInterface
                .validateEmailVerificationCode(verificationCodeRequestDTO, request);

        ResponseCookie refreshTokenCookie = ResponseCookie
                .from("refresh-token", loginResponseDTO.getRefreshToken())
                .httpOnly(true)
                .path("/")
                .maxAge(loginResponseDTO.getRefreshTokenExpiresIn() / 1000)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(new ApiResponse<>(200, "Your email '" + verificationCodeRequestDTO.getEmail()
                        + "' is verified successfully", loginResponseDTO));
    }

    // #region == API Doc ==
    // @formatter:off
    @Operation(
        summary = "Login", 
        description = "Login to vigy bubble", 
        parameters = {
            @Parameter(
                name = "User-Agent",
                description = "Client info",
                schema = @Schema(
                    type = "string",
                    example = "JavaFxApp: OS-Windows10 OS-VERSION-10 ARCH-x64"
                )
            )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Login payload",
            required = true, 
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = LoginRequestDTO.class)
            )
        ),
        responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "Login succeed",
                headers = {
                    @Header(
                        name = "Set-Cookie",
                        description = "HTTP-only cookie containing refresh token",
                        schema = @Schema(
                            type = "string",
                            example = "refresh-token=eyJhbGciOiJIUzI1Ni...; HttpOnly; Path=/; Max-Age=3600; Secure; SameSite=Strict"
                        )
                    )
                },
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = LoginResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "Email doesn't exist",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "403",
                description = "Account is not verified, we have sent an email with a verification code",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "500",
                description = "Email sending exception",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "401",
                description = "Wrong password",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            )
        }
    )
    // @formatter:on
    // #endregion
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> authenticateUser(
            @RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest request) {
        LoginResponseDTO loginResponseDTO = authServiceInterface.authenticateAccount(loginRequestDTO,
                request);

        ResponseCookie refreshTokenCookie = ResponseCookie
                .from("refresh-token", loginResponseDTO.getRefreshToken())
                .httpOnly(true)
                .path("/")
                .maxAge(loginResponseDTO.getRefreshTokenExpiresIn() / 1000)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(new ApiResponse<>(200, "Login succeed", loginResponseDTO));
    }

    // #region == API Doc ==
    // @formatter:off
    @Operation(
        summary = "Refresh access and refresh tokens", 
        description = "Generate new access and refresh tokens using a valid refresh token and revoke this refresh token", 
        parameters = {
            @Parameter(
                name = "Set-Cookie",
                description = "HTTP-only cookie containing the old refresh token",
                required = true,
                schema = @Schema(
                    type = "string",
                    example = "refresh-token=eyJhbGciOiJIUzI1Ni...; HttpOnly; Path=/; Max-Age=3600; Secure; SameSite=Strict"
                )
            ),
            @Parameter(
                name = "User-Agent",
                description = "Client info",
                schema = @Schema(
                    type = "string",
                    example = "JavaFxApp: OS-Windows10 OS-VERSION-10 ARCH-x64"
                )
            )
        },
        responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "Access and refresh tokens have been refreshed",
                headers = {
                    @Header(
                        name = "Set-Cookie",
                        description = "HTTP-only cookie containing new refresh token",
                        schema = @Schema(
                            type = "string",
                            example = "refresh-token=eyJhbGciOiJIUzI1Ni...; HttpOnly; Path=/; Max-Age=3600; Secure; SameSite=Strict"
                        )
                    )
                },
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = LoginResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "Refresh token / account not found",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "Refresh token is expired / revoked",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "401",
                description = "Refresh token is invalid",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            )
        }
    )
    // @formatter:on
    // #endregion
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> refreshAccessToken(HttpServletRequest request) {
        LoginResponseDTO loginResponseDTO = authServiceInterface.refreshAccessToken(request);

        ResponseCookie refreshTokenCookie = ResponseCookie
                .from("refresh-token", loginResponseDTO.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(loginResponseDTO.getRefreshTokenExpiresIn() / 1000)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(new ApiResponse<>(200, "Access and refresh tokens have been refreshed", loginResponseDTO));
    }

    // #region == API Doc ==
    // @formatter:off
    @Operation(
        summary = "Logout", 
        description = "Logout from current device and revoke refeshtoken", 
        parameters = {
            @Parameter(
                name = "Set-Cookie",
                description = "HTTP-only cookie containing the old refresh token",
                required = true,
                schema = @Schema(
                    type = "string",
                    example = "refresh-token=eyJhbGciOiJIUzI1Ni...; HttpOnly; Path=/; Max-Age=3600; Secure; SameSite=Strict"
                )
            ),
            @Parameter(
                name = "User-Agent",
                description = "Client info",
                schema = @Schema(
                    type = "string",
                    example = "JavaFxApp: OS-Windows10 OS-VERSION-10 ARCH-x64"
                )
            )
        },
        responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "Logout succeed",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            )
        }
    )
    // @formatter:on
    // #endregion
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request) {
        authServiceInterface.logOut(request);
        ResponseCookie refreshTokenCookie = ResponseCookie
                .from("refresh-token", null)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(new ApiResponse<>(200, "Logout succeed", null));
    }

    // #region == API Doc ==
    // @formatter:off
    @Operation(
        summary = "Forget password",
        description = "Unverify the account and send verification code to check account ownership",
        parameters = {
            @Parameter(
                name = "email",
                description = "Email must must match a-z, A-Z, 0-9, [._%+-] @gmail.com",
                required = true,
                example = "mostafanahas777@gmail.com"
            ),
            @Parameter(
                name = "User-Agent",
                description = "Client info",
                schema = @Schema(
                    type = "string",
                    example = "JavaFxApp: OS-Windows10 OS-VERSION-10 ARCH-x64"
                )
            )
        },
        responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "Verification code is sent to your email",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "Email doesn't exist",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "500",
                description = "Email sending error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            )
        }
    )
    // @formatter:on
    // #endregion
    @PostMapping("/forget-password/{email}")
    public ResponseEntity<ApiResponse<Void>> forgetPassword(@PathVariable String email) {
        authServiceInterface.sendVerificationCode(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "Verification code is sent to " + email, null));
    }

    // #region == API Doc ==
    // @formatter:off
    @Operation(
        summary = "Resend verification code",
        description = "Resend a verification code to your email",
        parameters = {
            @Parameter(
                name = "email",
                description = "Email must must match a-z, A-Z, 0-9, [._%+-] @gmail.com",
                required = true,
                example = "mostafanahas777@gmail.com"
            ),
            @Parameter(
                name = "User-Agent",
                description = "Client info",
                schema = @Schema(
                    type = "string",
                    example = "JavaFxApp: OS-Windows10 OS-VERSION-10 ARCH-x64"
                )
            )
        },
        responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "Verification code is sent to your email",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "404",
                description = "Email doesn't exist",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "500",
                description = "Email sending error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmptyResponse.class)
                )
            )
        }
    )
    // @formatter:on
    // #endregion
    @PostMapping("/resend-code/{email}")
    public ResponseEntity<ApiResponse<Void>> resendVerificationCode(@PathVariable String email) {
        authServiceInterface.sendVerificationCode(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, "Verification code is sent to " + email, null));
    }
}
