package org.ow2.proactive.brokering.triggering;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyRuntimeException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.ow2.proactive.brokering.Scripts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class ScriptUtils {

    private static final Logger logger = Logger.getLogger(ScriptUtils.class.getName());

    public static Class getEncodedScriptAsClass(Map<String, String> args, Scripts scripts, String key)
            throws ScriptException {

        GroovyClassLoader gcl = new GroovyClassLoader();
        String argumentScript = args.get(key);

        if (argumentScript == null)
            return null;

        if (isFileName(argumentScript))
            try {
                return scripts.getScriptAsClass(argumentScript);
            } catch (IOException e) {
                throw new ScriptException(e);
            }
        else if (encodedScriptIsNotEmpty(argumentScript))
            try {
                String script = decode(argumentScript);
                logger.debug("Script decoded: '" + script + "'");
                return gcl.parseClass(script);
            } catch (GroovyRuntimeException e) {
                throw new ScriptException(e);
            }

        throw new ScriptException("Cannot interpret: " + argumentScript);

    }

    private static boolean encodedScriptIsNotEmpty(String encodedScript) {
        return (!encodedScript.isEmpty());
    }

    private static boolean isFileName(String className) {
        return (className.contains("."));
    }

    public static String encode(String src) {
        return new String(Hex.encodeHex(src.getBytes()));
    }

    public static String decode(String src) {
        try {
            return new String(Hex.decodeHex(src.toCharArray()));
        } catch (DecoderException e) {
            throw new RuntimeException("Error decoding: " + src, e);
        }
    }

}


