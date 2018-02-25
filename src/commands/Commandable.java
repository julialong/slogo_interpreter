package commands;

public interface Commandable {
	Result execute();
	
	void inject(Double arg);
}
