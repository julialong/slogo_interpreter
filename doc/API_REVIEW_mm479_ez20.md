Maya Messinger (mm479)
Eddie (ez20)

API review
===

## Part 1
What about your API/design is intended to be flexible?
* Having a controller between model and view, so that they're not passing information between each other directly, fewer dependencies
* Commands
* Multiple controllers that handle different interactions of model and view

How is your API/design encapsulating your implementation decisions?
* MVC splits up different parts of the project
* Use of interfaces and limiting what methods are accessible by other classes

What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
* incorrect command (like a typo) throw exception - alert that says command DNE
	* no commands sent with an incorrect command will run

Why do you think your API/design is good (also define what your measure of good is)?
* Implements MVC
* Encapsulation within classes and separation of model and view
* Use of generals to reducuce dependencies

## Part 2
How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
* MVC
* Command factory

What feature/design problem are you most excited to work on?
* Console was really nice and easy, actually TextArea handled a lot of things I thought I'd have to try to implement (scrolling)

What feature/design problem are you most worried about working on?
* We're considering making a second controller to encapsulate passing commands to the view, which front-end (Jennifer and I) will implement. If we do this, it's going to be a lot of work to handle each individual command, and work on integration to back-end
Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).