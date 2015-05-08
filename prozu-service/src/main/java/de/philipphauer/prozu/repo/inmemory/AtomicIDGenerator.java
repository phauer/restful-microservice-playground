package de.philipphauer.prozu.repo.inmemory;

import java.util.concurrent.atomic.AtomicLong;

import com.google.inject.Singleton;

@Singleton
public class AtomicIDGenerator {

	private AtomicLong id = new AtomicLong();

	public long generateID() {
		return id.getAndIncrement();
	}
}
