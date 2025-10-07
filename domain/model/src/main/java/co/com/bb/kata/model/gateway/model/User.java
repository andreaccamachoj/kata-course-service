package co.com.bb.kata.model.gateway.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String identityDocument;
    private String phone;
    private Long roleId;
    private String roleName;
}
