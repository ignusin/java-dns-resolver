package ru.revoltech.dnsresolver.dns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.revoltech.dnsresolver.io.LittleEndianOutputStream;
import ru.revoltech.dnsresolver.io.LittleEndianOutputStreamHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestBuilderImpl implements RequestBuilder {

    private AtomicInteger requestId = new AtomicInteger(Short.MIN_VALUE);

    @Override
    public byte[] build(ResolverRequest request) {
        byte[] result = new FluentBuilder()
                .setId((short)requestId.getAndIncrement())
                .setRecursive(true)
                .addQuestion(QName.fromHostname(request.getResolveHost()), QType.A, QClass.IN)
                .toByteArray();

        return result;
    }

    private static class FluentBuilder {
        private short id = 0x0001;
        private boolean recursive = true;
        private final List<Question> questions = new ArrayList<>();

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
            try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                 LittleEndianOutputStream output = new LittleEndianOutputStream(byteStream)) {
                writeHeader(output);
                writeBody(output);

                return byteStream.toByteArray();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        private void writeHeader(LittleEndianOutputStream output) throws IOException {
            writeId(output);
            writeFlags(output);
            writeQuantities(output);
        }

        private void writeId(LittleEndianOutputStream output) throws IOException {
            output.writeShort(id);
        }

        private void writeFlags(LittleEndianOutputStream output) throws IOException {
            if (recursive) {
                output.writeByte(0x01);
            } else {
                output.writeByte(0x00);
            }

            output.writeByte(0x00);
        }

        private void writeQuantities(LittleEndianOutputStream output) throws IOException {
            short questionCount = (short) questions.size();
            output.writeShort(questionCount);
            output.writeShort(0);
            output.writeShort(0);
            output.writeShort(0);
        }

        private void writeBody(LittleEndianOutputStream output) throws IOException {
            for (Question question : questions) {
                writeQuestion(output, question);
            }
        }

        private void writeQuestion(LittleEndianOutputStream output, Question question) throws IOException {
            writeQName(output, question.getQName());
            writeQType(output, question.getQType());
            writeQClass(output, question.getQClass());
        }

        private void writeQName(LittleEndianOutputStream output, QName qName) throws IOException {
            for (String section : qName.getSections()) {
                output.writeByte(section.length());
                output.writeStringASCII(section);
            }

            output.writeByte(0x00);
        }

        private void writeQType(LittleEndianOutputStream output, QType qType) throws IOException {
            output.writeShort((short)qType.getValue());
        }

        private void writeQClass(LittleEndianOutputStream output, QClass qClass) throws IOException {
            output.writeShort((short)qClass.getValue());
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
