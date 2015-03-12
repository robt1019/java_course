import java.util.ArrayList;

class Record{

	private String name;
	static ArrayList<String> recordKeysArray = new ArrayList<String>();
	private int fieldCount = 0;
	private ArrayList<Field> fieldsArray = new ArrayList<Field>();

	//Record constructor. Number of fields must be specified, as well as first field
	//First field acts as key. Uniqueness is checked;
	public Record(int fieldCount, String keyField){
		if(keyExists(keyField, recordKeysArray)){
			System.out.println("Key exists. Try another name for Record " + keyField);
			System.exit(1);
		}
		else{
		this.name = keyField;
		recordKeysArray.add(keyField);
		addField(keyField, 1);
		this.fieldCount = fieldCount;
		fieldsArray.ensureCapacity(fieldCount);
		}
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
		Field newField = new Field(fieldString);
		try{
			fieldsArray.add(column-1, newField);
		}
		catch(Exception e){
			System.out.println("Tried to access invalid field location");
			System.exit(1);
		}
		fieldCount++;
	}

	//removes field from specific column
	public void removeField(int column){
		fieldsArray.remove(column-1);
		try{
			fieldCount--;
		}
		catch(Exception e){
			System.out.println("Tried to remove invalid field");
			System.exit(1);
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