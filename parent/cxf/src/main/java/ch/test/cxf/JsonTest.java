package ch.test.cxf;
import com.fasterxml.jackson.annotation.JsonProperty;
public class JsonTest {
	private String name;
	@JsonProperty("DBMongo")
	public String getName() {
		return name;
	}
	@JsonProperty("MongoDB")
	public void setName(String name) {
		this.name = name;
	}
	private String test;
	@JsonProperty("name")
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
}