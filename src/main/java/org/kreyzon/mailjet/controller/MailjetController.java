package org.kreyzon.mailjet.controller;

import lombok.RequiredArgsConstructor;
import org.kreyzon.mailjet.request.MailRequest;
import org.kreyzon.mailjet.response.APIResponse;
import org.kreyzon.mailjet.service.MailjetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kreyzon/api/v1/mailjet")
@RequiredArgsConstructor
public class MailjetController {

    private final MailjetService mailjetService;

    @PostMapping
    public ResponseEntity<APIResponse> send(@RequestBody List<MailRequest> mailRequestList) {
        APIResponse response = mailjetService.sendEmail(mailRequestList);
        return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
    }
}
