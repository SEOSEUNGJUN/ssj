package com.ssj.place.service.place;

import java.util.concurrent.locks.StampedLock;

/**
 * 클래스설명 : 
 * @version : 2020. 9. 21.
 * @author : SSJ
 * @분류 : 
 * place-management / package com.ssj.place.service.place;
 */

public class Count {
	   private int x;
	   private final StampedLock sl = new StampedLock();
	   void get(int cnt) { 
	// an exclusively locked method
	     long stamp = sl.writeLock();
	     try {
	       x += cnt;
	     } finally {
	       sl.unlockWrite(stamp);
	     }
	   }
}
