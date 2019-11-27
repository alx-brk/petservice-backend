package com.petservice.backend.persistence.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JobStatus {
    NEW("Новый"),
    PERFORMED("Обработан");

    private String value;

    JobStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
