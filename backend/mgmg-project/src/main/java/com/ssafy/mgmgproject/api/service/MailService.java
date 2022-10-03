package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.dto.Mail;

public interface MailService {

    Mail createTempPwMail(String tmpPassword, String userEmail);

    Mail createAuthMail(String userEmail);

    String checkAuthKey(String authKey);

    void deleteAuthKey(String authKey);

    void sendMail(Mail mail);

    String getTmpPassword();

}
