package com.cinema.service.domain.repository;

import com.cinema.service.domain.entity.Session;
import com.cinema.service.rest.dto.SessionListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN FALSE ELSE TRUE END " +
            "FROM Session s " +
            "WHERE s.room.id = :roomId " +
            "AND :sessionTime <= s.sessionStartTime " +
            "AND :sessionEndTime >= s.sessionStartTime")
    Boolean isRoomEmptyAtThisTime(@Param("sessionTime") LocalDateTime sessionStartTime,
                                  @Param("sessionEndTime") LocalDateTime sessionEndTime,
                                  @Param("roomId") Long roomId);

    @Query("SELECT new com.cinema.service.rest.dto.SessionListResponse(s.id, s.sessionStartTime, s.sessionEndTime, s.basePrice, s.movie, s.room) " +
            "FROM Session s " +
            "JOIN s.movie m " +
            "JOIN s.room r")
    Page<SessionListResponse> findAllSessionsPaginated(Pageable pageable);
}
