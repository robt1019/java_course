import java.util.ArrayList;

class Record{

	private int field_count = 0;
	private ArrayList< String > fields_array = new ArrayList < String >();

	//adds new field to specific column in Record
	public void addField(String new_field, int column){
		try{
			fields_array.add(column-1, new_field);
		}
		catch(Exception e){
			System.out.println("Tried to access invalid field location");
			System.exit(1);
		}
		field_count++;
	}

	//removes field from specific column
	public void removeField(int column){
		fields_array.remove(column-1);
		try{
			field_count--;
		}
		catch(Exception e){
			System.out.println("Tried to remove invalid field");
			System.exit(1);
		}
	}

	//returns data from field at specific column
	public String getField(int column){
		return fields_array.get(column-1);
	}

	//returns number of fields in Record
	public int getFieldCount(){
		return field_count;
	}
}