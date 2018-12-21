package com.coders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Deepak Kejriwal
 *
 */

/*
 * Generally we use new HashSet<>() and then addAll method to create copy of
 * HashSet. Below example demonstrate that it will work only if value is
 * Immutable. For Immutable one need to create separate copy of value.
 */
public class CreateCopyOfHashSet {

	public static void main(String[] args) {
		Set<List<Integer>> set = new HashSet<>();
		List<Integer> list1 = Stream.of(1, 2, 3).collect(Collectors.toList());
		List<Integer> list2 = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
		set.add(list1);
		set.add(list2);
		System.out.println("Initial Set Value");
		System.out.println("Set: " + set);
		// Create copy1 using addAll
		Set<List<Integer>> copy1 = createCopyMethod1(set);
		// Using copy2 by creating separate copy of value
		Set<List<Integer>> copy2 = createCopyMethod2(set);
		// Modifying copy1 by adding 10 to each list
		for (List<Integer> list : copy1) {
			list.add(10);
		}
//Printing Set, Copy1 and Copy2. Observation: Modification of Copy1 lead to modification lead to modification of Original Set.
		System.out.println("Copy1:        " + copy1);
		System.out.println("Original Set: " + set);
		System.out.println("Copy2:        " + copy2);
		// Now modifying copy2 by adding 20 to each list
		for (List<Integer> list : copy2) {
			list.add(20);
		}
//Observation, modifying copy2 doesn't impact other sets (Original Set and copy1).		
		System.out.println("Copy1:        " + copy1);
		System.out.println("Original Set: " + set);
		System.out.println("Copy2:        " + copy2);
		System.out.println("Hence copy2 is a true copy of set.");
	}

	private static Set<List<Integer>> createCopyMethod2(Set<List<Integer>> set) {
		Set<List<Integer>> set2 = new HashSet<>();
		for (List<Integer> list : set) {
			set2.add(new ArrayList<Integer>(list));
		}
		return set2;
	}

	private static Set<List<Integer>> createCopyMethod1(Set<List<Integer>> set) {
		Set<List<Integer>> set2 = new HashSet<>();
		set2.addAll(set);
		return set2;
	}
}
