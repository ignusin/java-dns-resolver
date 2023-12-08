package ru.revoltech.dnsresolver.dns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestBuilder {
    private short id = 0x0001;
    private boolean recursive = true;
    private List<Question> questions = new ArrayList<>();

    public RequestBuilder setId(short id) {
        this.id = id;
        return this;
    }

    public RequestBuilder setRecursive(boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    public RequestBuilder addQuestion(QName qName, QType qType, QClass qClass) {
        questions.add(new Question(qName, qType, qClass));
        return this;
    }

    public byte[] toByteArray() {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            writeHeader(output);
            writeBody(output);

            return output.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void writeHeader(ByteArrayOutputStream output) throws IOException {
        writeId(output);
        writeFlags(output);
        writeQuantities(output);
    }

    private void writeId(ByteArrayOutputStream output) throws IOException {
        output.write(id >> 8);
        output.write(id & 0xFF);
    }

    private void writeFlags(ByteArrayOutputStream output) throws IOException {
        if (recursive) {
            output.write(0x01);
        } else {
            output.write(0x00);
        }

        output.write(0x00);
    }

    private void writeQuantities(ByteArrayOutputStream output) throws IOException {
        short questionCount = (short)questions.size();
        output.write(questionCount >> 8);
        output.write(questionCount & 0xFF);

        output.write(0x00);
        output.write(0x00);

        output.write(0x00);
        output.write(0x00);

        output.write(0x00);
        output.write(0x00);
    }

    private void writeBody(ByteArrayOutputStream output) throws IOException {

    }

    @RequiredArgsConstructor
    @Getter
    private static class Question {
        private final QName qName;
        private final QType qType;
        private final QClass qClass;
    }
}
