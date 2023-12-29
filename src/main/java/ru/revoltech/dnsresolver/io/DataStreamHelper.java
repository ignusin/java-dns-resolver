package ru.revoltech.dnsresolver.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DataStreamHelper {
    public static void writeByte(OutputStream output, byte value) throws IOException {
        output.write(value);
    }

    public static void writeShortLittleEndian(OutputStream output, short value) throws IOException {
        output.write(value >> 8);
        output.write(value & 0xFF);
    }

    public static void writeStringASCII(OutputStream output, String value) throws IOException {
        byte[] asciiBytes = value.getBytes(StandardCharsets.US_ASCII);
        output.write(asciiBytes);
    }

    public static byte readByte(InputStream input) throws IOException {
        int value = input.read();
        if (value < 0) {
            throw new EOFException();
        }

        return (byte)value;
    }

    public static short readShortLittleEndian(InputStream input) throws IOException {
        int b1 = readByte(input);
        int b2 = readByte(input);

        return (short)((b1 << 8) | b2);
    }

    public static String readStringASCIILittleEndian(InputStream input, int length) throws IOException {
        byte[] buffer = new byte[length];

        int readByteCount = input.read(buffer);
        if (readByteCount < length) {
            throw new EOFException();
        }

        return new String(buffer, StandardCharsets.US_ASCII);
    }
}
