package interfaces;

import java.io.Serializable;

import ReadderWriter.Reader;
import ReadderWriter.Writer;

public interface Map extends Serializable {
	public void map(Reader reader, Writer writer);
}