package com.example.banksystem;



public interface Query <I, O> {

	
	O execute(I input);
}

