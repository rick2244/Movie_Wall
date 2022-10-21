package CS245_Project01;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Collections;


public class Movie_Wall {
	
	/* This is my own custom method for binarySearch of an Arraylist of string. It returns the index
	 * of the string if it's there, and if not it returns the left index times -1*/
	public static int binarySearchString(ArrayList<String> nums, int left, int right, String target) {
		if(left > right) {
			return left*-1;
		}
		int mid = (left + right) / 2;
		if(target.equals(nums.get(mid))) {
			return mid;
		}
		else if (target.compareTo(nums.get(mid)) < 0){
			return binarySearchString(nums, left, mid-1, target);
		}
		else {
			return binarySearchString(nums, mid + 1, right, target);
		}
	}
	
	/*I reused the quick sort method from lab6*/
	public static <T extends Comparable<T>> void sort(T[] unsorted) {
        quickSort(unsorted, 0, unsorted.length - 1);
    }
    public static <T extends Comparable<T>> void quickSort(T[] unsorted, int left, int right) {
        //TODO
    	// Complete the quicksort logic here
        
    	// pivot assignment should call the randomPartition helper function
        int pivot = randomPartition(unsorted, left, right);
        if(left < pivot -1) {
        	quickSort(unsorted, left, pivot - 1);
        }
        if(pivot < right) {
        	quickSort(unsorted, pivot, right);
        }
    }

    public static <T extends Comparable<T>> int randomPartition(T[] unsorted, int left, int right) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(unsorted, randomIndex, right);
        return partition(unsorted, left, right);
    }

    public static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <T extends Comparable<T>> int partition(T[] arr, int left, int right) {
        T pivot = arr[left + right >> 1]; // -> the element in the middle, not the pivot index

        //When comparing generic data from the array, use the compareTo() function instead of equality operators
        //compareTo return a boolean value, and boolean can also represent by -1, 0, 1
        while (left <= right) {
            while(arr[left].compareTo(pivot) < 0){
            	left++;//1. keep checking left element in the array if is less than pivot, update the left boundary
            }

            while(pivot.compareTo(arr[right]) < 0){
            	right--;//2. keep checking pivot if is less than the right element in the array, update the right boundary
            }

            if(left <= right){
            	swap(arr, left, right);
            	left++;
            	right--;//3. check left with right -> what should be the if-condition?
            }
            //then swap elements and update both boundary

        }
        return left;
    }

	/* This method is one I created on my own to calculate the similarity between words. I created this method to return a score of similarity
	 * between to words based on the amount of character matches at specific indexes. The score is determined by the cnt of matches divided by the actor
	 * name in the list of names I created. I have an else if conditional to handle the right for loop for when the length of both words don't match.
	 * */
	public static double isSimilar(String actor, String input) {
		int cnt = 0;
		if(input.length() >= actor.length()) {
			for(int i = 0; i < actor.length(); i++) {
				if(actor.charAt(i) == input.charAt(i)) {
					cnt++;
				}
			}
		}
		else if(input.length() < actor.length()) {
			for(int i = 0; i < input.length(); i++) {
				if(actor.charAt(i) == input.charAt(i)) {
					cnt++;
				}
			}
		}
		return (double) cnt/actor.length();
	}
	/* I created this method to parse through the data and return an ArrayList filled with the possible actor names the user can look up from the data
	 * file */
	public static ArrayList<String> CSVReader_Names(String file2) {
		ArrayList<String> names_list = new ArrayList<>();
	    try {
	      File file = new File(file2);
	      FileReader fr2 = new FileReader(file);
	      BufferedReader br2 = new BufferedReader(fr2);
	      String line = "";
	      Set<String> name = new HashSet<String>();
	      int cnt = 0;
	      while ((line = br2.readLine()) != null) {
	    	  //I introducted the variable cnt so that I can skip the first row of data
	    	  if(cnt >= 1){
	    		  //Splits the rows into a string that only contains infor on cast
    			  String cast = line.substring(line.indexOf("["), line.indexOf("]"));
    			  //Splits new string further based on ,
	    		  String[] castarr = cast.split(",");
	    		  //Loops through the array and if any of the elements contains "name" the name of that actor is added to my hashset
	    		  //I choose a hashset data type to add to so that I would not get repeats to show up twice
	    		  for(String a: castarr) {
	    			  if(a.contains("name")) {
	    				  int first = a.indexOf(':');
	    				  name.add(a.substring(first+4, a.length()-2));
	    			  }
	    		  }
    		  }
	    	  cnt++;
	      }
	      //After filling my hashset with the list of possible actor names I created a for loop to add those elements to the ArrayList I am returning
	      String[] arr = new String[name.size()];
	      int i = 0;
	      //this loop allows me to add all the elements from my set to my String array
	      for(String e: name) {
	    	  arr[i] = e; 
	    	  i++;
	      }
	      //used my sort method to sort my String array
	      sort(arr);
	      //After sorting arr I looped through it and added each element to my String Arraylist
	      for(int b = 0; b < arr.length; b++) {
	    	  names_list.add(arr[b]);
	      }
	      br2.close();
	   }
	    catch(IOException ioe) {
	      ioe.printStackTrace();
	    }
	    return names_list;
	 }

	//This method was created to parse through the data again, but to find info on the movie, actor, and role into an ArrayList
	public static ArrayList<String> CSVReader(String file2) { //make sure to change return type back to ArrayList
		ArrayList<String> movie_info = new ArrayList<>();
	    try {
	      File file = new File(file2);
	      FileReader fr = new FileReader(file);
	      BufferedReader br = new BufferedReader(fr);
	      String line = "";
	      int cnt = 0;
	      while ((line = br.readLine()) != null) {
	    	  String res = ""; //is used to create a easy string that contains info 
	    	  if(cnt >= 1){
	    		  //Movie is found by splitting each row by ",", when this is done I know for sure the movie is at index 1 of the new array of each row
	    		  String[] movie_find = line.split(",");
	    		  String movie = movie_find[1];
	    		  //repeat of process is previous method expect I split further and also find the role of the actor in that movie
	    		  String cast = line.substring(line.indexOf("["), line.indexOf("]")+1);
	    		  //an array to further split the cast JSON file into individual pieces for each actor and their role
	    		  String[] role_find = cast.split("}");
	    		  //loops through the possible cast
	    		  for(int i = 0; i < role_find.length;i++) {
	    			  res+=movie + ","; //adds movie to res
	    			  String[] role_find_split = role_find[i].split(","); //further split of role_find
	    			  //Loops through new array to find elements that contains "character" or "name" because the data I needed is associated with 
	    			  //elements that contain those phrases
	    			  for(String b: role_find_split) {
	    				  if(b.contains("character")) {
	    					  String[] split3 = b.split(":");
	    					  String role = split3[1].substring(3, split3[1].length()-2);
	    					  res+=role + ",";
	    				  }
	    				  if(b.contains("name")) {
	    					  String[] split3 = b.split(":");
	    					  String actor = split3[1].substring(3, split3[1].length()-2);
	    					  res+=actor;
	    				  }
	    			  }
	    			  //final res after each is added to Arraylist holding info
	    			  movie_info.add(res);
	    			  //res is reset for next iteration
	    			  res = "";
	    		  }
	    	  }
	     
	      cnt++;
	      
	      }
	      br.close();
	      
	   }
	   catch(IOException ioe) {
	      ioe.printStackTrace();
	   }
	   return movie_info;
	 }
         
	public static void main(String[] args) {
		String path = args[0];
		System.out.println("Welcome to the Movie Wall!");
		boolean Truth = true;
		int cnt = 0;
		//holds the Arraylist of movie_info from CSVReader
		ArrayList<String> list = CSVReader(path);
		//holds the Arraylist of actor names in data from CSVReader_Names method
		ArrayList<String> names_list = CSVReader_Names(path);
		//I use a boolean while loop to run my program until the user types in "EXIT"
		while(Truth) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of an actor (or \"EXIT\" to quit): ");
		String actor = sc.nextLine();
		String[] actor2 = actor.split(" ");
		for(int i = 0; i < actor2.length; i++) {
			actor2[i] = actor2[i].substring(0,1).toUpperCase() + actor2[i].substring(1, actor2[i].length());
		}
		actor = "";
		for(int b = 0; b < actor2.length; b++) {
			if(b != actor2.length -1) {
				actor+= actor2[b] + " ";
			}
			else {
				actor+=actor2[b];
			}
		}
		if(actor.equals("EXIT")) {
			Truth = false;
			System.out.println("Thanks for using the Movie Wall!");
			break;
		}
		//I get an index from my binarySearchString
		int index = binarySearchString(names_list, 0, names_list.size(), actor);
		//Conditionals of actions if the element exists in the list
		if(index >= 0) {
			System.out.println("Actor: " + actor);
			for(int i = 0; i < list.size(); i++) {
				String line = list.get(i);
				if(line.contains(actor)) {
					String[] info = line.split(",");
					String movie = info[0];
					String role = info[1];
					System.out.println("* Movie: " + movie + " as " + role);
				}
			}
			System.out.println();
		}
		//Conditional if the element doesn't exist in the list
		else if(index < 0){
			int start = index * -1;
			int index_name = 0;
			double max = 0;
			int end  = 0;
			//This if statment is used to make that if the index returned + 20 is over the Arraylist size then it fixes it so that an index out of bounds error doesn't occur
			if(start + 20 >= names_list.size()) {
				for(int a = start; a < names_list.size(); a++) {
					end++;
				}
				for(int i = start - 20; i < start + end; i++) {
					double score = isSimilar(actor, names_list.get(i));
					if(score > max) {
						max = score;
						index_name = i;
					}
				}	
			}
			//This if statment is used to make that if the index returned - 20 is under the 0 then it fixes it so that an index out of bounds error doesn't occur
			else if(start - 20 < 0) {
				int new_start = 0;
				for(int a = start; a >= 20; a--) {
					new_start++;
				}
				start-=new_start;
				for(int i = start; i < start + 20; i++) {
					double score = isSimilar(actor, names_list.get(i));
					if(score > max) {
						max = score;
						index_name = i;
					}
				}
			}
			//if none of those potential errors occurs then this search range is performed
			else {
				for(int i = start - 20; i < start + 20; i++) {
					double score = isSimilar(actor, names_list.get(i));
					if(score > max) {
						max = score;
						index_name = i;
					}
				}
			}
			//The new index is used to get the suggestion name. The following code allows the user the option the view the information of the suggested actor
			String new_name = names_list.get(index_name);
			System.out.print("No such actor. Did you mean \"" + new_name + "\" (Y/N): ");
			String answer = sc.next();
			if(answer.equals("Y")) {
				System.out.println("Actor: " + new_name);
				for(int i = 0; i < list.size(); i++) {
					String line = list.get(i);
					if(line.contains(new_name)) {
						String[] info = line.split(",");
						String movie = info[0];
						String role = info[1];
						System.out.println("* Movie: " + movie + " as " + role);
					}
				}
			}else {
					continue;
			}
			System.out.println();
		}
		}
	}
}
