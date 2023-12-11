package ru.revoltech.dnsresolver.dns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RequestBuilderImpl implements RequestBuilder {

    private volatile short requestId = Short.MIN_VALUE;

    @Override
    public byte[] build(ResolverRequest request) {
        byte[] result = new FluentBuilder()
                .setId(requestId++)
                .setRecursive(true)
                .addQuestion(QName.fromHostname(request.getResolveHost()), QType.A, QClass.IN)
                .toByteArray();

        return result;
    }

    private static class FluentBuilder {
        private short id = 0x0001;
        private boolean recursive = true;
        private List<Question> questions = new ArrayList<>();

        public FluentBuilder setId(short id) {
            this.id = id;
            return this;
        }

        public FluentBuilder setRecursive(boolean recursive) {
            this.recursive = recursive;
            return this;
        }

        public FluentBuilder addQuestion(QName qName, QType qType, QClass qClass) {
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
            short questionCount = (short) questions.size();
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
            for (Question question : questions) {
                writeQuestion(output, question);
            }
        }

        private void writeQuestion(ByteArrayOutputStream output, Question question) throws IOException {
            writeQName(output, question.getQName());
            writeQType(output, question.getQType());
            writeQClass(output, question.getQClass());
        }

        private void writeQName(ByteArrayOutputStream output, QName qName) throws IOException {
            for (String section : qName.getSections()) {
                byte[] asciiSection = section.getBytes(StandardCharsets.US_ASCII);
                output.write(asciiSection.length);
                output.write(asciiSection);
            }

            output.write(0x00);
        }

        private void writeQType(ByteArrayOutputStream output, QType qType) {
            output.write(qType.getValue() >> 8);
            output.write(qType.getValue() & 0xFF);
        }

        private void writeQClass(ByteArrayOutputStream output, QClass qClass) {
            output.write(qClass.getValue() >> 8);
            output.write(qClass.getValue() & 0xFF);
        }

        @RequiredArgsConstructor
        @Getter
        private static class Question {

            private final QName qName;
            private final QType qType;
            private final QClass qClass;

        }
    }
}
