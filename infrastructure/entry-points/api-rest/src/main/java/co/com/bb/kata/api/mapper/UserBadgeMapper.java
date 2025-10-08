package co.com.bb.kata.api.mapper;

import co.com.bb.kata.api.dto.request.UserBadgeRequest;
import co.com.bb.kata.model.course.Course;
import co.com.bb.kata.model.userbadge.UserBadge;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserBadgeMapper {

    public UserBadge toDomain(UserBadgeRequest dto) {
        if (dto == null) {
            return null;
        }

        // Crear objeto Course con el ID recibido
        Course course = Course.builder()
                .id(dto.getCourseId())
                .build();

        // Construir el UserBadge de dominio
        return UserBadge.builder()
                .userId(dto.getUserId())
                .badgeId(dto.getBadgeId())
                .course(course)
                .awardedAt(dto.getAwardedAt() != null ? dto.getAwardedAt() : LocalDateTime.now())
                .build();
    }
}
