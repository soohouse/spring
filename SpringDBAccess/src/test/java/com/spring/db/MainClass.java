package com.spring.db;

interface Calculator {
	int add(int n1, int n2);
}

public class MainClass {

	public static void main(String[] args) {
		
		Car s1 = new Sonata();
		s1.run();
		
		Car s2 = new Sonata();
		s2.run();
		
		Car tesla = new Car() {
			@Override
			public void run() {
				System.out.println("테슬라가 슝슝 달립니다~");
			}
		};
		
		tesla.run();
		
		new Car() {
			@Override
			public void run() {
				System.out.println("포르쉐가 쌩쌩 달립니다~");	
			}
		}.run();
		
		Car ferrari = () -> {
			System.out.println("페라리가 씽씽 달립니다~");
		};
		
		ferrari.run();
		
		System.out.println("---------------------------------------");
		
		//계산기 인터페이스와 람다식
		Calculator sharp = new Calculator() {	
			@Override
			public int add(int n1, int n2) {
				System.out.println("샤프 계산기의 덧셈!");
				return n1 + n2;
			}
		};
		
		System.out.println(sharp.add(10, 15));
		
		Calculator casio = (x, y) -> {
			System.out.println("카시오 계산기의 덧셈!");
			return x + y;
		};
		
		System.out.println(casio.add(100, 200));
		
		//만약 오버라이딩하는 추상메서드에 작성할 코드가 단 한 줄이라면
		//return과 괄호가 생략이 가능합니다.
		
		Calculator xiaomi = (x, y) -> x + y;
		System.out.println(xiaomi.add(30, 50));
		
		

	}

}














