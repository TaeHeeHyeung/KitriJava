package com.kitri.exceiption.runtime;

public class IndexBoundsTest {

	public static void main(String[] args) {
		String s[] = {"1","2","3","4"};
		int len = s.length;
//		for (int i = 0; i <= 4; i++) {
//			System.out.println(s[i]);
//		}
		for (int i = 0; i < len; i++) {
			System.out.println(s[i]);
		}
		
		String str = "hello";
//		for (int i = 0; i <= 5; i++) {
//			System.out.println(str.charAt(i));
//		}
		len = str.length();
		for (int i = 0; i < len; i++) {
			System.out.println(str.charAt(i));
		}
		
	}//end main

}//IndexBoundsTest
