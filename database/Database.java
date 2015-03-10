import java.util.*;
import java.nio.file.*;
import java.io.*;

class Database{

	private ArrayList<Table> tableArray = new ArrayList<Table>();
	private ArrayList<File> tableFileArray = new ArrayList<File>();

	public void addTable(Table tableToAdd){
		try{
			tableArray.add(tableToAdd);
		}
		catch(Exception e){
			System.out.println("tried to add invalid table");
			System.exit(1);
		}
	}

	public void removeTable(Table tableToRemove){
		try{
			if(tableArray.remove(tableToRemove) == true){
				return;
			}
			else{
				System.out.println("table to remove wasn't found in database");
			}
		}
		catch(Exception e){
			System.out.println("tried to remove invalid table");
			System.exit(1);
		}
	}

	public void loadDatabase(String databaseDirectory){
		Path dir = Paths.get(databaseDirectory);
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
			for (Path file: stream){
				
			}
		}
		catch(Exception e){
			System.out.println("Couldn't open " + databaseDirectory + " database");
			System.exit(1);
		}
	}

	public void saveDatabase(){
		for(Table table : tableArray){
			table.save();
		}
	}
}