package com.techkritika.chicken99_backend.enums;

public enum AddressType {
    HOME("HOME"),
    WORK("WORK"),
    BILLING("BILLING"),
    SHIPPING("SHIPPING"),
    OTHER("OTHER");

    private final String value;

    AddressType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}