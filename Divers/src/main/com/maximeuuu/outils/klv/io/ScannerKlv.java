package com.maximeuuu.outils.klv.io;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.maximeuuu.outils.klv.objets.KlvEncode;

public class ScannerKlv implements Closeable {

	private static final int OPTIMIZED_BUFFER_SIZE = 1024 * 8;
	private static final int ESTIMATION_MAX_ERREUR_ITERATIONS = 1000;

	private final BufferedInputStream reader;
	private int expectedKeySize;
	private int expectedLengthSize;
	private byte[] expectedKey;

	public ScannerKlv(InputStream inputStream) {
		this.reader = new BufferedInputStream(inputStream, OPTIMIZED_BUFFER_SIZE);
		this.expectedKeySize = 0;
		this.expectedLengthSize = 0;
		this.expectedKey = null;
	}

	public void setExpectedKeySize(int size) {
		this.expectedKeySize = size;
		this.expectedKey = null;
	}

	public void setExpectedLengthSize(int size) {
		this.expectedLengthSize = size;
	}

	public void setExpectedKey(byte[] key) {
		this.expectedKey = key;
		this.expectedKeySize = key.length;
	}

	public KlvEncode next() throws IOException {
		reader.mark(OPTIMIZED_BUFFER_SIZE);

		for (int i = 0; i < ESTIMATION_MAX_ERREUR_ITERATIONS; i++) {
			if (!hasNextKey()) {
				continue;
			}

			byte[] dataKey = nextKey();
			if (!verifyKey(dataKey)) {
				reader.reset();
				reader.skip(1);
				reader.mark(OPTIMIZED_BUFFER_SIZE);
				continue;
			}

			if (!hasNextLength()) {
				continue;
			}

			byte[] dataLength = nextLength();
			int length = bytesToLength(dataLength);

			if (!hasNextValue(length)) {
				continue;
			}

			byte[] dataValue = nextValue(length);
			return new KlvEncode(dataKey, length, dataValue);
		}

		return null;
	}

	private int bytesToLength(byte[] data) {
		int length = 0;
		for (int i = 0; i < expectedLengthSize; i++) {
			length = (length << 8) | (data[i] & 0xFF);
		}
		return length;
	}

	private byte[] nextKey() throws IOException {
		return readExactly(expectedKeySize);
	}

	private byte[] nextLength() throws IOException {
		return readExactly(expectedLengthSize);
	}

	private byte[] nextValue(int size) throws IOException {
		return readExactly(size);
	}

	private byte[] readExactly(int size) throws IOException {
		byte[] data = new byte[size];
		int bytesRead = reader.read(data);
		if (bytesRead != size) {
			throw new IOException("Lecture incomplète: attendu " + size + " octets, lu " + bytesRead);
		}
		return data;
	}

	private boolean hasNextKey() throws IOException {
		return reader.available() >= expectedKeySize;
	}

	private boolean hasNextLength() throws IOException {
		return reader.available() >= expectedLengthSize;
	}

	private boolean hasNextValue(int size) throws IOException {
		return reader.available() >= size;
	}

	private boolean verifyKey(byte[] data) {
		return this.expectedKey == null || Arrays.equals( data, this.expectedKey );
	}

	public boolean hasNext(){ //TODO: pas sur
		try{
			return this.reader.available() > 0;
		}
		catch( IOException ioe ){
			return false;
		}
	}

	public void clear() throws IOException {
		this.reader.skip( this.reader.available() );
	}
	
	@Override
	public void close() throws IOException {
		reader.close();
	}
}
