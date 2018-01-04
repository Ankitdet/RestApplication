package com.test.ws.utils;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import org.apache.cxf.tools.corba.idlpreprocessor.IncludeResolver;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.jaxrs.Annotations;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.StdSerializerProvider;

import java.io.IOException;

@Provider
@Consumes({ MediaType.APPLICATION_JSON, "text/json" })
@Produces({ MediaType.APPLICATION_JSON, "text/json" })
public class JacksonJaxbJsonProvider extends JacksonJsonProvider {

	public static ObjectMapper objectMapper ;
	public final static Annotations[] DEFAULT_ANNOTATIONS = { Annotations.JACKSON, Annotations.JAXB };

	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		/*objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);*/
		StdSerializerProvider sp = new StdSerializerProvider();
		sp.setNullValueSerializer(new NullSerializer());
		objectMapper.setSerializerProvider(sp);
	}

	public JacksonJaxbJsonProvider() {
		super(objectMapper, DEFAULT_ANNOTATIONS);
	}

	public static class NullSerializer extends JsonSerializer<Object> {
		public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
			jgen.writeString("");
		}
	}
}

