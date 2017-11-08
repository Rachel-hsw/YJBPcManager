package com.res.pc.util;

import java.util.concurrent.locks.ReentrantLock;

public class Slock {
	public static final ReentrantLock slock_bill = new ReentrantLock();
	public static final ReentrantLock slock_order = new ReentrantLock();
}
