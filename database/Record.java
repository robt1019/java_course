import java.util.ArrayList;

class Record{

	private String name;
	static ArrayList<String> recordKeysArray = new ArrayList<String>();
	private int fieldCount = 0;
	private ArrayList<Field> fieldsArray = new ArrayList<Field>();

	//Record constructor. Number of fields must be specified, as well as first field
	//First field acts as key. Uniqueness is checked;
	public Record(int fieldCount){
		this.fieldCount = fieldCount;
		fieldsArray.ensureCapacity(fieldCount);
	}

	private boolean keyExists(String nameToCheck, ArrayList<String> recordNamesArray){
		for(int i=0; i<recordNamesArray.size(); i++){
			if(recordNamesArray.get(i).equals(nameToCheck)){
				return true;
			}
		}
		return false;
	}

	//adds new field to specific column in Record
	public void addField(String fieldString, int column){
		if(column == 1){
			if(keyExists(fieldString, recordKeysArray)){
				System.out.println("key " + fieldString + " is already being used in this table. Please use another one");
				System.exit(1);
			}
			else{
				recordKeysArray.add(fieldString);
			}
		}
		Field newField = new Field(fieldString);
		try{
			fieldsArray.add(column-1, newField);
		}
		catch(Exception e){
			System.out.println("Tried to access invalid field location");
			return;
		}
		fieldCount++;
	}

	//removes field from specific column
	public void removeField(int column){
		try{
			fieldsArray.remove(column-1);
			fieldCount--;
		}
		catch(Exception e){
			System.out.println("Tried to remove invalid field");
			return;
		}
	}

	//changese field at specific column index
	public void editField(int column, String newData){
        if(column-1 == 1){
            System.out.println("Can't change first column. This is the primary key");
            return;
        }
		try{
			Field currentField = fieldsArray.get(column-1);
			currentField.editName(newData);
		}
		catch(Exception e){
			System.out.println("Couldn't edit Field at column " + column + ". Are you sure it exists?");
		}
	}

	public ArrayList<Field> getFieldsArray(){
		return fieldsArray;
	}

	//returns data from field at specific column
	public Field getField(int column){
		return fieldsArray.get(column-1);
	}

	//returns number of fields in Record
	public int getFieldCount(){
		return fieldCount;
	}
}