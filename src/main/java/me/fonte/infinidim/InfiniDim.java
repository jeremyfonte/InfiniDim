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
	private LinkedList<Integer> listLen = new LinkedList<Integer>(); //the length of each dimension, from inner to outer - i.e., x to y to z
	
	
	//default constructor
	public InfiniDim() {}
}
