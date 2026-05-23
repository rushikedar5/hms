package com.hospital.hms.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum QueueStatus {
    WAITING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    @JsonCreator
    public static QueueStatus fromValue(String value) {
        return QueueStatus.valueOf(value.toUpperCase());
    }
}