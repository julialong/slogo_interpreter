Ben Hubsch (bah37)
Jennifer Chin (jrc81)
Julia Long (jbl34)
Maya Messinger (mm479)

PLAN
===

### Introduction
Jennifer and Maya

### Design Overview
[]("UML.png")
Our project will be divided into 4 parts, or packages: View, Model, Controller, and Command. 
#### View
View is the part of the program that will control everything that the user sees. There will be a main Visualizer interface that is implemented by the Visualization class. This interface will also implement Observable, so that the Controller part of the project can monitor the text box that the user types commands in. Three of the public methods in this interface are ones from the Observable interface, specifically addObserver(), notifyObserver(), and setChanged(). The other public method of the Visualizer interface is draw(), which will call a Drawable object to draw itself on the user's screen. There will be other private methods within the Visualization class that assist in the creation of the user interface.
#### Model 
The Model package will contain our Turtle object. The Turtle will hold private instance variables, including an ID number that identifies which turtle that object is, an x and y postition, an image, and a color. It will also implement two interfaces-- Drawable and Updatable-- so that the Turtle can hold all relevant information for both the Controller and View portions of the project, without either part being able to modify the other. The Drawable interface will contain one public method: draw, which takes in a JavaFX group, so that the turtle can draw itself on the screen. The Updatable interface contains several public setters, so that the Controller portion of the project can update the Turtle as needed.  
#### Controller
The Controller component will have two classes, Parsing and Interpretation, that implement two interfaces, Parser and Interpreter, respectively. The Parser interface will take a String and return a Collection of Objects, specifically, Command objects, after parsing it. The Interpreter interface will implement the Observer interface so that it can observe the text box in the Visualization class, as mentioned earlier. It will have a public update method that allows it to update the Turtle objects through the Updatable interface.
#### Command
The Command interface will be implemented my a number of Command subclasses. There will be a Command subclass for every type of action. The Parser will create new Command objects through the CommandFactory class and return a Collection of them to the Interpreter through the createCommand method. These commands will have a public method execute() that takes in a Turtle object and updates the Turtle.

### User Interface
Jennifer and Maya

### API Details
public interface Interpreter implements Observer
* public void update(String s) {}

The purpose of this API is to provide a bridge from the Visualizer to the Turtle, so that the user input will properly update the state of the Turtle.

The Interpreter will implemented by classes that are responsible for controlling the Model portion of the project, in this case our Turtle. The Interpreter will implement the Observer interface because it will need to observe the text box in the Visualizer. This interface will call an update method when it is notified of a change in the text box, which will update the Updatable interface of the Turtle object.

The Interpreter's external API only consists of the update method, which needs to be accessible to other parts of the project in order to update the Turtle. Other helper methods that can assist with other requirements of the project can be contained within Interpreter or at most within the Controller package, so these methods would be able to remain private. Because the Interpreter will only access the Updatable interface of the Turtle object, it will be simple to switch from Turtle objects to another object that implements the same Updatable interface. 

public interface Vizualization implements Observable
* public void addObserver(Observer obs) {}
* public void notifyObservers (Object arg) {}
* protected void setChanged() {}
* public void draw() {}

public interface Drawable
* public void draw(Group g) {}

public interface Updatable
* public setter methods called by Command
    * setX() {}
    * setY() {}
    * setColor() {}
    * etc.

The Updatable API will be comprised of a collection of setter methods that allow an object that implements this interface to be modified. This will be implemented by the Turtle, so that the Interpreter classes can modify it without actually having to hold an instance of the Turtle itself. This allows the actual object to be modified and changed without having to modify the Interpreter interface itself, because the Interpreter will never know which object is implementing the Updatable interface that it is modifying, and only the interface itself.

public interface Parser
* public Collection parse(String s) {}

The purpose of this API is to take in a String passed in by the user, and return a Command object that encapsulates the desired behavior for the turtle.

The Parser interface will be implemented by a Parsing class. The Interpreter will call the Parser when it has been notified by the Visualizer that the user has put a new string that needs to be turned into a command. The Parser will take in a String and split it up into correct commands and values, and then pass those commands and values to the CommandFactory, which will pass back Command objects, which will be returned in a Collection of Commands from the parse method.

This interface will "parse" any inputs by the user, decide if they are valid, and then pass the arguments to the CommandFactory so that the correct command can be created. The validity at this stage will simply be determined by the structure of the argument, and not the actual argument itself. This flexibility will allow a variety of new commands to be implemented as long as they have a valid structure and will be recognized by the CommandFactory.

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