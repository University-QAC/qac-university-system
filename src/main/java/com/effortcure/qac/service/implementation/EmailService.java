package com.effortcure.qac.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.effortcure.qac.exception.EmailSendingException;
import com.effortcure.qac.service.interfaces.EmailServiceInterface;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@Primary
public class EmailService implements EmailServiceInterface {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendVerificationCodeEmail(String email, String verificationCode) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email);
            helper.setSubject("Verification code");
            String html = """
                    <html>
                        <body style="margin:0; padding:0; font-family: Arial, sans-serif; background-color:#f4f6f9;">
                            <div style="max-width:600px; margin:30px auto; padding:20px; text-align:center;">
                                <img src="cid:logo" width="120" style="margin-bottom:20px;" />
                                <h2 style="margin-bottom:20px;">
                                    Please, verify your identity
                                </h2>
                                <!-- Inner bordered box -->
                                <div style="border:1px solid #177B75; padding:20px; border-radius:6px;">
                                    <p>
                                        Here is your University QAC account authentication code:
                                    </p>
                                    <h3 style="font-size:28px; margin:15px 0;">
                                        %s
                                    </h3>
                                    <p>
                                        This code is valid for <strong>10 minutes</strong> and can only be used once.
                                    </p>
                                    <p>
                                        <strong>Please don't share this code with anyone:</strong>
                                        we'll never ask for it on the phone or via email.
                                    </p>
                                    <p style="margin-top:20px;">
                                        Thanks,<br/>
                                        University QAC team
                                    </p>
                                </div>
                            </div>
                        </body>
                    </html> """;
            String content = html.formatted(verificationCode);
            helper.setText(content, true);
            helper.addInline("logo", new ClassPathResource("static/logo.png"));
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendingException("Failed to send verification email to: " + email, e);
        }
    }

}
