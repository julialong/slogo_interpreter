Refactoring Discussion
===
jbl34, bah37, mm479
 
### Duplication Refactoring

Our biggest instances of code duplication came from repeated string literals that were sprinkled throughout the frontend. The easiest fix we saw was to pull those literals into constants at the top of the file, some of which were public and shared amongst multiple classes, and others of which were private and used by a single class.

Another many instances of duplicated code involved having many instances of lists of colors within several classes. Since several classes had to access a list of colors, the lists were lazily added into each class. Refactored to have all of the colors in one main class, and other classes draw on that single color list for their uses. (Also, the checked provided incorrectly labels a lot of named variables as magic numbers.)

### Checklist Refactoring

We realized that we had a lot of "magic values" in our code. We started tackling these and cut them down by about half, but this is something that we will have to continue to fix in the future. Some honestly are arbitrary numbers, a few involve dividing by 2, which is pretty common
Getting rid of unused libary imports, unused variables. Category "Java compiler warnings"

All of the "too-long" methods that the Flexibility category flagged actually are single-purpose, but we fixed a couple that could be split

### General refactoring

We changed a pretty big issue that was the Console parsing commands, checking for special user-defined commands or making/setting variables. A front-end class doing this was really bad and we left it in for the first sprint, but getting info from backend instead is a lot better.