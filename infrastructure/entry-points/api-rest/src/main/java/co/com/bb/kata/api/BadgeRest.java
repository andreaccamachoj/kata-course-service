package co.com.bb.kata.api;


import co.com.bb.kata.model.userbadge.BadgeByCourse;
import co.com.bb.kata.model.userbadge.UserBadgesList;
import co.com.bb.kata.usecase.badge.BadgeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value = "/api/v1/badges", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BadgeRest {

    private final BadgeUseCase badgeUseCase;
    
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
}