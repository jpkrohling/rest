package com.example.demoopentracing.rest;

import java.io.Serializable;

public class TestDTO implements Serializable {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
