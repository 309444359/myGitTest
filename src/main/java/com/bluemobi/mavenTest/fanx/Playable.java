package com.bluemobi.mavenTest.fanx;

public interface Playable<T> {
	
	void play(T t);
	
	T getPlayer();

}
