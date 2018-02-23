SLogo API Review 
===
Julia Long (jbl34) and Belanie Nagiel (bn51)

## Part 1
#### What about your API/design is intended to be flexible?
Our API is designed to have a lot of flexibility in respect to the Model. Because the Turtle is implementing two different interfaces that the front-end and back-end access, changing the model to a different class that implements both of these interfaces would be simple.
We also have flexibility in creating new commands by having a Commandable interface. 
Belanie's API is flexible in their implementation of the commands as nodes in a tree.

#### How is your API/design encapsulating your implementation decisions?
Our commands are currently encapsulated within themselves, taking in a turtle to turtle to act on. So the actual logic of our Command objects in our current API design is encapsulated.

#### What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
Exceptions could include the user inputting an invalid command format. If a user inputs an invalid command in the correct format, that error would be thrown by the CommandFactory and would have to be thrown back to the Vizualizer from the Parser to display an error message to the use. 

#### Why do you think your API/design is _good_ (also define what your measure of good is)?
I think our API is fairly flexible because of the way we were able to implement interfaces. Our API could be better by becoming more encapsulated. I think flexibility and encapsulation are probably good measures of how _good_ an API is, because that measures its ability to expand and be expanded upon, two important goals of software as mentioned in our readings.

## Part 2

#### How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
We are following the Model View Controller design pattern, which has helped us separate out the components into different sections.

#### What feature/design problem are you most excited to work on?
I am excited to work on the Interpreter as well as working with the commands/CommandFactory. 

#### What feature/design problem are you most worried about working on?
I'm worried about working on the Parser and separating the user input into Commands.

#### Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
* Recognizing different languages 
* Recognizing weird command entries 
* Recognizing nested commands
* Alerting the view that something has changed 
* Determining the validity of a command


