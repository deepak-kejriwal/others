package com.leetcode.google.top50;

import java.util.HashMap;
import java.util.Map;

/*
 * 
 Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;

// logging string "bar" at timestamp 8
logger.shouldPrintMessage(8,"bar"); returns false;

// logging string "foo" at timestamp 10
logger.shouldPrintMessage(10,"foo"); returns false;

// logging string "foo" at timestamp 11
logger.shouldPrintMessage(11,"foo"); returns true;
 */
public class Q359_LoggerRateLimiter {
    private Map<String, Integer> log;
    
    public Q359_LoggerRateLimiter() {
        log = new HashMap<>();
    }
    
    
    public boolean shouldPrintMessageAlt(int timestamp, String message) {
  
        int lastPrintTime = log.containsKey(message) ? log.get(message) : -10;
        if (timestamp - lastPrintTime >= 10) {
            log.put(message, timestamp);
        }
        return timestamp - lastPrintTime >= 10;
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false. The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
    //update timestamp of the message if the message is coming in for the first time,or the last coming time is earlier than 10 seconds from now
        if(!log.containsKey(message)||timestamp-log.get(message)>=10){
        	log.put(message,timestamp);
            return true;
        }
        return false;
    }
}
