package commands.factory;

import java.util.List;

import commands.Command;

public interface Factory {
	List<Command> create(String keyword);
}
