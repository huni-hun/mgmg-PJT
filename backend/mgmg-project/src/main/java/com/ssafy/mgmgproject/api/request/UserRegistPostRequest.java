package com.ssafy.mgmgproject.api.request;

import com.ssafy.mgmgproject.db.entity.GiftCategory;
import com.ssafy.mgmgproject.db.entity.MusicGenre;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserRegistPostRequest {

    @NotEmpty
    String email;

    @NotEmpty
    String password;

    @NotEmpty
    String userId;

    @NotNull
    Date birth;

    @NotEmpty
    String name;

    @NotEmpty
    String gender;

    @NotEmpty
    List<String> musicTaste;

    @NotEmpty
    List<String> giftTaste;
}
