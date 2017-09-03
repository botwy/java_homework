package com.homework5.terminal;

public class PinValidator {
    private String right_pin="1111";

    public boolean checkPin(String pin) {
        if (pin.equals(right_pin)) return true;
        else return false;
    }
}
