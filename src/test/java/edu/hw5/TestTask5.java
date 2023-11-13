package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Тесты для валидации российских номерных знаков")
public class TestTask5 {

    @Test
    @DisplayName("Тест: правильный номерной знак А123ВЕ777")
    public void testValidLicensePlate1() {
        assertTrue(Task5.validateLicensePlate("А123ВЕ777"));
    }

    @Test
    @DisplayName("Тест: правильный номерной знак О777ОО177")
    public void testValidLicensePlate2() {
        assertTrue(Task5.validateLicensePlate("О777ОО177"));
    }

    @Test
    @DisplayName("Тест: неправильный номерной знак 123АВЕ777")
    public void testInvalidLicensePlate1() {
        assertFalse(Task5.validateLicensePlate("123АВЕ777"));
    }

    @Test
    @DisplayName("Тест: неправильный номерной знак А123ВГ77")
    public void testInvalidLicensePlate2() {
        assertFalse(Task5.validateLicensePlate("А123ВГ77"));
    }

    @Test
    @DisplayName("Тест: неправильный номерной знак А123ВЕ7777")
    public void testInvalidLicensePlate3() {
        assertFalse(Task5.validateLicensePlate("А123ВЕ7777"));
    }
}
