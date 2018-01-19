package me.fonte.infinidim;

import java.util.HashMap;
import java.util.ArrayList;

public class BasicTests {
	
	
	public static void main(String[] args) {
		System.out.println("Testing...");
			
		//testBasic(5, 4, 1, 2);
		//testBasic(10, 4, 1, 2);
		//test3D(5, 10, 10, 1, 2, 2);
		//testBasicFlatIndex(20, 5, 44);
		//test3DFlatIndex(5, 7, 3, 26);
		//testMap(5, 5, 10);
		//testMatrixAddition();
		testBasicSlice();
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
		
		InfiniDim<Integer> testInf = new InfiniDim<>(2, null, dimLenHashMap);

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
		
		InfiniDim<Integer> testInf = new InfiniDim<>(3, null, dimLenHashMap);
		
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
	
	
	private static void testBasicFlatIndex(int xLen, int yLen, int flatIndex) {
		HashMap<Integer, Integer> dimLen = new HashMap<>();
		dimLen.put(0, xLen);
		dimLen.put(1, yLen);
		
		System.out.println("x Length: " + xLen);
		System.out.println("y Length: " + yLen);	
		
		
		InfiniDim<Integer> testInfDim = new InfiniDim<>(2, null, dimLen);
		HashMap<Integer, Integer> outNDim = new HashMap<>();
		
		outNDim = testInfDim.getNDim(flatIndex);
		
		for(int n = 0; n < testInfDim.getDimensions(); n++) { 
			System.out.println(n + ": " + outNDim.get(n));
			
		}
	}
	
	
	
	private static void test3DFlatIndex(int xLen, int yLen, int zLen, int flatIndex) {
		HashMap<Integer, Integer> dimLen = new HashMap<>();
		dimLen.put(0, xLen);
		dimLen.put(1, yLen);
		dimLen.put(2, zLen);
		
		System.out.println("x Length: " + xLen);
		System.out.println("y Length: " + yLen);	
		System.out.println("z Length: " + zLen);
		
		
		InfiniDim<Integer> testInfDim = new InfiniDim<>(3, null, dimLen);
		HashMap<Integer, Integer> outNDim = new HashMap<>();
		
		outNDim = testInfDim.getNDim(flatIndex);
		
		for(int n = 0; n < testInfDim.getDimensions(); n++) { 
			System.out.println(n + ": " + outNDim.get(n));
		}
	}
	
	private static void testMap(int xLen, int yLen, int amount) {
		HashMap<Integer, Integer> dimLen = new HashMap<>();
		dimLen.put(0, xLen);
		dimLen.put(1, yLen);
		
		InfiniDim<Integer> testInfDim = new InfiniDim<>(2, 1, dimLen);
		
		
		for(int n = 0; n <= 1; n++) {
			System.out.println("Output #" + n);
			for(int y = 0; y < yLen; y++) {
				for(int x = 0; x < xLen; x++) {
					HashMap<Integer, Integer> tempHashArray = new HashMap<>();
					tempHashArray.put(0, x);
					tempHashArray.put(1, y);
					int thisVar = testInfDim.getData(tempHashArray);
					System.out.print(thisVar + " ");
				}
				
				System.out.print("\n");
			}
			//testInfDim.addOrSubInteger(addAmount);
			testInfDim.multiplyScalarInt(amount);
		}
		
	}
	
	private static void testMatrixAddition() {
		HashMap<Integer, Integer> dimLen = new HashMap<>();
		int xLen = 5;
		int yLen = 4;
		dimLen.put(0, xLen);
		dimLen.put(1, yLen);
		
		InfiniDim<Integer> testInfDimLeft = new InfiniDim<>(2, 0, dimLen);
		InfiniDim<Integer> testInfDimRight = new InfiniDim<>(2, 2, dimLen);
		
		int totalVal = 0;
		for(int y = 0; y < yLen; y++) {
			for(int x = 0; x < xLen; x++) {
				HashMap<Integer, Integer> coords = new HashMap<>();
				coords.put(0, x);
				coords.put(1, y);
				testInfDimLeft.setData(coords,  totalVal);
				totalVal++;
			}
		}

		InfiniDim<Integer> testInfDimOut = testInfDimLeft.matrixAddition(testInfDimRight);
		
		System.out.println("Output: ");
		for(int y = 0; y < yLen; y++) {
			for(int x = 0; x < xLen; x++) {
				HashMap<Integer, Integer> coords = new HashMap<>();
				coords.put(0, x);
				coords.put(1, y);
				int thisVar = testInfDimOut.getData(coords);
				System.out.print(thisVar + " ");
			}
			
			System.out.print("\n");
		}
	}
	
	private static void testBasicSlice() {
		HashMap<Integer, Integer> dimLen = new HashMap<>();
		int xLen = 3;
		int yLen = 4;
		dimLen.put(0, xLen);
		dimLen.put(1, yLen);
		
		InfiniDim<Integer> testInfDim = new InfiniDim<>(2, 0, dimLen);
		
		//init the 2D array
		for(int y = 0; y < yLen; y++) {
			for(int x = 0; x < xLen; x++) {
				HashMap<Integer, Integer> coords = new HashMap<>();
				coords.put(0, x);
				coords.put(1, y);
				int val = 0;
				if(x == 2) {
					val = 100;
				}
				
				testInfDim.setData(coords, val);
			}
		}
		
		//test the slicing function
		ArrayList<Integer> testX = new ArrayList<>();
		ArrayList<Integer> testY = new ArrayList<>();
		HashMap<Integer, Integer> coords = new HashMap<>();
		coords.put(0, 2);
		coords.put(1, 0);
		
		testY = testInfDim.getSlice(1, coords, 0, 3);
		
		System.out.println("\nY slice, x index = 2");
		for(int i : testY) {
			System.out.print(i + " ");
		}
		
		System.out.println("\nY slice, x index = 1");
		
		coords.put(0, 1);
		testY = testInfDim.getSlice(1, coords, 0, 3);
		for(int i : testY) {
			System.out.print(i + " ");
		}
		
		System.out.println("\nX slice, y index = 0");
		
		coords.put(0, 0);
		coords.put(1, 0);
		testX = testInfDim.getSlice(0, coords, 0, 3);
		for(int i : testX) {
			System.out.print(i + " ");
		}
	}
	
}