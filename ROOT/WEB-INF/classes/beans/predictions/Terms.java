package beans.predictions;

public class Terms
{
	public Terms(){}
	
	String value;
	int offset;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
}