package com.example.banksystem;

public interface ThreeParamCommand<I, O> {
	O execute(Integer input, Integer input2, I input3);
}
