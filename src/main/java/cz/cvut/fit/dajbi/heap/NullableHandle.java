package cz.cvut.fit.dajbi.heap;

public interface NullableHandle {

	public boolean isNull();
	
	public void setNull();

	public void IncReferences();
	
	public void DecReferences();
}
