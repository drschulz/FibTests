import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class FibComparison {
	
	public static HashMap<Integer, Integer> cache;
	public static ArrayList<Integer> list;
	
	public static void main(String[] args) {
		/*Change this value if needed*/
		int ind = 41;
		
		int result;
		long start, finish, totTime;
		int limit = 43;
		
		/*Initialize cache and list with 0 and 1*/
		cache = new HashMap<Integer, Integer>();
		cache.put(0, 0);
		cache.put(1, 1);
		list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Which number in the Fib sequence would you like? ");
		
		int index = sc.nextInt();
		while (index < 0 || index > limit) {
			System.out.print("Enter a positive index less than " + limit + ": ");
			index = sc.nextInt();
		}
		
		sc.close();
		
		start = System.nanoTime();
		result = fibCache(index);
		finish = System.nanoTime();
		totTime = finish - start;
		System.out.print("With Cache: Result: " + result + ", time to run: " + totTime + "ns\n");
		
		start = System.nanoTime();
		result = fibCache(ind);
		finish = System.nanoTime();
		totTime = finish - start;
		System.out.print("With Cache again (ind  " + ind + "): Result: " + result + ", time to run: " + totTime + "ns\n");
		
		start = System.nanoTime();
		result = fibNaive(index);
		finish = System.nanoTime();
		totTime = finish - start;
		System.out.print("Naive: Result: " + result + ", time to run: " + totTime + "ns\n");
		
		/*Reset cache for hybrid test*/
		cache = new HashMap<Integer, Integer>();
		cache.put(0, 0);
		cache.put(1, 1);
		
		start = System.nanoTime();
		result = fibHybrid(index);
		finish = System.nanoTime();
		totTime = finish - start;
		System.out.print("Hybrid: Result: " + result + ", time to run: " + totTime + "ns\n");
		
		start = System.nanoTime();
		result = fibIterative(index);
		finish = System.nanoTime();
		totTime = finish - start;
		System.out.print("Iterative: Result: " + result + ", time to run: " + totTime + "ns\n");
		
		start = System.nanoTime();
		result = fibIterative(ind);
		finish = System.nanoTime();
		totTime = finish - start;
		System.out.print("Iterative again (ind " + ind + "): Result: " + result + ", time to run: " + totTime + "ns\n");
		
	}
	
	/*Assumes n is positive */
	public static int fibNaive(int n) {
		
		if (n == 0 || n == 1) {
			return n;
		}
		
		return fibNaive(n-1) + fibNaive(n-2);
	}
	
	/*Assumes n is positive */
	public static int fibCache(int n) {
		
		if (cache.get(n) != null) {
			return cache.get(n);
		}
		
		int fibRes = fibCache(n-1) + fibCache(n-2);
		cache.put(n, fibRes);
		return fibRes;
	}
	
	/*Assumes n is positive */
	public static int fibHybrid(int n) {
		if(n <= 13) {
			return fibNaive(n);
		}
		return fibCache(n);
	}
	
	/*Assumes n is positive*/
	public static int fibIterative(int n) {
		
		if(n >= list.size()) {
			for(int i = list.size(); i <= n; i++) {
				list.add(i, list.get(i-1) + list.get(i - 2));
			}
		}
		
		return list.get(n);
	}

}
