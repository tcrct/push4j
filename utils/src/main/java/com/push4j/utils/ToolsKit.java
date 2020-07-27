package com.push4j.utils;


/**
 * Hello world!
 *
 */
public class ToolsKit {

    public static String getCrc16( String msg ) {
        return CRC16Util.getCrcString(msg);
    }

    public static void main(String[] args) {
        String crc ="pay"+"交易成功推1送" + System.currentTimeMillis();
        System.out.println(CRC16Util.getCrcString(crc));
    }
}
