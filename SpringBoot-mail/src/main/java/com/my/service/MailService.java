package com.my.service;

import java.util.Map;


public interface MailService {

    public void sendSimpleMail(String to, String subject, String content);

    public void sendHtmlMail(String to, String subject, String content);

    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
    
    public void sendMimeMessge(String to, String subject, String content, Map<String, String> rscIdMap) ;

}