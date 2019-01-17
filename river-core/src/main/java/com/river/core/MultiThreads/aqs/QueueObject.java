package com.river.core.MultiThreads.aqs;

public class QueueObject {
	private boolean isNotified = false;
    /**
     * 等待
     */
	public synchronized void doWait() {
		while (!isNotified) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.isNotified = false;
	}
    /**
     * 唤醒
     */
	public synchronized void doNotify() {
		this.isNotified = true;
		this.notify();
	}

	public boolean equals(Object o) {
		return this == o;
	}
}
