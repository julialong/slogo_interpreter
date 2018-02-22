### API REVIEW

All answers are referencing Team _'s API, and we are both working on front end. 

#### Part 1

1. The turtle interface has public methods such as move that allow the turtle to be updated. Therefore it is easy to add new turtles. Every aspect of the GUI has its own class, so it is easy to add new features to the GUI as well. There is also a Pen class. 

2. It is encapsulating the design implementation because the API doesn't give access to the instance variables of the Turtle, rather just the public methods. The actual details of the method are concealed, and other classes just implement the methods from the interface.

3. The front end isn't throwing any exceptions - the Parser decides whether or not a command is valid and the front end just displays the exceptions. The exceptions won't crash the program, but just throw an alert.

4. The front end API is really flexible because it is easy to add new objects such as Pens, Turtles, and Commands. There are APIs for the Turtle and the Pen, and there is inheritance and polymorphism for the GUI.

#### Part 2

1. Right now, no Design Patterns are being used, but the Factory pattern could help the design of the Turtles and Commands.

2. 