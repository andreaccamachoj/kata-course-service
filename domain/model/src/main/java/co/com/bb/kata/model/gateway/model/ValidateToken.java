package co.com.bb.kata.model.gateway.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ValidateToken {
    private boolean valid;
    private String subject;
    private String role;
    private Long userId;
}
