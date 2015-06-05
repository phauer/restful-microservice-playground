package de.philipphauer.prozu.util.ser;

import java.io.IOException;
import java.time.YearMonth;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("serial")
public class YearMonthSerializer extends StdSerializer<YearMonth> {

	public YearMonthSerializer() {
		super(YearMonth.class);
	}

	@Override
	public void serialize(YearMonth value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeString(value.toString());
	}

}
