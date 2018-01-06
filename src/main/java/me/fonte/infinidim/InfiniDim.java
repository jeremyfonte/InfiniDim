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

import java.util.LinkedList;
import java.util.HashMap;
import java.util.UUID;

/**
 * 
 * @author Jeremy Fonte
 *
 */
public class InfiniDim<T extends Number> {
	public final String id = UUID.randomUUID().toString(); //unique ID for this instance
	private LinkedList<T> data = new LinkedList<T>(); //1D list that holds all the multidimensional, flattened
	private int dimensions; //The number of dimensions (i.e., x, y, etc)
	private HashMap<Integer, Integer> dimLen = new HashMap<Integer, Integer>(); //the length of each dimension, by key, i.e., 0, 1, 2 = x, y, z
	
	
	//default constructor
	public InfiniDim() {}
	
	
	/**normal, smaller constructor - specify dimensions and set lengths of the dimensions to 0
	 * 
	 * @param numDimensions The number of dimensions
	 */
	public InfiniDim(int numDimensions) {
		this.dimensions = numDimensions;
		for(int i = 0; i < dimensions; i++) {
			this.dimLen.put(i, 0);
		}
	}
	
	/**normal, full constructor - specify dimensions and lengths of each dimension
	 * 
	 * @param numDimensions The number of dimensions
	 * @param dimLen A linked list storing the length of each dimension
	 */
	public InfiniDim(int numDimensions, HashMap<Integer, Integer> dimLen) {
		this.dimensions = numDimensions;
		this.dimLen = dimLen;
	}
	
	public int getFlatIndex(HashMap<Integer, Integer> coords) {
		return mapNDimToSingle(coords);
	}
	
	/*
	public int getNDimIndex() {
		
	}
	*/
	
	
	/**
	 * Map an N-Dimensional point from it's coordinates to their mapped position on a 1D "line" or array
	 * 
	 * @param coords A map of dimension keys to the value in that dimension specified by the input
	 * @return The index of the mapped coordinates on a 1D array
	 */
	private int mapNDimToSingle(HashMap<Integer, Integer> coords) {
		int coordLen = coords.size();
		int errReturn = -1;
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
	
}
