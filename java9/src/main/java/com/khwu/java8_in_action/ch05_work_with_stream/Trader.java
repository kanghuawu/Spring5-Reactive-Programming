package com.khwu.java8_in_action.ch05_work_with_stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Trader{
    private final String name;
    private final String city;
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}
