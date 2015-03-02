class Map{

	private Player player = new Player();

	Place nothing;
	Place bike_shop;
	Place door;
	Place bike_store;
	Place tool_shed;
	Place store_room;
	Place exit;

	Map(){

		//sets up map. Hard coded for now
		nothing = new Place();
		bike_shop = new Place();

		Item bikes = new Item("bikes", "some bikes");
		Item no_item = new Item("nothing", "nothing");
		Item crowbar = new Item("crowbar", "a crowbar");
		Item nails = new Item("nails", "some nails");
		Item keys = new Item("keys", "some keys");
		Item hand_sanitiser = new Item ("hand_sanitiser", "some hand_sanitiser");
		Item[] bike_shop_contents = {bikes};
		
		door = new Place();
		Item[] door_contents = {no_item};

		bike_store = new Place();
		Item[] bike_store_contents = {no_item};
		
		tool_shed = new Place();
		Item[] tool_shed_contents = {crowbar, nails};
		
		store_room = new Place();
		Item[] store_room_contents = {keys, hand_sanitiser};
		
		exit = new Place();
		Item[] exit_contents = {no_item};

		bike_shop.build_place("bike_shop", "You're in a creepy abandoned bike shop at night time.\n\nNorth is a door,\nWest is a room full of bikes,\neast is a tool shed,\nSouth is  a murky storage cupboard",
			bike_shop,
			door, store_room, tool_shed, bike_store,
			bike_shop_contents);

		door.build_place("door", "There's a door in front of you leading away from this nightmaresque bike shop. It seems to be locked\n\nNorth is freedom! But the door is locked\nWest is nothing\nEast is nothing\nSouth is the bike_shop",
			door,
			exit, bike_shop, nothing, nothing,
			door_contents);

		bike_store.build_place("bike_store", "You find yourself in a bike storage room. It's full of beautiful bikes but you can't appreciate them properly right now\nNorth is nothing, West is the bike shop, East is nothing, South is nothing",
			bike_store,
			nothing, nothing, bike_shop, nothing,
			bike_store_contents);

		tool_shed.build_place("tool_shed", "You're in a extremly well equipped tool shed.\nNorth is nothing\nWest is the bike shop\nEast is nothing\nSouth is nothing",
			tool_shed,
			nothing, nothing, nothing, bike_shop,
			tool_shed_contents);

		tool_shed.set_object_use(door);

		store_room.build_place("store_room",
			"You're in a murky store room. It smells opressive and you want to leave. \nNorth is the bike shop.\nWest is nothing\nEast is nothing\nSouth is nothing",
			store_room,
			bike_shop, nothing, nothing, nothing,
			store_room_contents);

		exit.build_place("exit", "You pry open the door with the crowbar and head out into the cool night air. Freedom at last",
			exit,
			nothing, door, nothing, nothing,
			exit_contents);
		exit.set_object_found_status(false);
	}

	void run(){
		String user_input = null;
		Place current_location = bike_shop.get_location();
		current_location.print_location();

		while(current_location.get_name() != "exit"){
			System.out.println("\nWhat do you want to do?\n");
			user_input = current_location.get_user_action();
			if(current_location.valid_action(user_input)){
				current_location = current_location.next_action(user_input);
				current_location.print_location();
			}
			else{
				System.out.println("Didn't recognise that option.\nDo you want to move somewhere? Or maybe pick something up?\nMaybe you should look around?");
			}
		}
	}
}