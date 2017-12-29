package com.test.ws.utils;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.Annotations;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonParser;

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
		
	}
	
	public JacksonJaxbJsonProvider() {
		super(objectMapper, DEFAULT_ANNOTATIONS);
	}
}
