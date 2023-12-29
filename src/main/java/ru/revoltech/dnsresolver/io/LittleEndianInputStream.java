package ru.revoltech.dnsresolver.io;

import java.io.*;

public class LittleEndianInputStream extends FilterInputStream {
    public LittleEndianInputStream(InputStream baseStream) {
        super(baseStream);
    }

    public byte readByte() throws IOException {
        return DataStreamHelper.readByte(this);
    }

    public short readShort() throws IOException {
        return DataStreamHelper.readShortLittleEndian(this);
    }

    public String readStringASCII(int length) throws IOException {
        return DataStreamHelper.readStringASCIILittleEndian(this, length);
    }
}
