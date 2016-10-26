import java.util.Arrays;


/**	
*	Given sets, merge them so that the outcome is also a set.
*	
**/
public class Dad
{
	/* Testing the program */
	public static void main(String[] args)
	{
		int[][] array = {{1, 2, 3, 4},
						 {1, 6, 9, 10, 12, 15},
						 {3, 9, 20, 90},
						 {2, 7, 10, 11, 20, 92}};
		int[] sizes = new int[4];
		for(int i = 0; i < 4; i++)
		{
			sizes[i] = array[i].length;
		}
		int[] result = mergeSets(array.length, array, sizes);
		
		for(int i = 0; i < result.length; i++)
		{
			System.out.print(result[i] + " ");
		}
		System.out.println();
		System.out.println(isSet(result, result.length));
		
		//System.out.println(isSet(a, size));
		//System.out.println(isSet(a2, size));
	}
	
	// Assuming all are sets
	public static int[] mergeSets(int setCount, int[][] setArrays, int[] setSizes)
	{
		int[] arrayToBeReturned = null;
		
		int duplicates = 0;
		int resultingIndex = 0;
		int[] indexs;
		
		do
		{
			// For second run through only, creating the correct sized array
			if(resultingIndex > 0)
			{
				int holder = 0;
				for(int i = 0; i < setCount; i++)
				{
					holder += setSizes[i];
				}
				arrayToBeReturned = new int[holder - duplicates];
				resultingIndex = 0;
			}
			indexs = new int[setCount];
			
			int smallestIndex = -1;
			int counter = 0;
			do
			{
				smallestIndex = -1;
				// Finding smallest value out of all current indexes
				for(int i = 0; i < setCount; i++)
				{
					// Have to be within the bounds of the i-th array
					if(indexs[i] < setSizes[i])
					{
						if(smallestIndex == -1)
						{
							smallestIndex = i;
							indexs[smallestIndex]++;
						}
						// Counting duplicates
						else if(setArrays[i][indexs[i]] == setArrays[smallestIndex][indexs[smallestIndex]-1])
						{
							indexs[i]++;
							duplicates++;
						}
						else if(setArrays[i][indexs[i]] < setArrays[smallestIndex][indexs[smallestIndex]-1])
						{
							indexs[smallestIndex]--;
							smallestIndex = i;
							indexs[smallestIndex]++;
						}
					}
				}
				// Add the smallest into the new array
				if(smallestIndex != -1)
				{
					if(arrayToBeReturned != null)
					{
						arrayToBeReturned[resultingIndex] = setArrays[smallestIndex][indexs[smallestIndex]-1];
						resultingIndex++;
					}
				}
				else
				{
					resultingIndex++;
				}
				// Increase the index the smallest came from to avoid using it again.
			} while(smallestIndex != -1);
			// ^ Runs until none of the indexes are within the bound of the arrays
			
		} while(arrayToBeReturned == null);
		// ^ Runs twice, once to determine the number of duplicates, second to build and populate the array.
		
		return arrayToBeReturned;
	}
	
	// Assuming both are sets already
	public static int[] mergeSet(int[] array1, int size1, int[] array2, int size2)
	{
		int[] array3 = null;
		
		int duplicates = 0;
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		
		do
		{			
			if(index3 > 0) array3 = new int[size1+size2 - duplicates];
			
			index1 = 0;
			index2 = 0;
			index3 = 0;
			
			while(index1 < size1 && index2 < size2)
			{
				// Sorting part 1
				if(array1[index1] < array2[index2]) 
				{
					if(array3 != null) array3[index3] = array1[index1];
					index1++;
				}
				// Remove duplicates
				else if(array1[index1] == array2[index2]) 
				{
					if(array3 != null) array3[index3] = array1[index1];
					index1++;
					index2++;
					duplicates++;
				}
				// Sorting part 2
				else
				{
					if(array3 != null) array3[index3] = array2[index2];
					index2++;
				}
			
				index3++;
			}
		}
		while(array3 == null);
		
		while(index1 < size1)
		{
			array3[index3] = array2[index1];
			index1++;
			index3++;
		}
		
		while(index2 < size2)
		{
			array3[index3] = array2[index2];
			index2++;
			index3++;
		}
		
		System.out.println(Arrays.toString(array3));
		return array3;
	}
	
	/**
	*	Check to see if a given array is a set.
	*	A set is a sorted array of all positive integers with no duplicates.
	**/
	public static boolean isSet(int[] array, int size)
	{
		boolean r = true;
		
		if(array[0] < 0)
		{
			r = false;
		}
		else
		{
			for(int i = 0; i < size - 1; i++) // only go up to the second to last term
			{
				if(array[i+1] <= array[i])
				{
					r = false;
					break;
				}
			}
		}
		
		return r;
	}
}