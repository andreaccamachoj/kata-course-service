package co.com.bb.kata.consumer.dto.response;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String identityDocument;
    private String phone;
    private Long roleId;
    private String roleName;

}