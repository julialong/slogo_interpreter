Ben Hubsch (bah37)
Jennifer Chin (jrc81)
Julia Long (jbl34)
Maya Messinger (mm479)

Cell Society API Critique
===

## Classification

### Not part of API (private/protected)
* public void graphCells(List<Cell> allCells, int simulationType) 
* public Group getGroup() 
* public Sliders getSliderScene()
* public List<Cell> getActiveCells()
* public Scene getScene() 
* public Group getMyGridContainer() 
* public Grid getGrid()
* public Scene getScene() 
* public Slider getSlider()
* public Scene getScene() 
* public BooleanProperty userSelectionReceivedProperty()
    
### External API
#### Simulation
This API handles everything related to the simulation required to be exposed across packages. The vast majority of it — every method but one — are getters or setters. Surely many of the getters are used on the Visualization side to set shapes on the screen and build the UI, while many of setters are called from the configuration package in order to initialize the simulation.
* public int getMyState() 
* public void setMyState(int state) 
* public int getMyRow() 
* public void setMyRow(int r) 
* public int getMyColumn() 
* public void setMyColumn(int c) 
* public Shape getMyShape() 
* public void setMyShape(Shape s)
* public List<Cell> getNeighbors()
* public void setNeighbors(List<Cell> neighbors) 
* public List<Cell> getMyNeighbors() 
* public void setMyNeighbors(List<Cell> neighbors)
* public List<Cell> updateCells(List<Cell> activeCells) 
* public String getNeighborType() 
* public boolean getToroidal() 
* public Grid getGrid() 
* public Group getRoot()

#### Configuration
This API contains the methods that any package should be able to call from this group's code that relates to XML. Because configuration is mainly supposed to not be modified very much by outside packages, this API is rather short. Only methods that call the API are present, getters instead of setters.
* public void save()
* public BooleanProperty getSave() 
* public String getUserSelection() 

#### Visualizaiton
This API contains front end methods that correspond to user input, and that the back end will therefore need to get information from. 
* public void handle(MouseEvent event)
* public int getWidth() 
* public double getWidthInPixels() 
* public int getHeight() 
* public double getHeightInPixels()
* public void start(Stage primaryStage) throws Exception
* public String getNeighborSelection() 
* public boolean getToroidalSelection() 
* public Scene getScene() 
* public BooleanProperty neighborSelectionReceivedProperty() 
* public BooleanProperty toroidalSelectionReceivedProperty()
* public DoubleProperty getMySpeed() 
* public BooleanProperty getPlaying() 
* public BooleanProperty getRestart() 
* public BooleanProperty goHome() 
* public BooleanProperty step() 
* public BooleanProperty getWindow() 
* public void setMyShape(Shape s) 
* public Cell[][] getMyCells() 


### Internal 
#### Simulation
The internal methods for the simulation part of this project deal with setting specific probabilities and states - things that should not be accessible outside of the backend. 
* public void setMyProbability(double p)
* public Triangle copy()
* public void setMySharkCycles(int mSharkCycles) 
* public void setInitialSharkEnergy(int mSharkEnergy) 
* public void setMyfishCycles(int mSharkCycles)
* * public void setNeighbors(List<Cell> neighbors)

#### Config
We assume that this group's internal configuration methods were all private, and therefore not part of the API. 

#### Visualization
The internal methods for the visualization portion mostly dealt with setting up and updating the scene to display the grid for the user. The establishScene() method appears to handle the various selections that the user could make with their mouse, getScene() is a getter for the current scene, update() updates the grid of cells, and newScene creates a new scene. There is no reason for these methods to be visible to the back-end.

* public void establishScene() 
* public void handle(MouseEvent event) 
* public void handle(MouseEvent event) 
* public void handle(MouseEvent event) 
* public void handle(MouseEvent event)
* public void handle(MouseEvent event) 
* public void handle(MouseEvent event) 
* public void handle(MouseEvent event) 
* public void handle(MouseEvent event) 
* public Scene getScene()
* public List<Cell> update()
* public void newScene() 