package commands;

public interface Commandable {
	void execute();
	
	boolean isReady();
	
	void inject(Double arg);
	
	Double getAns();
}
