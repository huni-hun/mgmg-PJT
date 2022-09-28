package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.db.entity.InterestGift;
import com.ssafy.mgmgproject.db.entity.InterestMusic;
import com.ssafy.mgmgproject.db.entity.User;
import com.ssafy.mgmgproject.db.repository.InterestGiftRepository;
import com.ssafy.mgmgproject.db.repository.InterestMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InterestServiceImpl implements InterestService{

    @Autowired
    InterestMusicRepository interestMusicRepository;

    @Autowired
    InterestGiftRepository interestGiftRepository;

    @Override
    public List<InterestMusic> searchInterestMusic(User user) {
        return interestMusicRepository.findByUser(user);
    }

    @Override
    public List<InterestGift> searchInterestGift(User user) {
        return interestGiftRepository.findByUser(user);
    }

    @Override
    @Transactional
    public void deleteInterestMusic(User user, long musicNo) {
        interestMusicRepository.deleteByUserAndMusic_MusicNo(user, musicNo);
    }

    @Override
    @Transactional
    public void deleteInterestGift(User user, long giftNo) {
        interestGiftRepository.deleteByUserAndGift_GiftNo(user, giftNo);
    }
}
