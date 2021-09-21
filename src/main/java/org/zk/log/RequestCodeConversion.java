package org.zk.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class RequestCodeConversion extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		return "[xxx]" + event.getMessage() ;
	}
}
