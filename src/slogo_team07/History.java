///**
// * @author Maya Messinger (mm479)
// * Started 21 Feb 18
// * Com
// */
//
//package slogo_team07;
//
//import java.util.List;
//import java.util.ArrayList;
//import javafx.scene.control.TextArea;
//import slogo_team07.TextInput;
//
//public class History extends TextArea implements TextInput {
//    private TextArea history;
//    private List<String> pastCommands;
//
//    private int width = 500;
//    private int height = 100;
//    private int commandIndex = -1;   // will track what command is "last" for scrollability
//
//    public History()    {
//        history = new TextArea();
//        pastCommands = new ArrayList<>();
//
//        history.setPrefWidth(width);
//    }
//
//    @Override
//    public void scrollUp() {
//        if (!pastCommands.isEmpty())   {
//            if (commandIndex == -1)   {
//                // have never scrolled before, so set index of last command to most recent
//                commandIndex = pastCommands.size();
//                scrollUp();
//            }
//            else if (commandIndex > 0)  {
//                history.setText(pastCommands.get(commandIndex--));
//            }
//        }
//    }
//
//    @Override
//    public void scrollDown() {
//        if (!pastCommands.isEmpty())   {
//            if (commandIndex == -1)   {
//                commandIndex = pastCommands.size();
//                scrollDown();
//            }
//            else if (commandIndex < pastCommands.size() - 1)  {
//                history.setText(pastCommands.get(commandIndex++));
//            }
//        }
//    }
//}