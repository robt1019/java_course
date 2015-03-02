class Place{
	private String name;
	private Place location;
	private String description;
	private String north_description;
	private String south_description;
	private String west_description;
	private String east_description;
	private Place north;
	private Place south;
	private Place west;
	private Place east;
	private String next_move;
	private String object_name;
	private String object_description;
	private boolean object_present;
	private Place object_use_place;
	private boolean passable;
	
	//Place constructor
	public void populate_place(String place_name, Place current_location, String place_description, 
		String location_north_description, String location_south_description, 
		String location_west_description, String location_east_description,
		Place location_north, Place location_south,
		Place location_west, Place location_east,
		String object, String object_description_string, Place object_place,
		boolean passable_status){
		name = place_name;
		location = current_location;
		description = place_description;
		north_description = location_north_description;
		south_description = location_south_description;
		west_description = location_west_description;
		east_description = location_east_description;
		north = location_north;
		south = location_south;
		west = location_west;
		east = location_east;
		object_name = object;
		object_description = object_description_string;
		object_use_place = object_place;
		object_present = false;
		passable = passable_status;
	}

	public void print_help(){
		System.out.println("\nTry typing 'look around'");
	}

	public void print_location(){
		System.out.printf("\n%s\n", description);	
	}

	public void print_surroundings(){
		System.out.printf("\nNorth is %s\n", north_description);
		System.out.printf("South is %s\n", south_description);
		System.out.printf("West is %s\n", west_description);
		System.out.printf("East is %s\n", east_description);
		System.out.printf("There is %s here\n", object_description);
	}

	public Place action (String command){
		command = command.toLowerCase();
		Place next_location = null;
		if(command.equals("help")){
			print_help();
			next_location = location;
		}
		if(command.equals("look around")){
			print_surroundings();
			next_location = location;
		}
		if(command.equals("move north") || command.equals("move south") 
			|| command.equals("move west") || command.equals("move east")){
			if(command.equals("move north")){
			next_move = "north";
			next_location = north;
			}
			if(command.equals("move south")){
				next_move = "south";
				next_location = south;
			}
			if(command.equals("move west")){
				next_move = "west";
				next_location = west;
			}
			if(command.equals("move east")){
				next_move = "east";
				next_location  = east;			
			}
			System.out.printf("\nYou move %s\n", next_move);
		}
		if(command.equals("pick up " + object_name)){
			System.out.printf("\nYou pick up the %s\n", object_name);
			object_description = "Nothing";
			object_use_place.object_present = true;
			object_use_place.passable = true;
			next_location = location;
		}
		if((next_location.description == null || next_location.passable == false)){
			next_location = location;
			System.out.println("Got here");
			System.out.println("\nYou can't move there\n");
		}
		return next_location;
	}
}
		