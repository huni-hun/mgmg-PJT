package com.b306.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ApiModel("UserChangeMusicPutRequest")
public class UserChangeMusicPutRequest {

    @NotEmpty
    Map<String, List<String>> musicTaste;

}
