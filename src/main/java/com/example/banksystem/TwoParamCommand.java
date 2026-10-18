package com.example.banksystem;

public interface TwoParamCommand <I, O> {

	
	O execute(Integer input, I input2);
}
