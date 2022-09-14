package com.ssafy.mgmgproject.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFindPwPostRequest {
    String userId;

    String email;
}
