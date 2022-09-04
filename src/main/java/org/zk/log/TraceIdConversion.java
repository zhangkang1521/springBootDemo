package org.zk.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 定制traceId
 */
public class TraceIdConversion extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		return "123";
	}
}
