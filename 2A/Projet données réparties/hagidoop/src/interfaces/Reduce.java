package interfaces;

import ReadderWriter.*;
import java.io.Serializable;

public interface Reduce extends Serializable {
	public void reduce(Reader reader, Writer writer);
}
