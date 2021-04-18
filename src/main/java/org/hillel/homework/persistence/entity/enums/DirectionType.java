package org.hillel.homework.persistence.entity.enums;

import lombok.Getter;

@Getter
public enum DirectionType {
    FROM(1), TO(2), UNKNOWN(3);

    private final int code;

    DirectionType(int code) {
        this.code = code;
    }
}
