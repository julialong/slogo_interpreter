package commands;

public interface Commandable {
	double execute();
	
	boolean isReady();
	
	void inject(double arg);
}
