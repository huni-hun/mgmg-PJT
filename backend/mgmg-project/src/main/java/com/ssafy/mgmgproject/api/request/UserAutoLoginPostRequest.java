package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserAutoLoginPostRequest")
public class UserAutoLoginPostRequest {
	@ApiModelProperty(name = "회원 아이디", example = "user_id")
	String userId;

	@ApiModelProperty(name = "JWT 인증 refresh 토큰", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String refreshToken;

}