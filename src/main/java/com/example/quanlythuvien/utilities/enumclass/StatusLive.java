package com.example.quanlythuvien.utilities.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusLive {
    CREATED(0), UPDATED(1), DELETED(2);
    private int value;
    public static StatusLive valueOf(int x) {
        for (StatusLive e : values()) {
            if (e.value == x) {
                return e;
            }
        }
        return null;
    }
}
