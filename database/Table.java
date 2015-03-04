
import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;

class Table{

	private String name;
 	private int columnCount;
 	private int recordCount;
 	private ArrayList<Column> columnArray = new ArrayList<Column>();
 	private ArrayList<Record> recordArray = new ArrayList<Record>();

 	//Table constructor. Defines name and number of columns. Ensures minimum
 	//number of columns have space cleared
 	public Table(String name, int column_count){
 		this.name = name;
 		this.columnCount = columnCount;
 		columnArray.ensureCapacity(column_count);
	}

 	//adds new column at specific column position
 	public void addColumn(String columnName, int columnPosition){
 		Column newColumn = new Column(columnName, columnPosition);
 		try{
	 		columnArray.add(columnPosition-1, newColumn); 			
 		}
 		catch(Exception e){
 			System.out.println("Tried to add table column at invalid location");
 			System.exit(1);
 		}
 	}

 	//prints table out to console with string formatting to make columns line up
 	public void printTable(){
 		for(Column column : columnArray){
			System.out.printf("%-20s", column.getName());
 		}
 		System.out.println();
 		for(Record record : recordArray){
 			for(Field field : record.getFieldsArray()){
 				System.out.printf("%-20s", field.getName());
 			}
 		}
 	}

 	public int getRecordCount(){
 		return recordCount;
 	}

 	//return record at specific row in database
 	public Record getRecord(int row){
 		Record returnRecord = null;
 		try{
 			returnRecord = recordArray.get(row-1);
 		}
 		catch(Exception e){
 			System.out.println("Tried to access invalid record");
 			System.exit(1);
 		}
 		return returnRecord;
 	}

 	//adds new record
 	public void addRecord(Record recordToAdd){
 		recordArray.add(recordToAdd);
 	}

 	//saves table to txt file in program folder. New line per record
 	public void save(){
 		try{
 			FileWriter newTable = new FileWriter(name + ".txt");
 			BufferedWriter buffer = new BufferedWriter(newTable);

			for(Column column : columnArray){
				buffer.write(column.getName() + " ");
	 		}
	 		buffer.write("\n");
	 		for(Record record : recordArray){
	 			for(Field field : record.getFieldsArray()){
	 				buffer.write(field.getName());
	 			}
	 			buffer.write("$");
	 		}
	 		buffer.close();
 		}
 		catch(IOException e){
 			System.out.println("A write error has occured");
 			System.exit(1);
 		}
 	}
}