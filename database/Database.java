import java.util.*;
import java.nio.file.*;
import java.io.*;

class Database{

	private ArrayList<Table> tableArray = new ArrayList<Table>();
	private String directoryName;

	public String getName(){
		return directoryName;
	}

	public void addTable(Table tableToAdd){
		try{
			tableArray.add(tableToAdd);
		}
		catch(Exception e){
			System.out.println("tried to add invalid table");
			System.exit(1);
		}
	}

	public void addTableFromFile(Path tableFile){
		try{
			Scanner file = new Scanner(tableFile);
			int columnCount = getColumnCount(tableFile);
			Table fileTable = new Table(file.next(), columnCount);
			int columnIndex = 1;
			String nextWord = "";
			file.next();

			while(file.hasNext() && !nextWord.equals("$")){
				nextWord = file.next();
				fileTable.addColumnFromFile(nextWord, columnIndex);
				columnIndex++;
			}
			
			int rowCount = getRowCount(tableFile);
			
			for(int i=0; i<rowCount; i++){
				nextWord = "";
				Record fileRecord = new Record(columnCount);
				columnIndex = 1;
				while(file.hasNext() && !nextWord.equals("$")){
					nextWord = file.next();
					fileRecord.addField(nextWord, columnIndex);
					columnIndex++;
				}
				fileTable.addRecord(fileRecord);
			}
			tableArray.add(fileTable);
		}
		catch(Exception e){
			System.out.println("Couldn't load tableFile " + tableFile);
			System.out.println("Are you sure it's formatted correctly?");
			System.exit(1);
		}
	}

	private int getColumnCount(Path tableFile){
		int columnCount = 1;
		String nextWord = "";
		try{
			Scanner file = new Scanner(tableFile);
			while(file.hasNext() && !nextWord.equals("$")){
				nextWord = file.next();
				columnCount++;
			}
			file.close();
		}
		catch(Exception e){
			System.out.println("Error getting columns from file " + tableFile);
			System.out.println("Is file properly formatted?");
			System.exit(1);
		}
		return columnCount;
	}

	private int getRowCount(Path tableFile){
		//'-2' is to take account of the first two rows which are assigned to name/column headings
		int rowCount = -2;
		String nextLine = null;
		try{
			Scanner file = new Scanner(tableFile);
			while(file.hasNextLine()){
				nextLine = file.nextLine();
				rowCount++;
			}
			file.close();
		}
		catch(Exception e){
			System.out.println("Error getting rows from file " + tableFile);
			System.out.println("Is file properly formatted?");
			System.exit(1);
		}
		return rowCount;

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

	public Table getTable(String tableName){
		Table tableToFind = null;
		for(int i=0; i<tableArray.size(); i++){
			tableToFind = tableArray.get(i);
			if(tableToFind.getName().equals(tableName)){
				return tableToFind;
			}
		}
		System.out.println("Couldn't find table " + tableName + ". Are you sure it exists?");
		return tableToFind;
	}

	public void createDirectory(String directoryName){
		Path dir = Paths.get(directoryName);
		this.directoryName = directoryName;
		try{
			Files.createDirectory(dir);	
		}
		catch(Exception e){
			System.out.println("Couldn't create directory");
			System.out.println("Does the directory already exist?");
			System.out.println("If so try loading it first to edit entries");
			System.exit(1);
		}	
	}

	public void loadDatabase(String databaseDirectory){
		Path dir = Paths.get(databaseDirectory);
		directoryName = databaseDirectory;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
			for (Path file: stream){
				addTableFromFile(file);
			}
		}
		catch(Exception e){
			System.out.println("Couldn't open " + databaseDirectory + " database");
			System.out.println("Are you sure the database exists?");
			System.exit(1);
		}
	}

	public void saveDatabase(){
		for(Table table : tableArray){
			table.save(directoryName);
		}
	}
}