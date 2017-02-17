package com.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;



public final class getMACAddress{

/**
    * 현재 컴퓨터의 맥 주소를 리턴한다.
    * 
    * @return MAC Address
    * @throws IOException
    */
    public final static String getMacAddress() throws IOException {
        String os = System.getProperty("os.name");

        if (os.startsWith("Windows")) {
            return ParseMacAddress(windowsRunIpConfigCommand());
        } else if (os.startsWith("Linux")) {
            return ParseMacAddress(linuxRunIfConfigCommand());
        } else {
            throw new IOException("unknown operating system: " + os);
        }

    }

    /*
    * Linux 에 있는 네트워크 설정 값들을 문자열로 불러온다.
    */

    private final static String linuxRunIfConfigCommand() throws IOException {
        Process p = Runtime.getRuntime().exec("ifconfig");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

        StringBuffer buffer = new StringBuffer();
        for (;;) {
            int c = stdoutStream.read();
            if (c == -1)
                break;
            buffer.append((char) c);
        }
        String outputText = buffer.toString();

        stdoutStream.close();

        return outputText;
    }

    /*
    * Windows에 있는 네트워크 설정값들을 문자열로 가져온다.
    */

    private final static String windowsRunIpConfigCommand() throws IOException {
        Process p = Runtime.getRuntime().exec("ipconfig /all");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

        StringBuffer buffer = new StringBuffer();
        for (;;) {
            int c = stdoutStream.read();
            if (c == -1)
                break;
            buffer.append((char) c);
        }
        String outputText = buffer.toString();

        stdoutStream.close();

        return outputText;
    }

    /**
    * 문자열에서  패턴에 맞는 문자열 즉 맥주소를 뽑아낸다.
    * @param text 검사할 문자열
    * @return 맥 주소
    */
    public static String ParseMacAddress(String text) {
        String result = null;
        String[] list = text.split("\\p{XDigit}{2}(-\\p{XDigit}{2}){5}");
        int index = 0;
        for (String str : list) {
            if (str.length() < text.length()) {
                index = str.length();
                result = text.substring(index, index + 17);
                if (!result.equals("00-00-00-00-00-00")) {
                    break;
                }
                text = text.substring(index + 17);

            }
        }
        return result;
    }
   
    public static void main(String[] args)throws IOException {
        System.out.print("MAC Address :: ");
        System.out.println(getMACAddress.getMacAddress());
    }

}

	


