# Program Report

### Package/class/interface
#### The "app" package consists of subpackages "state" for state pattern files, "observer" for observer pattern files, "generics" for self made generic file and "factory" for factory pattern files
#### - TownSim.java will be the main class to run the program 
#### - Any other file not in the sub packages are not to do with patterns

### Factory Pattern
#### In the file TownRailManager, the method carryOutMessage() creates a Town instance on line 131 and Railway instance on line 147. Instead of being instanciated in this class, a factory method has been made to create either object based on the message given. The file ElementFactory contains the logic carrying the decision out. RailwayFactory and TownsFactory are the concrete classes for the factory pattern both implementing the interface Element. This decouples the program and allows for better testing as you are isolating the logic and creation of the objects from the class.

### State Pattern
#### In the file RailState, it is an interface that defines the state specific methods that need to be performed depending on what stage the Railway instance is in. CompletedDoubleTrack, CompletedSingleTrack, ConstructingSingleTrack and ConstructingdoubleTrack are all concrete implementations of RailState. CompletedDoubleTrack and CompletedSingleTrack does not implement countRailConstructionDays as they are already completed. The transportGoodsHandler for CompletedDoubleTrack should move goods in masses of 200 as there are 2 rails doing 100 each. The CompletedSingleTrack should move only 100 as there is only 1 rail. The ConstructingDoubleRail should still be able to move 100 goods as it still operates the one rail while it builds the other. The Railway should have an attribute of type RailState that one of these instances of these concrete classes can be assigned to. The state specific methods can be called with the precedence of attribute state to carry out the correct state specific method.

### Generics
#### The generics implementation can be utilised by the factory pattern to create more than 1 type of object without needing a seperate factory for Town and Railways whilst ensuring type safety. Town and Rail are both objects of type <T>.


### Observer Pattern
#### The interface class TownRailObserver defines the observer specific method that reacts differently to the change in state. OutputtoFile is the only implementation class i could come up with. It constantly updates an outout file containing information about the towns and rails that exist. It only updates this information if it changes otherwise it wont overwrite the file. The notifier for the observer pattern is called in the method carryOutMessage. If a new rail is constructed, duplicate rail is constructed or new town is founded then this will be updated in the file. More observers can be added and they will all be called at the same time. 

