package com.ies.utils;

import com.ies.constants.AppConstants;
import com.ies.entities.UserEntity;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class EmailUtil {

@Autowired
private JavaMailSender javaMailSender;


    public boolean sendEmail(String to,String subject, String body){
        boolean isSent=false;
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body,true);
            javaMailSender.send(mimeMessage);
            isSent=true;


        } catch (Exception e) {
           e.printStackTrace();
        }
          return isSent;
    }


    public String readEmailBody(String fileName, UserEntity userEntity){
        StringBuilder sb=new StringBuilder();
        try(Stream<String> lines= Files.lines(Paths.get(fileName))){
            lines.forEach(line->{
                line=line.replace(AppConstants.STR_READ_FNAME,userEntity.getFullName());
                line=line.replace(AppConstants.STR_READ_PWD,userEntity.getPazzword());
                line=line.replace(AppConstants.STR_READ_EMAIL,userEntity.getEmail());
                sb.append(line);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

}
