package com.otakuma.products.dto.testy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class BOATest {

	@Test
	void test() {
		

		int[] array1 = new int[] {1,2,3,5,8,9,10,99,110};
		int[] array2 = new int[] {10,500,600,800,900};
		
		outputSearch(array1, 1);
		outputSearch(array2, 1);

		outputSearch(array1, 10);
		outputSearch(array2, 10);
		
		outputSearch(array1, 110);
		outputSearch(array2, 110);
	}



	private void outputSearch(int[] array2, int search) {
		System.out.println("searching " + search + " in " + Arrays.toString(array2));
		boolean result2  = binarySearch(array2, search);
		if(result2)System.out.println("found in : " + Arrays.toString(array2) );
		System.out.println();
	}

	
	
	public boolean binarySearch(int[] ints, int k) {
		 
        if(ints == null || ints.length == 0 || ints.length > 1000000)
            return false;
        int steps = 0;
        int first = 0;
        int upto = ints.length;
        
        int mid ;  // Compute mid point.
        while(first < upto) {
        	steps++;
        	mid = (first + upto) / 2;
        	if(ints[mid] == k)
        	{	
        		System.err.println(steps);
        		return true;
        	}
        	if(ints[mid] > k)
        		upto = mid;
        	else 
        		first = mid;
        }
		
        System.err.println(steps);
		return false;
	}
}
