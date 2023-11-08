package org.kreyzon.mailjet.response;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse <T> {
    private String status;
    private String message;
    private T data;
}
