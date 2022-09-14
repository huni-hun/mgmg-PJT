package com.ssafy.mgmgproject.api.request;

import com.ssafy.mgmgproject.db.entity.GiftCategory;
import com.ssafy.mgmgproject.db.entity.MusicGenre;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class UserRegistPostReq {

    @NotEmpty
    String email;

    @NotEmpty
    String password;

    @NotEmpty
    String userId;

    @NotEmpty
    String birth;

    @NotEmpty
    String name;

    @NotEmpty
    String gender;

    @NotEmpty
    List<MusicGenre> musicTaste;

    @NotEmpty
    List<GiftCategory> giftTaste;
}
