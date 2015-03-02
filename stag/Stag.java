import java.util.Scanner;

class Stag{
	public static void main(String[] args){

		Stag game = new Stag();
		game.run_game();
	}	

	private void run_game(){

		boolean game_over = false;

		Player character = new Player();

		Place nothing = new Place();
		Place bike_shop = new Place();
		Place door = new Place();
		Place bikes = new Place();
		Place store_room = new Place();
		Place tool_bench = new Place();
		Place exit = new Place();

		bike_shop.populate_place("bike_shop",
				bike_shop,
				"You're in a creepy abandoned bike shop at night", 
				"A Door", 
				"A murky store room", 
				"A lovely Selection of Bikes", 
				"A well-equipped tool_bench",
				door,
				store_room,
				bikes,
				tool_bench,
				"nothing",
				"Nothing useful",
				nothing,
				true);

		door.populate_place("door",
				door,
				"You are at a door leading to the outside world. It is locked", 
				"Freedom. But you can't get there because the door is locked!",
				"A bike shop",
				"Nothing",
				"Nothing",
				exit,
				bike_shop,
				nothing,
				nothing,
				"nothing",
				"Nothing useful",
				nothing,
				true);

		bikes.populate_place("bikes",
				bikes,
				"You are confronted with a dazzling array of lovely bikes.", 
				"Nothing",
				"Nothing",
				"Nothing",
				"The bike shop",
				nothing,
				nothing,
				nothing,
				bike_shop,
				"nothing",
				"Nothing useful",
				nothing,
				true);

		tool_bench.populate_place("tool_bench",
				tool_bench,
				"You find yourself at an extremely well-equipped tool bench", 
				"Nothing",
				"Nothing",
				"The bike_shop",
				"Nothing",
				nothing,
				nothing,
				bike_shop,
				nothing,
				"crowbar",
				"A hefty looking crowbar",
				exit,
				true);

		store_room.populate_place("store_room",
				store_room,
				"The store_room is small and oppressive. It smells of damp.", 
				"The bike_shop",
				"Nothing",
				"Nothing",
				"Nothing",
				bike_shop,
				nothing,
				nothing,
				nothing,
				"keys",
				"A glittering set of keys",
				nothing,
				true);

		exit.populate_place("exit",
				exit,
				"You pry open the door with the crowbar \nthen head out into the cool stillness night. Free at last",
				"Endless possibilities",
				"A creepy bike shop",
				"More endless possibilities",
				"A kebab shop",
				nothing,
				bike_shop,
				nothing,
				nothing,
				"nothing",
				"Nothing useful",
				nothing,
				false);

		bike_shop.print_location();
		System.out.println("\nWhat do you want to do? Type help if you're stuck\n");
		Place next_action = get_action(bike_shop);
		while(next_action != exit){
			next_action.print_location();
			next_action = get_action(next_action);
		}
		exit.print_location();
	}

	private Place get_action(Place current_place){
		boolean game_over = false;
		String next_action = null;
		Scanner user_input = new Scanner(System.in);
		//Get next move from player
		do{
			next_action = user_input.nextLine();
		} while(!valid_command(next_action));
		return current_place.action(next_action);
	}

	private boolean valid_command (String user_input){
		user_input = user_input.toLowerCase();
		if(user_input.equals("move north")
			|| user_input.equals("move south")
			|| user_input.equals("move west")
			|| user_input.equals("move east")
			|| user_input.equals("pick up keys")
			|| user_input.equals("pick up crowbar")
			|| user_input.equals("help")
			|| user_input.equals("look around")
			)
			return true;
		else{
			System.out.println("\nDidn't recocgnise any of those options. Would you like to 'move' somewhere? Or maybe 'pick up' something?\n");
			return false;
		}
	}
}