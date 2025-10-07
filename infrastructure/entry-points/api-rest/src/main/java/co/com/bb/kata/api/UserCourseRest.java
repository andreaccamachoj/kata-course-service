package co.com.bb.kata.api;

import co.com.bb.kata.api.dto.request.UserAssignCourseRequest;
import co.com.bb.kata.api.dto.request.UserCompleteChapterRequest;
import co.com.bb.kata.model.usercourseprogress.CourseProgressResponse;
import co.com.bb.kata.model.usercourseprogress.UserCourseProgress;
import co.com.bb.kata.usecase.chapter.ChapterUseCase;
import co.com.bb.kata.usecase.usercourse.UserCourseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/training", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserCourseRest {

    private final UserCourseUseCase userCourseUseCase;
    private final ChapterUseCase chapterUseCase;

    @PostMapping("/coursesassign")
    public ResponseEntity<UserCourseProgress> assignCourseToUser(@RequestBody UserAssignCourseRequest request) {
        UserCourseProgress progress = userCourseUseCase.assignCourse(request.getUserId(), request.getCourseId());
        return ResponseEntity.status(HttpStatus.CREATED).body(progress);
    }

    @PostMapping("/chapters/complete")
    public ResponseEntity<Void> completeChapter(@RequestBody UserCompleteChapterRequest request) {
        chapterUseCase.completeChapter(request.getUserId(), request.getCourseId(), request.getChapterId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/courses/progress/{userId}")
    public ResponseEntity<List<CourseProgressResponse>> getUserCoursesProgress(@PathVariable("userId") Long userId) {
        List<CourseProgressResponse> progressList = userCourseUseCase.getUserCoursesProgress(userId);
        return ResponseEntity.ok(progressList);
    }

    @GetMapping("/courses/{courseId}/progress/{userId}")
    public ResponseEntity<CourseProgressResponse> getUserCourseProgress(
            @PathVariable("courseId") Long courseId,
            @PathVariable("userId") Long userId) {

        CourseProgressResponse progress = userCourseUseCase.getUserCourseProgress(userId, courseId);
        return ResponseEntity.ok(progress);
    }

}