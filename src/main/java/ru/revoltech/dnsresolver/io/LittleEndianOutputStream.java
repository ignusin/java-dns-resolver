package ru.revoltech.dnsresolver.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LittleEndianOutputStream extends FilterOutputStream {
    public LittleEndianOutputStream(OutputStream baseStream) {
        super(baseStream);
    }

    public void writeByte(byte value) throws IOException {
        DataStreamHelper.writeByte(this, value);
    }

    public void writeByte(int value) throws IOException {
        writeByte((byte)value);
    }

    public void writeShort(short value) throws IOException {
        DataStreamHelper.writeShortLittleEndian(this, value);
    }

    public void writeShort(int value) throws IOException {
        writeShort((short)value);
    }

    public void writeStringASCII(String value) throws IOException {
        DataStreamHelper.writeStringASCII(this, value);
    }
}
