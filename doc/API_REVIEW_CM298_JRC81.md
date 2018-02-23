### API REVIEW

All answers are referencing Team 9's API, and we are both working on front end. 

#### Part 1

1. The turtle interface has public methods such as move that allow the turtle to be updated. Therefore it is easy to add new turtles. Every aspect of the GUI has its own class, so it is easy to add new features to the GUI as well. There is also a Pen class. 

2. It is encapsulating the design implementation because the API doesn't give access to the instance variables of the Turtle, rather just the public methods. The actual details of the method are concealed, and other classes just implement the methods from the interface.

3. The front end isn't throwing any exceptions - the Parser decides whether or not a command is valid and the front end just displays the exceptions. The exceptions won't crash the program, but just throw an alert.

4. The front end API is really flexible because it is easy to add new objects such as Pens, Turtles, and Commands. There are APIs for the Turtle and the Pen, and there is inheritance and polymorphism for the GUI.

#### Part 2

1. Right now, no Design Patterns are being used, but the Factory pattern could help the design of the Turtles and Commands.

2. Implementing variables that have already been used and saving them to be used later.

3. Most nervous about integrating the front end and the back end.

4. Use Cases:
* User inputs an incorrect command: the raw input goes to the Parser and the Parser detects that the command is invalid and throws an error. The error is displayed as an alert by the front end.
* User creates their own command and saves it: the user inputs their code into the console and the Parser sends it to the back end. The back end will create a command object corresponding to the code and the front end will display the saved code on the side of the screen
* User changes the color of the turtle: the user will select a color from the pen color combo box on the side bar. Then the GUI will change the color of the lines being drawn.`
* User changes the language: 
