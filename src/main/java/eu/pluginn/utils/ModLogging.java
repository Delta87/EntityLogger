package eu.pluginn.utils;

import eu.pluginn.References;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModLogging {

    private static final Logger logger = LogManager.getLogger(References.MOD_ID);

    public static void info(String logMessage){
            logger.info("[Info]: " + logMessage);
    }

    public static void warning(String logMessage){
        logger.warn("[Warning]: " + logMessage);
    }

    public static void error(String logMessage){
        logger.info("[Error]: " + logMessage);
    }
}
