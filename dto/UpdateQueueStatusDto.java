package com.hospital.hms.dto;

import com.hospital.hms.enums.QueueStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQueueStatusDto {
    @NotNull
    private QueueStatus queueStatus;
}
