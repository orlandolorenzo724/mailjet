package org.kreyzon.mailjet.service;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kreyzon.mailjet.request.MailRequest;
import org.kreyzon.mailjet.response.APIResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MailjetService {

    @Value("${mailjet.api-key}")
    private String apiKey;

    @Value("${mailjet.secret-key}")
    private String secretKey;

    @Value("${mailjet.email}")
    private String email;

    @Value("${mailjet.name}")
    private String name;

    public APIResponse sendEmail(List<MailRequest> mailRequestList)  {
        MailjetClient client = new MailjetClient(apiKey, secretKey, new ClientOptions("v3.1"));

        List<String> failedReceivers = new ArrayList<>();

        mailRequestList
                .forEach(mailRequest -> {
                    log.info("Sending email to: " + mailRequest.getReceiver());
                    MailjetRequest request = new MailjetRequest(Emailv31.resource)
                            .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                            .put(Emailv31.Message.FROM, new JSONObject()
                            .put("Email", email)
                            .put("Name", name))
                            .put(Emailv31.Message.TO, new JSONArray()
                            .put(new JSONObject()
                            .put("Email", mailRequest.getReceiver())
                            .put("Name", name)))
                            .put(Emailv31.Message.SUBJECT, mailRequest.getSubject())
                            .put(Emailv31.Message.HTMLPART, mailRequest.getText())
                            //.put(Emailv31.Message.TEXTPART, mailRequest.getText())
                            .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
                    MailjetResponse response = null;
                    try {
                        response = client.post(request);
                    } catch (MailjetException | MailjetSocketTimeoutException e) {
                        failedReceivers.add(mailRequest.getReceiver());
                    }

                    log.info("Email sent to: " + mailRequest.getReceiver());
                    log.info("Response: " + response.getData());
                }
        );

        if (failedReceivers.size() > 0) {
            return APIResponse
                    .builder()
                    .status("ERROR")
                    .message("Email not sent to some receivers")
                    .data(failedReceivers)
                    .build();
        }

        return APIResponse
                .builder()
                .status("OK")
                .message("Email sent to all receivers")
                .data(mailRequestList)
                .build();
    }
}
