package com.cinema.service.rest.dto;

import com.cinema.service.rest.validation.annotation.ValidSessionTime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ValidSessionTime(startTimeField = "sessionTime", endTimeField = "sessionEndTime", message = "Session end time must be after session start time")
public class CreateSessionRequest {
    @Future(message = "it's not possible to create a session in the past")
    private LocalDateTime sessionTime;
    @Future(message = "it's not possible to create a session ending in the past")
    private LocalDateTime sessionEndTime;
    @NotNull(message = "session base price required")
    @Positive(message = "session base price cannot be zero or negative")
    private BigDecimal basePrice;
}
