package com.effortcure.qac.swagger;

import com.effortcure.qac.dto.response.LoginResponseDTO;
import com.effortcure.qac.payload.ApiResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginResponse")
public class LoginResponse extends ApiResponse<LoginResponseDTO> {

}
