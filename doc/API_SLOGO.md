Ben Hubsch (bah37)
Jennifer Chin (jrc81)
Julia Long (jbl34)
Maya Messinger (mm479)

API SLogo
=====

1. The parsing needs to take place on the user's command (like when they press "Enter"). It only needs the raw input string entered by the user in the correct format with instruction and value.

2. The parser should return a List of Command objects. Command objects each have instance variables: instruction and value. A backend class will take the commands and apply them one by one (effectively a for each loop) to the turtle. After each command is run, the backend will send a message to the visualizer to show the turtle's new state.

3. Invalid strings will be detected in the parser, and invalid commands will be detected in the interpreter. We can think of two kinds of invalid commands: the first is a command input that doesn't match an instruction that the Interpreter knows how to execute (this might happen after typos) and if the value for a command doesn't match the expected type (i.e., trying to advance a turtle by "one hundred" instead of 100). The appropriate exception can be thrown from these classes and the visualization component will display the correct error message, instructing the user on how they can re-run the command to comply with proper syntax.

4. Commands have a reference to the turtle object and also know the implementation details required to execute themselves and the argument specified by the user. Commands know that information when it is their turn to be executed from our Interpreter. The Parser gives it to the Interpreter, and the Interpreter gives the value to the command when it's run. It has the Turtle object injected into the constructor.

5. The GUI will call a display() method that the turtle object has. The turtle will use its own information (x position, y position, color, other visual characteristics) to draw itself on the screen according to its updated information.