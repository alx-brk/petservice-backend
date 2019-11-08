package com.petservice.backend.persistence.enums;

public enum JobStatus {
    NEW("Новый"),
    PERFORMED("Обработан");

    private String value;

    JobStatus(String value) {
        this.value = value;
    }
}
