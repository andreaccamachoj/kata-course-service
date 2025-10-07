package co.com.bb.kata.model.userbadge;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserBadgesList {
    private Long id;
    private Long userId;
    private Long badgeId;
    private String badgeName;
    private String badgeDescription;
    private String badgeIconUrl;
    private LocalDateTime assignedAt;
}