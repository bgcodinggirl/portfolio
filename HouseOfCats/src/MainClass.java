
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numberOfLegs=0;
		do {
			System.out.println("Please, enter a valid even number between 0 and 50:");
			 try {
				 numberOfLegs = input.nextShort(); 
		     }
			 catch (InputMismatchException e) {
				 System.out.println("Not a valid number! Please, try again.");
				 input.nextLine();
		    }
		}while(numberOfLegs<0 || numberOfLegs>50 || numberOfLegs%2!=0 );
		System.out.println("The total number of legs in the house is: "+numberOfLegs);
		
		int countCats = 0;
		int countPeople = 0;
		
		for(int i=0;i<numberOfLegs;i+=4) {
			countCats++;
		}
		for(int i=0;i<numberOfLegs;i+=2) {
			countPeople++;
		}
		List<Integer> listOfNumbers=new ArrayList<>();
		listOfNumbers.add(countPeople);
		countPeople = 0;
		for(int i=1;i<=countCats; i++) {
			for(int j=0;j<numberOfLegs-(i*4);j+=2) {
				countPeople++;
			}
			listOfNumbers.add(countPeople);
			countPeople=0;
		}
		Integer[] array = listOfNumbers.toArray(new Integer[0]);
		Arrays.sort(array);
		System.out.print("houseOfCats(legs) = [");
		for(int n=0;n<array.length;n++) {
			if(n!=array.length-1 && array[n]!=0)System.out.print(array[n]+",");
			if(n==array.length-1) System.out.print(array[n]+"]");
		}
	
	}

}
