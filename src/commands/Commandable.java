package commands;

public interface Commandable {
	Result execute();
	
	boolean isReady();
	
	// an argument object potentially?
	void inject(Double arg);
}
