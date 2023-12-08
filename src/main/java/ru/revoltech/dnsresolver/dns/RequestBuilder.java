package ru.revoltech.dnsresolver.dns;

import org.apache.commons.lang3.NotImplementedException;

public class RequestBuilder {
    private short id = 0x0001;

    public RequestBuilder setId(short id) {
        this.id = id;
        return this;
    }

    // public RequestBuilder addQuestion()

    public byte[] toByteArray() {
        throw new NotImplementedException();
    }
}
