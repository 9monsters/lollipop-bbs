package com.just.lollipop.bbs.exception;


public class PtException extends Exception{

	private static final long serialVersionUID = -7498532032324540068L;
	private Throwable cause = null;
	
	public PtException() {
		super();
	}
	public PtException(String message) {
		super(message);
	}
	public PtException(Throwable cause) {
		super();
		this.cause = cause;
	}
	public PtException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
	}
	
	@Override
	public String getMessage() {
		if (super.getMessage() != null && cause != null){
			return super.getMessage() + "\r\n" + ExceptionUtils.getCause(cause);
		}else if (cause != null) {
			return ExceptionUtils.getCause(cause);
		}else if (super.getMessage() != null){
			return super.getMessage();
		}else{
			return null;
		}
	}
}
