package com.b306.mgmgproject.api.service;

import com.b306.mgmgproject.db.entity.InterestGift;
import com.b306.mgmgproject.db.entity.InterestMusic;
import com.b306.mgmgproject.db.entity.User;

import java.util.List;

public interface InterestService {

    List<InterestMusic> searchInterestMusic(User user);

    List<InterestGift> searchInterestGift(User user);

    void deleteInterestMusic(User user, long musicNo);

    void deleteInterestGift(User user, long giftNo);
    
}
