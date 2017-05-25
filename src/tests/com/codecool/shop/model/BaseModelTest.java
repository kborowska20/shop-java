package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by morthan on 25.05.17.
 */
class BaseModelTest {
    @Test
    void testToString() {
        BaseModel baseModel = new BaseModel("Test", "Testcription");
        assertEquals("id:0,name:Test,description:Testcription,", baseModel.toString());
    }

}