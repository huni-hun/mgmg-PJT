package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("NotificationResponse")
public class NewNotificationResponse extends BaseResponseBody {

    @ApiModelProperty(name = "신규알림여부")
    boolean notificationFlag;

    public static NewNotificationResponse of(boolean notificationFlag, Integer statusCode, String message) {
        NewNotificationResponse res = new NewNotificationResponse();
        res.setNotificationFlag(notificationFlag);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
