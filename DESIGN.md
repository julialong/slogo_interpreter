For the backend, input is passed into the Engine class from the frontend which then delegates the input string to the `Parser`. The `Parser` delegates the input string to the `Sanitizer`, which formats the input string in a desirable way by putting a single whitespace between significant characters, while leaving lists as singular, concatenated strings. (Sidenote: we chose to leave lists as one large string because it made it more straightforward to consider lists as arguments. For example,`if` is a `Command` that take two arguments: the first is an `expr` and the second is a list of commands, `[ command(s) ]`. We felt this was the most straightforward way to pass lists as arguments, which our design required.) Once the Parser has the input formatted in the proper way, it traverses each of the "trees" nested within a given input string, creating and executing nodes as it parses the input. The "tree" is simulated with recursion inside of `traverse()`. The `CommandFactory` delegates instantiation of `Commands` to specific Factory objects that recognize a given command. The `CommandFactory` ultimately returns an `Iterable<Command>` that the Parser can then iterate over and operate on. The final result is returned to Engine where it is ultimately passed to the frontend.

The highest-level class on the frontend is `Visualizer`. It accepts results and exposes an API that is convenient for the backend to make use of given specific commands like `to`, `make` or `set`, `setbackground`, and `setpalette`. On valid commands, `Visualizer` delegates to `Canvas` the responsibility of updating the `Drawable` objects on-screen and tells the `Console` what to print. The UI components like `HelpBox`, `SideBar`, and `Toolbar` all have some kind of action that is triggered on them whenever the user interacts with those components.

There is also the `Turtle` class which implements two different interfaces, one for the frontend and one for the backend. As I've alluded to previously, the type that the frontend holds to interact with the `Turtle` is called a `Drawable`. It exposes methods used in displaying the Turtle on-screen. The interface exposed to the backend is called the `Updatable`, which the `Command` objects use to update the `Turtle`'s state on an executed `Command`, if necessary. This means that each of the "ends" only has access to the methods that we want them to use.

Adding a `Command` to the language is relatively straightforward. Depending on what kind of command it is, you may want it to extend a specific kind of command (like `Boolean`, if it's a command that deals with booleans). Regardless, if it's a command that has some effect on the `Turtle`, you'll want the new `Command` to extend `UpdatableCommand`, which is an abstract class that requires the implementation of one method:

```java
protected double calcValues(Updatable updatable, List<Double> args) {
	// operate on Updatable
}
```

If it's a new `Command` that has no effect on the `Turtle` (i.e., in the same vein as the math commands or boolean commands), you'll want that command to extend `NonUpdatableCommand`, which is an abstract class that, again, requires the implementation of one method:

```java
protected double calcValue(List<String> args) {
	// calculate result
}
```

The `Command` hierarchy has been carefully designed with the addition fo new `Command` objects in mind, and this single method simple implementation results from that.

Additionally, you'll have to add the new `Command` name to Command.properties and specify the absolute file path, and you'll also have to specify the kind of factory that should create it. Depending on the kind of `Command` you've created, you may have to add a new if statement to successfully create the new `Command` (this isn't true for the `UpdatableFactory` and `NonUpdatableFactory` commands, which use reflection to instantiate new `Command` objects).