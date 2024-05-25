package com.cinema.service.domain.repository;

import com.cinema.service.domain.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN FALSE ELSE TRUE END " +
            "FROM Session s " +
            "WHERE s.room.id = :roomId " +
            "AND :sessionTime <= s.sessionTime " +
            "AND :sessionEndTime >= s.sessionTime")
    Boolean isRoomEmptyAtThisTime(@Param("sessionTime") LocalDateTime sessionTime,
                                  @Param("sessionEndTime") LocalDateTime sessionEndTime,
                                  @Param("roomId") Long roomId);
}
