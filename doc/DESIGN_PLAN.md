Ben Hubsch (bah37)
Jennifer Chin (jrc81)
Julia Long (jbl34)
Maya Messinger (mm479)

PLAN
===

### Introduction
Jennifer and Maya

### Design Overview
[Components]("slogoapi.jpg")
Julia

### User Interface
Jennifer and Maya

### API Details
public interface Interpreter implements Observer
* public void update(String s) {}

public interface Vizualization implements Observable
* public void addObserver(Observer obs) {}
* public void notifyObservers (Object arg) {}
* protected void setChanged() {}
* public void draw() {}

public interface eDrawable
* public void draw(Group g) {}

public interface Updatable
* public setter methods called by Command
    * setX() {}
    * setY() {}
    * setColor() {}
    * etc.

public interface Parser
* public Collection parse(String s) {}

public interface Command
* public void execute(Turtle t) {}

public interface CommandFactory
* public void createCommand() {}

### API Example Code
Everyone handles their own API

### Design Considerations
Ben

### Team Responsibilities
#### Ben
* Command, CommandFactory
#### Jennifer
* Visualization and Drawable interfaces (w/ Maya)
#### Julia
* Parser, Interpreter
#### Maya
* Visualization and Drawable interfaces (w/ Jennifer)
    * language