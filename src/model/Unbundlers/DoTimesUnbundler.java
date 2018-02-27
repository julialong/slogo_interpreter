package model.Unbundlers;

import model.Parser;

import java.util.ArrayList;
import java.util.Map;

public class DoTimesUnbundler {

    private Map<String, String> dictionary;
    private Parser pr;

    private double repeat;

    private StringBuilder expression;
    private ArrayList<String> unbundledArray;

    private static final String LEFT_BRACE = "[";
    private static final String RIGHT_BRACE = "]";
}
