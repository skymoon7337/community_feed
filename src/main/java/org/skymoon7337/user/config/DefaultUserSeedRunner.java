package org.skymoon7337.user.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 유저 1~5 없으면 생성, 있으면 무시
 */
@Component
@Profile("dev")
@RequiredArgsConstructor
public class DefaultUserSeedRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        for (long userId = 1; userId <= 5; userId++) {
            seedUserIfAbsent(userId, "user" + userId);
        }
    }

    private void seedUserIfAbsent(Long userId, String name) {
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from community_user where id = ?",
                Integer.class,
                userId
        );

        if (count != null && count > 0) {
            return;
        }

        jdbcTemplate.update(
                "insert into community_user "
                        + "(id, name, profile_image, follower_count, following_count, reg_dt, up_dt) "
                        + "values (?, ?, ?, ?, ?, now(), now())",
                userId,
                name,
                null,
                0,
                0
        );
    }
}
