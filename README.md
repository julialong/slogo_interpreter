SLOGO
===

Date started: 15 Feb 18
Date finished: 9 Mar 18

<<<<<<< HEAD
Ben Hubsch (bah37)
* hours worked:

Jennifer Chin (jrc81)
* hours worked:

Julia Long (jbl34)
* hours worked: 35

Maya Messinger (mm479)
* hours worked: 45

# slogo
A development environment that helps users write SLogo programs.

### Roles
Ben and Julia worked mostly on the backend features while Jenny and Maya tackled the frontend. Ben created all of the 
Command objects (both turtle and otherwise), designed the final implementation of the parser, and created the parts of
the Turtle that were necessary for backend implementation *idk what else you did @Ben*. Julia worked on the initial 
(and sadly nonworking) version of the parser, created all of the "Unbundler" classes that handle the implementation 
of the control commands, and wrote the classes and buttons necessary to write and read files. Maya and Jennifer both touched 
all of the classes on front-end over the process of the project. Maya's work dealt largely with storing and then re-accessing 
data related to front-end. Maya was mostly in charge of the Console (text input and history), the tables that displayed the 
user-defined commands, the pop-up windows that displayed a table of all the turtles and their various statuses (id, image, 
pen color, etc), and creating the palettes that listed colors for referencing by index. Each of these elements were in various 
parts of the GUI, so unlike CellSociety, the classes she wrote were collaborations instead of exclusively her own work.
Jennifer largely worked with the actual turtle manipulation, and creating the GUI container elements. She organized buttons, 
styled the GUI with CSS, and handled all of the work with turtles (Drawables) and actually displaying the effects of commands.

### Resources
Julia used some online resources to assist in reading and writing a file 
(<http://www.stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java> and  
<https://txt2re.com/index-java.php3>), which are also cited in the FileReader and FileWriter classes.

Maya used TableViews a lot, and found out how to generally create them using the tutorial
(<http://www.java2s.com/Tutorials/Java/JavaFX/0650__JavaFX_TableView.htm>).

Jennifer mostly looked at the Java documentation for JavaFX and the Java tutorials for how to use various JavaFX elements. In particular she spent a lot of time reading about different types of Panes, how to drag and drop a node using the computer mouse, and how to use CSS with JavaFX.

### The Project
#### Starting the project
Our Main.java class starts this Slogo project. 
#### Testing the project
We used all provided test files to test our project, and we also formulated extreme cases of our own to test 
possible choices a user may make, including using a high level of nested control commands that all need to be evaluated
within each other.
#### Necessary files
In order to support a variety of languages, our project requires the necessary property files (currently Chinese, English,
French, German, Italian, Portuguese, Russian, Spanish, and Urdu) with translations for all of the commands so that a user 
can write commands in any language they wish.
No input files are required to get the project to work- this project functions very well only with direct user 
input! However, the user is invited to upload any properly formatted file, and the interpreter will be able to parse 
and run its commands and store the appropriate functions and variables.

#### Assuptions and decisions to handle vague specs
* MAKE/SET command only sets variables to double values (no strings)

#### Known bugs, crashes, problems
* GUI activation and disactivation of drawables (Turtle Information button) does not work. Was too much of a design problem
finding out how to call/use a TELL command, which requires knowing active statuses of all turtles, when checking/unchecking
a box that is tied to only one individual turtle.
* Turtle information pop-up does not update based on typed commands in real time. A new instance of the window needs to be opened
before that information is updated. This is because these pieces of information are only grabbed at the start of an instance. If 
values are changed while a window is open, it will not update, but data is accurate at time of window launch.
* Turtle movement wrapping around the screen is hardcoded so that if a turtle goes over the edge of the Canvas, the turtle will simply be placed on the opposite edge, no matter how much over the edge the turtle was. Therefore, if the turtle could have wrapped around the entire Canvas 3 times, the turtle will still only appear on the other side of the Canvas and will not draw a trail over the entire Canvas. 
