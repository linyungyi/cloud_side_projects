package com.albert.dto;

public class SoccerCombine {
	
	public SoccerCombine(SoccerDTO a,SoccerDTO b)
	{
		A=a;
		B=b;
	}
	
	SoccerDTO A;
	SoccerDTO B;
	
	public SoccerDTO getA() {
		return A;
	}
	public void setA(SoccerDTO a) {
		A = a;
	}
	public SoccerDTO getB() {
		return B;
	}
	public void setB(SoccerDTO b) {
		B = b;
	}
	
	
}
