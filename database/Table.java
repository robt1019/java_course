import java.util.ArrayList;

class Table{

	private String name;
 	private int column_count;
 	private ArrayList< Column > column_array;
 	private ArrayList< Record > record_array;

 	//Table constructor. Defines name and number of columns
 	public Table(String table_name){
 		name = table_name;
 		column_count = 0;
 	}

 	//adds new column at specific column position
 	public void addColumn(String column_name, int column_position){
 		Column new_column = new Column(column_name, column_position);
 		try{
	 		column_array.add(column_position-1, new_column); 			
 		}
 		catch(Exception e){
 			System.out.println("Tried to add table column at invalid location");
 			System.exit(1);
 		}
 	}

 	//adds record to specific 
 	public void add_record(Record record_to_add, int row_position){
 		record_array.add(row_position-1, record_to_add);
 	}
}