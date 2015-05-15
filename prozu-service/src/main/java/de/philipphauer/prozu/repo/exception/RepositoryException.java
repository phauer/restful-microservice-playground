package de.philipphauer.prozu.repo.exception;

@SuppressWarnings("serial")
public class RepositoryException extends RuntimeException {

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(String message, Throwable ex) {
		super(message, ex);
	}

}
