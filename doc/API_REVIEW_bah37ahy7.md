SLogo API Review (Ben Hubsch and Andrew Yeung)
======
#### Part 1
1. The ability to add new commands -- it should be relatively easy to add new commands, as long as they have the proper implementation. Even TurtleCommands should have the proper Turtle objects exposed necessary for working on the Turtle.
2. Everything is a "Commandable" object to the outside world. This is abstracted using an interface with a single method inside of it: an `execute()` method that returns a `Result` object. Whoever will end up calling the commands can depend on the abstraction rather than the implementation, so the command implementation is encapsulated nicely for every `Commmand`.
3. Not many exceptions should occur -- the Parser should handle all of the logic dealing with improper commands or bad input. In the worst case scenario if the parsing goes wrong, I have a `NullCommand` object that the `CommandFactory` defaults to if the input key string isn't recognized.
4. I think it's good because the interface is really clean and straightforward. The `Result` class has a `toString()` method that the front-end can rely on for the result of the series of the commands, and otherwise it's really just about calling `execute` in the right place at the right time.

#### Part 2
1. We are using the Command Pattern for executing the commands, meaning that the Commands expose a single method that allows them to be executed. We also are making use of the Factory Pattern to instantiate command objects based on keywords. Lastly, we are (potentially) thinking about using the Singleton Pattern for our engine Interpreter class, which would allow us to have access to Turtles from within Turtle Command objects, without having to pass the turtle instances every time to the execute method even when they aren't needed.
2. I'm most excited about trying to make the API as clean as possible. I don't think we're at a point where our API is totally perfect, but it's a good place to work toward.
3. I'm most worried about the internal (potential ugliness) of the Command structure. It's difficult because different commands need different things to function properly. The "under-the-hood" stuff, I should say.
4. a. The user types "fd 50" and hits enter.
   b. The user types "sum 10 20" and hits enter.
   c. The user makes a typo on a command.
   d. The user types "pu" twice on a row.
   e. The user types "sum 10" and presses enter.