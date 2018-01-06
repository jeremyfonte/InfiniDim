package me.fonte.infinidim;

import java.util.HashMap;

public class BasicTests {
	
	
	public static void main(String[] args) {
		System.out.println("Testing...");
			
		//testBasic(5, 4, 1, 2);
		//testBasic(10, 4, 1, 2);
		test3D(5, 10, 10, 1, 2, 2);
	}
	
	private static void testBasic(int xLen, int yLen, int xCoord, int yCoord) {
		//---
		//declare required vars, init them
		//---
		HashMap<Integer, Integer> dimLenHashMap = new HashMap<>();
		
		dimLenHashMap.put(0, xLen);
		dimLenHashMap.put(1, yLen);
		
		System.out.println("x Length: " + xLen);
		System.out.println("y Length: " + yLen);		
		
		InfiniDim<Integer> testInf = new InfiniDim<>(2, dimLenHashMap);

		//set up the coords for the new value		
		HashMap<Integer, Integer> coordHashMap = new HashMap<>();
		
		coordHashMap.put(0, xCoord); //x coord
		coordHashMap.put(1, yCoord); //y coord
		
		System.out.println("x Coordinate: " + xCoord);
		System.out.println("y Coordinate: " + yCoord);
		
		int result = testInf.getFlatIndex(coordHashMap);
		
		
		System.out.println("Flattened Index Result: " + result);
		
	}
	
	private static void test3D(int xLen, int yLen, int zLen, int xCoord, int yCoord, int zCoord) {
		//---
		//declare required vars, init them
		//---
		HashMap<Integer, Integer> dimLenHashMap = new HashMap<>();
		
		dimLenHashMap.put(0, xLen);
		dimLenHashMap.put(1, yLen);
		dimLenHashMap.put(2, zLen);
		
		System.out.println("x Length: " + xLen);
		System.out.println("y Length: " + yLen);		
		System.out.println("z Length: " + zLen);
		
		InfiniDim<Integer> testInf = new InfiniDim<>(3, dimLenHashMap);
		
		//set up the coords for the new value		
		HashMap<Integer, Integer> coordHashMap = new HashMap<>();
				
		coordHashMap.put(0, xCoord); //x coord
		coordHashMap.put(1, yCoord); //y coord
		coordHashMap.put(2, zCoord); //z coord
				
		System.out.println("x Coordinate: " + xCoord);
		System.out.println("y Coordinate: " + yCoord);
		System.out.println("y Coordinate: " + zCoord);
				
		int result = testInf.getFlatIndex(coordHashMap);
				
				
		System.out.println("Flattened Index Result: " + result);
	}
}