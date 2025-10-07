package co.com.bb.kata.model.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessExceptionMessage {
    COURSE_ALREADY_ASSIGNED(
            "BUS0002", "The course is already assigned to this user.", "409",
            "The user already has this course assigned in their profile."
    ),
    USER_NOT_FOUND(
            "BUS0001", "The user does not exist in the authentication service.", "404",
            "The user with the provided ID was not found in the authentication service."
    );

    private final String code;
    private final String description;
    private final String itcCode;
    private final String message;
}
