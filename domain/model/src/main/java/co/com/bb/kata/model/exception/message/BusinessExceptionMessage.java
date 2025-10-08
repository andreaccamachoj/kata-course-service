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
    ),
    CHAPTER_ALREADY_COMPLETED(
            "BUS0003", "The chapter has already been completed by the user", "404",
            "The chapter with the provided ID has already been completed by the user."
    ),
    NO_CHAPTERS_FOR_COURSE(
            "BUS0004", "The course has no registered chapters", "404",
            "The course with the provided ID has no registered chapters."
    ),
    COURSE_NOT_ASSIGNED(
            "BUS0005", "The user is not assigned to this course", "404",
            "The user is not assigned to the course with the provided ID."
    ),
    BADGE_NOT_FOUND(
            "BUS0006", "The badge for the specified course was not found", "404",
            "The badge for the course with the provided ID was not found."
    ),
    BADGE_ALREADY_ASSIGNED(
            "BUS0007",
            "The badge for the specified course is already assigned to the user.",
            "409",
            "The user already has this badge assigned. Duplicate assignments are not allowed."
    );


    private final String code;
    private final String description;
    private final String itcCode;
    private final String message;
}