package com.SpringBootEmailing.utility;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmailUtility {
    public String creatMessageText(String name, String host, String token)
    {
        String str = "Hello " + name + ".\n\nYour new account was Successfully created.\nClick link below to verify.\n\n" + createVerificationUrl(token, host);
        return (str);
    }
    public static String createVerificationUrl(String token, String host)
    {
        return (host + "/api/users?token=" + token);
    }

}
