package com.effortcure.qac.swagger;

import com.effortcure.qac.payload.ApiResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "EmptyResponse")
public class EmptyResponse extends ApiResponse<Void> {

}