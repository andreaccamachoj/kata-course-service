package co.com.bb.kata.api;

import co.com.bb.kata.api.dto.request.UserBadgeRequest;
import co.com.bb.kata.api.dto.request.UserCompleteChapterRequest;
import co.com.bb.kata.api.mapper.UserBadgeMapper;
import co.com.bb.kata.model.course.Course;
import co.com.bb.kata.model.userbadge.BadgeByCourse;
import co.com.bb.kata.model.userbadge.UserBadge;
import co.com.bb.kata.model.userbadge.UserBadgesList;
import co.com.bb.kata.usecase.badge.BadgeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/v1/badges", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BadgeRest {

    private final BadgeUseCase badgeUseCase;
    private final UserBadgeMapper userBadgeMapper;
    
    @GetMapping("/me/{userId}")
    public ResponseEntity<List<UserBadgesList>> getMyBadges(@PathVariable("userId") Long userId) {
        List<UserBadgesList> badges = badgeUseCase.getBadgesByUser(userId);
        return ResponseEntity.ok(badges);
    }

    @GetMapping("/{courseId}/badge")
    public ResponseEntity<BadgeByCourse> getBadgeByCourseId(@PathVariable("courseId") Long courseId) {
        BadgeByCourse badge = badgeUseCase.getBadgeByCourseId(courseId);
        return ResponseEntity.ok(badge);
    }


    @PostMapping("/assingBadge")
    public ResponseEntity<UserBadge> assignBadgeToUser(@RequestBody UserBadgeRequest request) {
        UserBadge userBadge = userBadgeMapper.toDomain(request);

        UserBadge assigned = badgeUseCase.assignBadgeToUser(userBadge);
        return ResponseEntity.status(HttpStatus.CREATED).body(assigned);
    }

}