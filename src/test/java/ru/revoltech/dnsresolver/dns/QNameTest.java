package ru.revoltech.dnsresolver.dns;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class QNameTest {
    @Test
    public void testFromHostname() {
        QName qname = QName.fromHostname("test.hostname.org");

        Assertions.assertEquals(3, qname.getSections().size());
        Assertions.assertEquals("test", qname.getSections().get(0));
        Assertions.assertEquals("hostname", qname.getSections().get(1));
        Assertions.assertEquals("org", qname.getSections().get(2));
    }

    @Test
    public void testSections() {
        QName qname = new QName(List.of("test", "hostname", "org"));

        Assertions.assertEquals(3, qname.getSections().size());
        Assertions.assertEquals("test", qname.getSections().get(0));
        Assertions.assertEquals("hostname", qname.getSections().get(1));
        Assertions.assertEquals("org", qname.getSections().get(2));
    }
}
