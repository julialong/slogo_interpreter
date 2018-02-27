package model;

import commands.CommandFactory;
import model.Unbundlers.RepeatUnbundler;

import java.util.HashMap;
import java.util.Map;

public class UnbundleTest {

    private CommandFactory myCommandFactory;
    private Parser myParser;
    private HashMap<String, String> myVariables;
    private RepeatUnbundler myUnbundler;

    private UnbundleTest() {
        myCommandFactory = new CommandFactory(new HashMap<>());
        myParser = new Parser(myCommandFactory);
        myVariables = new HashMap<>();
        myVariables.put("x", "fd 50");
        String[] exp = {"2"};
        String[] command = {"fd", "fd", "2"};
        myUnbundler = new RepeatUnbundler(myParser, myVariables, exp, command);
        System.out.println(myUnbundler.getUnbundledString());
    }

    public static void main(String[] args) {
        UnbundleTest u = new UnbundleTest();
    }
}
