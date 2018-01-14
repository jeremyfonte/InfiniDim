/*
Copyright 2018 Jeremy Fonte

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/



package me.fonte.infinidim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;


/**
 * 
 * @author Jeremy Fonte
 *
 */
public class InfiniDim<T extends Number> {
	public final String id = UUID.randomUUID().toString(); //unique ID for this instance
	private ArrayList<T> data = new ArrayList<T>(); //1D list that holds all the multidimensional data, flattened
	private int dimensions; //The number of dimensions (i.e., x, y, etc)
	private HashMap<Integer, Integer> dimLen = new HashMap<Integer, Integer>(); //the length of each dimension, by key, i.e., 0, 1, 2 = x, y, z
	
	public int getDimensions() {
		return this.dimensions;
	}
	
	//default constructor - would create invalid object without number of dimensions specified
	//public InfiniDim() {}
	
	public void setDimLen(HashMap<Integer, Integer> newDimLen) {
		this.dimLen = newDimLen;
	}
	
	/**normal, smaller constructor - specify dimensions and set lengths of the dimensions to 0
	 * 
	 * @param numDimensions The number of dimensions
	 */
	public InfiniDim(int numDimensions) {
		this.dimensions = numDimensions;
		for(int i = 0; i < dimensions; i++) {
			this.dimLen.put(i, 0);
		}
		initDataArray(null);
	}
	
	public InfiniDim(int numDimensions, T defaultValue) {
		this.dimensions = numDimensions;
		for(int i = 0; i < dimensions; i++) {
			this.dimLen.put(i, 0);
		}
		initDataArray(defaultValue);
	}
	
	
	/**normal, full constructor - specify dimensions and lengths of each dimension
	 * 
	 * @param numDimensions The number of dimensions
	 * @param dimLen A linked list storing the length of each dimension
	 */
	public InfiniDim(int numDimensions, T defaultValue, HashMap<Integer, Integer> dimLen) {
		this.dimensions = numDimensions;
		this.dimLen = dimLen;
		this.initDataArray(defaultValue);
	}
	
	/**
	 * Figure out the length of the 1D array, init it to the specified default value
	 * 
	 * @param defaultValue The initial value to set by default throughout the entire N-dimensional array
	 */
	private void initDataArray(T defaultValue) {
		//if somehow the dimensions are zero, abort, throw error
		if(dimensions < 1) {
			//throw error
			return;
		}
		else {
			//figure out the length of the 1D array that holds the N-dim array's values
			int dimLenProduct = this.dimLen.get(0);
			
			for(int i = 1; i < this.dimensions; i++) {
				dimLenProduct *= this.dimLen.get(i);
			}
			//declare and init capacity of the data ArrayList
			this.data = new ArrayList<T>(dimLenProduct);
			
			for(int n = 0; n < dimLenProduct; n++) {
				this.data.add(n, defaultValue);
			}	
		}
	}
	
	/**
	 * Accessor function for converting an N-dim value to a 1D value
	 * 
	 * @param coords The N-dimensional array coordinates for a point to convert to 1D flat array
	 * @return The index of the flat array corresponding to the N-Dim value provided
	 */
	public int getFlatIndex(HashMap<Integer, Integer> coords) {
		return mapNDimToSingle(coords);
	}
	
	
	/**
	 * Map an N-Dimensional point from it's coordinates to their mapped position on a 1D "line" or array
	 * 
	 * @param coords A map of dimension keys to the value in that dimension specified by the input
	 * @return The index of the mapped coordinates on a 1D array
	 */
	private int mapNDimToSingle(HashMap<Integer, Integer> coords) {
		int coordLen = coords.size();
		final int errReturn = -1; //constant val for error return values (should throw errors too)
		int returnVal = 0;
		
		//If the input coords have more dimensions that the enclosing object, or there are no dimensions, throw an error and return to caller
		if(coordLen > this.dimensions || coordLen == 0) {
			//throw error and return
			return errReturn;
		}		
		//This else wraps code for valid # of dimensions
		else {	
			
			//check if the available keys match this class' patterns
			//then calc the return value
			for(int i = 0; i < coordLen; i++) {
				if(coords.get(i) == null) {
					//throw error, missing or incorrect dimension key (should be 0, 1, 2, 3, etc, not 0, 5, 11, 4, etc.)
					return errReturn;
				}
				else if(coords.get(i) < 0) {
					//throw error, negative indeces not allowed for location on a dimension					
					return errReturn;
				}
				else if(coords.get(i) > this.dimLen.get(i) ) {
					//throw error, specified coord for specified dimension is larger than max value for that dimension
					return errReturn;
				}
				//else, valid input - increase the value of index in 1D array
				else {
					if(i == 0) {
						//the value of the x coordinate is just the index, no multiplication of dimension lengths
						returnVal += coords.get(0);
					}
					//calculate the value of the current dimension
					else {
						int calcVal = coords.get(i);
						
						for(int n = i - 1; n >= 0; n--) {
							calcVal *= this.dimLen.get(n);
						}
						
						returnVal += calcVal;
					}
				}
			}						
			
			return returnVal;
		}
	}
	
	
	public HashMap<Integer, Integer> getNDim(int flatIndex) {
		return mapSingleToNDim(flatIndex);
	}
	
	/**
	 * Map an index (from a 1D array) to the corresponding N-dimensional array it represents
	 * 
	 * @param flatIndex The integer index to convert to an N-dim array
	 * @return The corresponding N-dimensional array
	 */
	private HashMap<Integer, Integer> mapSingleToNDim(int flatIndex) {
		//declare and init the ouput hash map
		HashMap<Integer, Integer> outputNDim = new HashMap<>();
		
		//flatIndex stays the same and workingAmount holds the value as it changes
		int workingAmount = flatIndex;
		
		//loop through the dimensions, separating the flat index into the various dimensions
		for(int i = this.dimensions - 1; i >= 0; i--) {
			//init the current dimension
			outputNDim.put(i, 0);
			
			int dimLenProduct = 1;
			for(int n = i - 1; n >= 0; n--) {
				dimLenProduct *= this.dimLen.get(n);
			}
			
			
			while(workingAmount >= dimLenProduct) {
				workingAmount -= dimLenProduct;
				outputNDim.put(i, outputNDim.get(i) + 1);
			}
		}
		
		return outputNDim;
	}
	
	/**
	 * Apply a lambda function to each element in the InfiniDim's data array
	 * 
	 * @param func A lambda function to apply to each data element in the N-dim array
	 */
	public void map(Function<T, T> func) {
		ArrayList<T> outputData = new ArrayList<T>();
		
		int n = 0;
		for(T elem : this.data) {
			outputData.add(n, func.apply(elem));
			n++;
		}
		
		this.data = outputData;
	}
	
	
	
	/**
	 * Add or subtract a constant amount from each data item
	 * 
	 * @param addOrSubAmount The positive or negative amount to add
	 */
	public void addOrSubInteger(T addOrSubAmount) {
		this.map((n)-> {
			return (T)(Integer)((Integer)n + (Integer)addOrSubAmount);
		});
	}
	
	
}
