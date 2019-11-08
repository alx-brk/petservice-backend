package com.petservice.backend.persistence.enums;

public enum Units {
    RUB("руб"),
    RUB_DAY("руб/день"),
    RUB_HOUR("руб/час");


    private String value;

    Units(String value) {
        this.value = value;
    }
}
