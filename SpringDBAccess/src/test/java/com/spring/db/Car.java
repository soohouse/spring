package com.spring.db;

public interface Car {

	public void run(); //추상 메서드
}

class Sonata implements Car {
	@Override
	public void run() {
		System.out.println("소나타가 달립니다~");
		
	}
}
