package com.openkm.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);
    public static boolean RESTRICT_FILE_MIME;
    public static String RESTRICT_FILE_NAME;
    public static long CLOUD_MAX_REPOSITORY_SIZE;
    public static long MAX_FILE_SIZE;
    public static boolean SYSTEM_PROFILING = false;

    public static boolean STORE_NODE_PATH = true;

    public static UUID ROOT_NODE_UUID = UUID.fromString("cafebabe-cafe-babe-cafe-babecafebabe");
}
