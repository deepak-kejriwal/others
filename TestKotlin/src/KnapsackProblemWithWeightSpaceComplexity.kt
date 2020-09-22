//package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

//import java.util.List

//import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.KnapsackProblem.Clock

/* Problem 17.6.3 page 325 */

fun main(args : Array<String>) {
	var c1: Clock=Clock(60,5);
	var c2: Clock=Clock(50,3);
	var c3: Clock=Clock(70,4);
	var c4: Clock=Clock(30,2);
	var list=listOf(c1,c2,c3,c4);
	//list.add(c1);
    println(list)
	var p=KnapsackProblemWithWeightSpaceComplexity.computeBottomUpWithMemoization(5,list);
	println(p);
}

object KnapsackProblemWithWeightSpaceComplexity {

    fun computeBottomUpWithMemoization(capacity: Int, clocks: List<Clock>): Int {
        val cache = MutableList(capacity + 1) { 0 }
        clocks.forEachIndexed { clockIndex, clock ->
            (capacity downTo 0).forEach { capacity ->
                var ct=
                        if (clockIndex == 0) {
                            // Seed the cache.
                            if (capacity >= clock.weight)
                                clock.value else 0
                       } else {
							var list=listOf(capacity, capacity - clock.weight);
                            var map=list.map {
                                        // Filter out invalid clockIndex +
                                        // capacity pairs.
                                        if (it >= 0 )
                                            cache[it]
                                        else null
                                    };
							
                             map.let {
                                        // Select the max value.
                                       var list1= listOfNotNull(
                                            it[0],
                                            it[1]?.let { it + clock.value });
                                                list1.max() ?: 0
										
                            }
							
                        }
				cache[capacity] =ct;
            }
        }
		var vald=cache[capacity];
		println(vald);
        return cache[capacity]
    }

}