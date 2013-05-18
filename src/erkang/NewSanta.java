package erkang;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NewSanta {
	
	// The main method is a test
	public static void main(String[] args) {
		final String[] array = new String[]{"a","b","c","d","e","f"};
		
		// Generate a new array and display it.
		String[] testArray = generateAssignments(array);
		for(int a=0; a<6; a++){
			System.out.println(testArray[a]);
	
		}
		
		// Generate new array 1000 times, then test if there is violation of the rules. If there is any violation, it will print "error!"
		for(int i=0; i<10000; i++) {
			String[] newArray = generateAssignments(array);
			for(int a=0; a<6; a++){
				if(newArray[a]==array[a]){
					System.out.println("error!");
					break;
				}
			}
		}

	}
    
	public static String[] generateAssignments(final String[] participants) {
		int length = participants.length;
		Set<Integer> uncopiedIndice = new HashSet<Integer>();
		for(int i=0; i<length; i++){
			uncopiedIndice.add(i);
		}
		// uncopyedIndice stores the indice of the the index of the elements haven't bean copied from the passed-in array
		String[] newArray = new String[length];
		// A new array for return
		boolean flag = false; // A flag to indicate whether you should add the index back before the next loop
		Random generator = new Random();
		for(int i=0; i<length; i++){
			int size = uncopiedIndice.size();
			if(size==2 && uncopiedIndice.contains(length-1)){
					newArray[length-2] = participants[length-1];
					uncopiedIndice.remove(length-1);
					size--;
			}
			// In the situation of last two elements left in the uncopiedIndex, if the index of (length-1) exist, the second last element of the new array
			// should be copied from the last element of the passed-in array. otherwise the last element of the new array will remain the same. That is unacceptable
			else{
				if(uncopiedIndice.contains(i)){
					flag = true;
					uncopiedIndice.remove(i); // This index should be temporarily removed from the set and added back later
					size--;
				}
				int target = generator.nextInt(size);
				int k = 0;
				for(int a: uncopiedIndice){
					if(k==target) {
						newArray[i]= participants[a];
						uncopiedIndice.remove(a);
						break;
					}
					k++;
				}
				if(flag) {
					uncopiedIndice.add(i);
				    flag = false;
				}
			}
		}
		return newArray;	
	}
}
