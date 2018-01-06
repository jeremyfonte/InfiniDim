package me.fonte.infinidim;

import java.util.HashMap;

public class BasicTests {
	
	
	public static void main(String[] args) {
		System.out.println("Testing...");
			
		testBasic();
	}
	
	private static void testBasic() {
		//---
		//declare required vars, init them
		//---
		HashMap<Integer, Integer> dimLenHashMap = new HashMap<>();
		int xLen = 5;
		int yLen = 4;
		dimLenHashMap.put(0, xLen);
		dimLenHashMap.put(1, yLen);
		
		System.out.println("x Length: " + xLen);
		System.out.println("y Length: " + yLen);		
		
		InfiniDim<Integer> testInf = new InfiniDim<>(2, dimLenHashMap);

		//set up the coords for the new value		
		HashMap<Integer, Integer> coordHashMap = new HashMap<>();
		int xCoord = 1;
		int yCoord = 2;
		
		coordHashMap.put(0, xCoord); //x coord = 3rd (arrays start at 0)
		coordHashMap.put(1, yCoord); //y coord = 2nd
		
		System.out.println("x Coordinate: " + xCoord);
		System.out.println("y Coordinate: " + yCoord);
		
		int result = testInf.getFlatIndex(coordHashMap);
		
		
		System.out.println("Flattened Index Result: " + result);
		
	}
}
