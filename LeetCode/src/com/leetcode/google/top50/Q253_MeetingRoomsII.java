package com.leetcode.google.top50;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 
 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */

class Q253_MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        List<Point> points=new ArrayList<>();
        for(int[] interval:intervals){
            points.add(new Point(interval[0],true));
            points.add(new Point(interval[1],false));
        }
        Collections.sort(points);
        int max=0;
        int current=0;
        for(Point pnt:points){
            if(pnt.isStart){
                current++;
                if(current>max){
                    max=current;
                }
            }else{
                current--;
            }
        }
        return max;
    }
    
    class Point implements Comparable<Point> {
        int x;
        boolean isStart;
        Point(int x, boolean isStart){
            this.x=x;
            this.isStart=isStart;
        }
        
        @Override
        public int compareTo(Point other) {
 
            if (this.x == other.x) {
                if (this.isStart == other.isStart) {
                    return 0;
                } else {
                    return this.isStart ? 1 : -1;
                }
            }
            return this.x > other.x ? 1 : -1;
        }
        
         @Override
        public String toString(){
            return x+":"+isStart;
        }
    }
}