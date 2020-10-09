package org.zk.avro;


import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.zk.dto.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class AvroSerializer implements Serializer<User> {

	@Override
	public void configure(Map configs, boolean isKey) {

	}

	@Override
	public byte[] serialize(String topic, User data) {
		if(data == null) {
			return null;
		}
		DatumWriter writer = new SpecificDatumWriter(data.getSchema());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(out, null);
		try {
			writer.write(data, encoder);
		}catch (IOException e) {
			throw new SerializationException(e.getMessage());
		}
		return out.toByteArray();
	}

	@Override
	public void close() {

	}
}
