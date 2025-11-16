
public class BFSwRE {

	public static void main(String[] args) 
	{
		int[] arr = {
				2, 2, 2, 3, 3, 3, 3, 4, 5,
				5, 5, 5, 5, 5, 6, 6, 6, 7,
				7, 7, 7, 7, 8, 8, 9, 9, 9,
				9, 9, 9, 10, 10, 10, 10
			};
		
		int target = 5;
		int count = countOccurrences(arr,target);
		System.out.println("Array: ");
		
		//delete this when I talk to the professor
		System.out.println("Total number of " + target + "s in array is " + count);

	}
	
	//Performs a binary search for your target in an array
	//Note this does not find the first value of target if there is more than one.
	public static int binarySearch(int[] arr, int target)
	{	
		if(arr.length <= 0)//checks to see if array is an empty list or not
		{
			throw new IllegalArgumentException("Array must have a length of 1 or more");
		}
		
		//create a low and high value
		int low = 0;
		int high = arr.length-1;
		
		while(low <= high)//stop loop if low is more than high
		{
			int mid = low + (high - low) / 2;//find the middle point of array
			
			if(arr[mid]==target)//check to see if middle value is target
			{
				return mid;
			}
			else if(arr[mid]>target)//if target is less than mid value make high mid -1
			{
				high = mid -1;
			}
			else//if target is more than mid value make low mid +1
			{
				low = mid + 1;
			}
		}
		return -1;//return -1 if target is not in array
	}
	
	//get the most left index of the number
	public static int expandLeft(int[] arr, int index, int target)
	{	
		
		int leftIndex = index-1;//create a variable for index to the left
		
		if(index == 0) //checks to see if index is first value in array
		{
			return 0;
		}
		else if(arr[leftIndex] != target)//checks to see if left index is not equal to target
		{
			return index;
		}
		else if(arr[leftIndex] == target)//checks to see if right index is equal to target
		{
			return expandLeft(arr,leftIndex,target);//if so thats where the recursion comes in
		}
		return 0;
	}
	
	//get the most right index of the number
	public static int expandRight(int[] arr, int index, int target)
	{
		int rightIndex = index+1;//create variable for index to the right
		
		if(index == arr.length-1)//checks to see if index is at the end of array
		{
			return 0;
		}
		else if(arr[rightIndex] != target)//checks to see if right index is not equal to target
		{
			return index;
		}
		else if(arr[rightIndex] == target)//checks if right index is equal to target
		{
			return expandRight(arr,rightIndex,target);//recursion if it does happen
		}
		return 0;
	}
	
	//this method calls all other methods so we can then return the total number
	//of times the target is in the array
	public static int countOccurrences(int[] arr, int target)  
	{
		//create local variables
		int index = (binarySearch(arr, target));
		int leftMostIndex;
		int leftCount;
		int rightMostIndex;
		int rightCount;
		
		//crate a try catch block for index about of bounds exception
		try
			{
			//set variables to see how many time the number occurs to the left
			leftMostIndex = expandLeft(arr, index, target);
			leftCount = index - leftMostIndex;
			
			//set variables to see how many times the number occurs to the right
			rightMostIndex = expandRight(arr, leftMostIndex, target);
			//this prevents a negative number from happening
			if(rightMostIndex == 0)
			{
				rightCount = 0;
			}
			else 
			{
				rightCount = rightMostIndex - index;
			}
			
			
			//just for user to see numbers
			System.out.println("Index " +index);
			System.out.println("\nCount to left " + leftMostIndex);
			System.out.println("Left count " + leftCount);
			System.out.println("\nCount to right " + rightMostIndex);
			System.out.println("right count " + rightCount);
			
			//adds up the left, right and 1 for the base index
			return rightCount + leftCount + 1;
			}
		
		//prints out error if number is not in array
		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			System.err.println("-1 is not in the array" + aioobe.toString());
		}
		return -1;
	}
}
