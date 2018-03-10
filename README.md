# slogo

A development environment that helps users write SLogo programs.

Created by Ben Hubsch (bah37), Jennifer Chin (jrc81), Julia Long (jbl34), and Maya Messinger (mm479).

### Roles
Ben and Julia worked mostly on the backend features while Jenny and Maya tackled the frontend. Ben created all of the 
Command objects (both turtle and otherwise), designed the final implementation of the parser, and created the parts of
the Turtle that were necessary for backend implementation *idk what else you did @Ben*. Julia worked on the initial 
(and sadly nonworking) version of the parser, created all of the "Unbundler" classes that handle the implementation 
of the control commands, and wrote the classes and buttons necessary to write and read files. (@Maya and @Jenny I'm 
not sure exactly what you did so fill that in here)

### Resources
Julia used some online resources to assist in reading and writing a file 
(<www.stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java> and  
<https://txt2re.com/index-java.php3>), which are also cited in the FileReader and FileWriter classes.

### The Project
#### Starting the project
Our Main.java class starts this Slogo project. 
#### Testing the project
We used all provided test files to test our project, and we also formulated extreme  cases of our own to test 
possible choices a user may make, including using a high level of nested control commands that all need to be evaluated
within each other.
### Necessary Files
In order to support a variety of languages, our project requires the necessary property files (currently Chinese, English,
French, German, Italian, Portuguese, Russian, Spanish, and Urdu) with translations for all of the commands so that a user 
can write commands in any language they wish.

No input files are required to get the project to work- this project functions very well only with direct user 
input! However, the user is invited to upload any properly formatted file, and the interpreter will be able to parse 
and run its commands and store the appropriate functions and variables.
