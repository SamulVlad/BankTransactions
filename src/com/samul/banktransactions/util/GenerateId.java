package com.samul.banktransactions.util;

import java.util.Date;

public final class GenerateId {
	
	private GenerateId() {}
	
	public static long nextId() {
		return (new Date()).getTime();
	}


}
