import java.util.*;

class Place{
	private String name;
	private String description;
	private Place location;
	private Place north;
	private Place south;
	private Place east;
	private Place west;
	private ArrayList<Item> contents;
	private Place object_use;
	private boolean key_object_found;

	public void build_place(String place_name, String place_description,
		Place current_location,
		Place north_location, Place south_location,
		Place east_location, Place west_location,
		Item[] place_contents)
	{
		name = place_name;
		description = place_description;
		location = current_location;
		north = north_location;
		south = south_location;
		east = east_location;
		west = west_location;
		contents = new ArrayList<Item>();
		populate_contents(place_contents);
	}

	public void set_object_found_status(boolean status){
		key_object_found = status;
	}

	public void set_object_use(Place object_use_location){
		object_use = object_use_location;
	}

	private void populate_contents(Item[] place_contents){
		// System.out.println(place_contents.length);
		for(int i=0; i<place_contents.length; i++){
			contents.add(place_contents[i]);
		}
	}

	public String get_user_action(){
		String user_input = null;
		Scanner input = new Scanner(System.in);
		user_input = input.nextLine();
		return user_input;
	}

	public boolean valid_action(String action){
		action = action.toLowerCase();
		if(action.equals("move north")
			|| action.equals("move south")
			|| action.equals("move east")
			|| action.equals("move west")
			|| action.equals("pick up crowbar")
			|| action.equals("pick up keys")
			|| action.equals("pick up bikes")
			|| action.equals("pick up nails")
			|| action.equals("pick up hand sanitiser")
			|| action.equals("look around")
			){
			return true;
		}
		else{
			return false;
		}
	}

	public Place next_action(String action){
		Place next_place = null;
		String[]actions = action.split(" ");
		action = action.toLowerCase();
		if(action.equals("move north")){
			next_place = north;
		}
		if(action.equals("move south")){
			next_place = south;
		}
		if(action.equals("move east")){
			next_place = east;
		}
		if(action.equals("move west")){
			next_place = west;
		}
		if(action.equals("pick up" + actions[2]) && in_location(actions[2])){
			check_for_item("crowbar");
			return location;
		}
		if(action.equals("look around")){
			print_contents();
			return location;
		}
		if(next_place.description == null){
			System.out.println("\nYou can't go there\n");
			next_place = location;
		}
		if(next_place.name.equals("exit")){
			if(!key_object_found){
				System.out.println("\nYou can't go there\n");
				next_place = location;
			}
		}
		return next_place;
	}

	private boolean in_location(String item){
		if(contents.contains(item)){
			return true;
		}
		else{
			return false;
		}
	}

	private void check_for_item(String item){
		if(contents.contains(item)){
			remove_item(item);
			object_use.set_object_found_status(true);
			System.out.printf("\nYou pick up %s\n", item);
		}
		else{
			System.out.printf("Can't find %s", item);
		}
	}

	public Place get_location(){
		return location;
	}

	public String get_name(){
		return name;
	}

	public void print_location(){
		System.out.printf("\n%s\n", description);
	}

	public void print_contents(){
		Item contents_array[] = new Item[contents.size()];
		for(int i=0; i<contents.size(); i++){
			contents_array[i] = contents.get(i);
			System.out.printf("\nYou look around and find %s\n", contents_array[i].description);
		}
	}

	public ArrayList<Item> get_contents(){
		return contents;
	}

	public void remove_item(String item){
		contents.remove(item);
	}
}