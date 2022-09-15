package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("NotificationListResponse")
public class NotificationListResponse extends BaseResponseBody{

    @ApiModelProperty(name = "알림 목록")
    List<NotificationListMapping> notifications;

    public static NotificationListResponse of(List<NotificationListMapping> notifications, Integer statusCode, String message) {
        NotificationListResponse res = new NotificationListResponse();
        res.setNotifications(notifications);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
