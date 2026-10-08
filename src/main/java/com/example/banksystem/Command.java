package com.example.banksystem;



public interface Command <I, O> {

	
	O execute(I input);
}