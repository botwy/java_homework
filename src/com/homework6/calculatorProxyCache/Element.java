package com.homework6.calculatorProxyCache;

public class Element {
   final float operand1;
   final float operand2;
   final String operator;

    public Element(float operand1, float operand2, String operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        if (Float.compare(element.operand1, operand1) != 0) return false;
        if (Float.compare(element.operand2, operand2) != 0) return false;
        return operator.equals(element.operator);
    }

    @Override
    public int hashCode() {
        int result = (operand1 != +0.0f ? Float.floatToIntBits(operand1) : 0);
        result = 31 * result + (operand2 != +0.0f ? Float.floatToIntBits(operand2) : 0);
        result = 31 * result + operator.hashCode();
        return result;
    }
}
