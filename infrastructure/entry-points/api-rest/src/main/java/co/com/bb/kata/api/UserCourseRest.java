package co.com.bb.kata.api;

import co.com.bb.kata.api.dto.request.UserAssignCourseRequest;
import co.com.bb.kata.model.usercourseprogress.UserCourseProgress;
import co.com.bb.kata.usecase.usercourse.UserCourseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/training", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserCourseRest {

    private final UserCourseUseCase userCourseUseCase;

    @PostMapping("/coursesassign")
    public ResponseEntity<UserCourseProgress> assignCourseToUser(@RequestBody UserAssignCourseRequest request) {
        UserCourseProgress progress = userCourseUseCase.assignCourse(request.getUserId(), request.getCourseId());
        return ResponseEntity.status(HttpStatus.CREATED).body(progress);
    }

}
