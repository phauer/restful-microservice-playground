package de.philipphauer.prozu.util.ser;

import java.io.IOException;
import java.time.YearMonth;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class YearMonthDeserializer extends StdScalarDeserializer<YearMonth> {

	public YearMonthDeserializer() {
		super(YearMonth.class);
	}

	@Override
	public YearMonth deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		switch (parser.getCurrentToken()) {
		case VALUE_STRING:
			String monthString = parser.getText().trim();
			return YearMonth.parse(monthString);
		default:
			throw ctxt.wrongTokenException(parser, JsonToken.VALUE_STRING, "expected String");
		}
	}
}
