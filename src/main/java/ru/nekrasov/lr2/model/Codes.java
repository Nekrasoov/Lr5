package ru.nekrasov.lr2.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Codes {
        SUCCESS("succes"),
        FAILED("failed");

        private final String name;

        Codes(String name){
            this.name = name;
        }

        @JsonValue
        public String getName(){
            return name;
        }

        @Override
        public String toString(){
            return name;
        }
}
