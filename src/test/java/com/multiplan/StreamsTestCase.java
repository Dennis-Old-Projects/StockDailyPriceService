package com.multiplan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamsTestCase {

	@Test
	public void testMin() {
		
		Stream<Integer> stream = Stream.of(1,2,3,4);
		
		assertEquals(1,stream.min(Comparator.comparing(a -> a)).orElseGet(() -> new Integer(-1)).intValue());
		
		/*
		assertEquals(1,stream.min((a,b) -> {
			if (a == b) return 0;
			if (a < b) return -1;
			if (a < b) return 1;
			return 1;
		}).get().intValue());
		*/
		
		stream = Stream.of(1,2,3,4);
		List<Integer> x = stream.filter(a -> a ==1).collect(Collectors.toList());
		assertEquals(1,x.get(0).intValue());
		
		stream = Stream.of(1,2,3,4);
		List<String> s = stream.map(a -> a+"").collect(Collectors.toList());
		
		 
		assertEquals("1",s.get(0));
		
		List<? super Number> superInteger = new ArrayList<>();
		superInteger.add(new Float(1.0f));
		stream = Stream.of(1,1,2,3,4);
		superInteger = stream.distinct().collect(Collectors.toList());
		assertTrue(superInteger.size() ==4);
		
		
	}

	@Test
	public void testStreamsFilter() {
		Stream<Integer> integerStream = Stream.of(1,2,3,4,5,5);
		
		List<Integer> filteredList = integerStream.filter(i -> i ==5).collect(Collectors.toList());
		assertTrue(filteredList.size() == 2);
		assertTrue(filteredList.contains(5));
	}

	@Test
	public void testStreamsMap() {
		Stream<Integer> integerStream = Stream.of(1,2,3,4,5,5);
		
		List<String> listOfStrings = integerStream.map(i -> i+"").filter(a -> a.equals("5")).collect(Collectors.toList());
		assertTrue(listOfStrings.size() ==2);
		assertTrue(listOfStrings.contains("5"));
	}
	
	@Test
	public void testStreamsDistinct() {
		Stream<Integer> integerStream = Stream.of(1,2,3,4,5,5);
		
		List<Integer> distinctIntegers = integerStream.distinct().collect(Collectors.toList());
		assertTrue(distinctIntegers.size() == 5);
		
	}
}
