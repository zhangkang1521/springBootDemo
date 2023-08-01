package org.zk.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

	/**
	 * 不定义会导致序列化id发生变化，出现兼容性问题
	 * org.springframework.data.redis.serializer.SerializationException:
	 * Cannot deserialize; nested exception is org.springframework.core.serializer.support.SerializationFailedException:
	 * Failed to deserialize payload. Is the byte array a result of corresponding serialization for DefaultDeserializer?;
	 * nested exception is java.io.InvalidClassException: org.zk.model.User;
	 * local class incompatible: stream classdesc serialVersionUID = 1, local class serialVersionUID = 3763359144362764013
	 */
	 // private static final long serialVersionUID = 3763359144362764013L;


	private Long id;

	private String username;

	private String sex;


}
