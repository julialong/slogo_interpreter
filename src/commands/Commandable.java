package commands;

public interface Commandable {
	Result execute();
	
	boolean isReady();
	
	void inject(Double arg);
	
	Double getAns();
}
