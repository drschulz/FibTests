/**
 * @author Drew Schulz
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class FibComparison {
	
	public static HashMap<Integer, Long> cache;
	public static ArrayList<Long> list;
	
	public static void main(String[] args) {
		/*Change this value if needed*/
		int ind = 41;
		
		long result;
		long start, finish, totTime;
		/*limit of index*/
		int limit = 43;
		
		/*Initialize cache and list with 0 and 1*/
		cache = new HashMap<Integer, Long>();
		cache.put(0, (long) 0);
		cache.put(1, (long) 1);
		list = new ArrayList<Long>();
		list.add((long) 0);
		list.add((long) 1);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Which number in the Fib sequence would you like? ");
		
		int index = sc.nextInt();
		while (index < 0 || index > limit) {
			System.out.print("Enter a positive index less than " + limit + ": ");
			index = sc.nextInt();
		}
		
		/*long cRes, nRes, iRes;
		for(int i = 0; i < index; i++) {
			cRes = fibCache(i);
			nRes = fibNaive(i);
			iRes = fibIterative(i);
			
			if(cRes != nRes || cRes != iRes) {
				System.out.println("Error on fib with index " + index + "\n");
			}
		}*/
		
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
		cache = new HashMap<Integer, Long>();
		cache.put(0, (long) 0);
		cache.put(1, (long) 1);
		
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
	public static long fibNaive(int n) {
		
		if (n == 0 || n == 1) {
			return n;
		}
		
		return fibNaive(n-1) + fibNaive(n-2);
	}
	
	/*Assumes n is positive */
	public static long fibCache(int n) {
		
		if (cache.get(n) != null) {
			return cache.get(n);
		}
		
		long fibRes = fibCache(n-1) + fibCache(n-2);
		cache.put(n, fibRes);
		return fibRes;
	}
	
	/*Assumes n is positive */
	public static long fibHybrid(int n) {
		if(n <= 13) {
			return fibNaive(n);
		}
		return fibCache(n);
	}
	
	/*Assumes n is positive*/
	public static long fibIterative(int n) {
		
		if(n >= list.size()) {
			for(int i = list.size(); i <= n; i++) {
				list.add(i, list.get(i-1) + list.get(i - 2));
			}
		}
		
		return list.get(n);
	}

}
