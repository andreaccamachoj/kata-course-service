package co.com.bb.kata.model.badge.gateways;

import co.com.bb.kata.model.userbadge.BadgeByCourse;

public interface BadgeRepository {
    BadgeByCourse findByCourseId(Long courseId);
}
