package util;

import org.slf4j.LoggerFactory;

public class LoggerUtil {
	public static  org.slf4j.Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
	public static void info(String param){
		logger.info(param);
	}
	public static void info(String pattern,Object...objects){
		logger.info(pattern,objects);
	}
}
