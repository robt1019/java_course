import java.util.*;
import java.io.*;

class Table{

    private String name;
    private int columnCount;
    private int recordCount;
    private ArrayList<Column> columnArray = new ArrayList<Column>();
    private ArrayList<Record> recordArray = new ArrayList<Record>();
    private File tableFile;

    //Table constructor. Defines name and number of columns. Ensures minimum
    //number of columns have space cleared
    public Table(String name, int column_count){
        this.name = name;
        this.columnCount = columnCount;
        columnArray.ensureCapacity(column_count);
    }

    public String getName(){
        return name;
    }

    //adds new column at specific column position
    public void addColumn(String columnName, int columnPosition){
        if(columnName.contains("$")){
            System.out.println("Error.Your column name contains a $ sign, this is a reserved character.");
            System.exit(1);
        }
        Column newColumn = new Column(columnName, columnPosition);
        try{
            columnArray.add(columnPosition-1, newColumn);           
        }
        catch(Exception e){
            System.out.println("Tried to add table column at invalid location");
            return;
        }            
    }

    //adds new column at specific column position
    public void addColumnFromFile(String columnName, int columnPosition){
        Column newColumn = new Column(columnName, columnPosition);
        try{
            columnArray.add(columnPosition-1, newColumn);           
        }
        catch(Exception e){
            System.out.println("Tried to add table column at invalid location");
            return;
        }            
    }

    public void removeColumn(int columnPosition){
        if(columnPosition == 1){
            System.out.println("Can't change first column, this is the primary key");
        }
        try{
            columnArray.remove(columnPosition-1);            
        }
        catch(Exception e){
            System.out.println("Tried to remove invalid column");
        }
    }

    public void editColumn(String newColumnName, int columnPosition){

        if(columnPosition-1 == 1){
            System.out.println("Can't change first column. This is the primary key");
            return;
        }
        else{
            try{
            columnArray.get(columnPosition-1).renameColumn(newColumnName);
            }
            catch(Exception e){
                System.out.println("Couldn't edit column " + columnPosition +". Are you sure it exists?");
            }

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
            System.out.println();
        }
    }

    //adds new record
    public void addRecord(Record recordToAdd){
        recordArray.add(recordToAdd);
        recordCount++;
    }

    //adds new record
    public void removeRecord(Record recordToRemove){
        if(recordArray.remove(recordToRemove) == true){
            recordCount--;            
        }
        else{
            System.out.println("Couldn't find record to remove. Are you sure it exists?");
        }
    }

    public int getRecordCount(){
        return recordCount;
    }

    //edits a specific field of a specific record
    public void editRecord(Record record, int columnPosition, String newData){
        try{
            Record recordToChange = recordArray.get(recordArray.indexOf(record));
            recordToChange.editField(columnPosition-1, newData);
        }
        catch (Exception e){
            System.out.println("Couldn't edit record at +" + columnPosition + ". Are you sure it exists?");
        }
    }

    //return record at specific row in database
    public Record getRecord(int row){
        Record returnRecord = null;
        try{
            returnRecord = recordArray.get(row-1);
        }
        catch(Exception e){
            System.out.println("Tried to access invalid record");
            return null;
        }
        return returnRecord;
    }

    //creates buffered writer for specific file and returns it. Checks
    //whether file already exists and gets user input on whether to overwrite
    private BufferedWriter makeWriter(File tableFile){

        BufferedWriter buffer = null;

        if(tableFile.exists()){
            if(getOverwriteResponse() == false){
                return null;
            }
        }
        try{
            FileWriter writer = new FileWriter(tableFile);
            buffer = new BufferedWriter(writer);
        }
        catch(IOException e){
            System.out.println("A write error has occured");
            return null;
        }
        return buffer;
    }

    //checks whether user wants to overwrite file or not
    private boolean getOverwriteResponse(){
        Scanner userResponse = new Scanner(System.in);
        boolean validResponse = false;
        System.out.printf("Table file '%s.txt' already exists. Overwrite? yes/no\n", name);
        while(!validResponse){
            String response = userResponse.next();
            if(response.toLowerCase().equals("no")){
                return false;
            }
            if(response.toLowerCase().equals("yes")){
                validResponse = true;
            }
            else{
                System.out.println("Didn't recognise that. Type 'yes' or 'no'");
            }
        }
        return true;
    }

    //saves table to txt file in a specified directory folder. New line per record
    public void save(String directoryName){
        try{
            BufferedWriter buffer = null;
            File newTableFile = new File(directoryName + "/" + name + ".txt");

            buffer = makeWriter(newTableFile);

            if(buffer != null){
                buffer.write(name + " $\n");
                for(Column column : columnArray){
                buffer.write(column.getName() + " ");
                }
                buffer.write(" $\n");
                for(Record record : recordArray){
                    for(Field field : record.getFieldsArray()){
                        buffer.write(field.getName());
                    }
                    buffer.write(" $\n");
                }
                buffer.close();
            }
            tableFile = newTableFile;
        }
        catch(IOException e){
            System.out.println("A write error has occured");
            return;
        }
    }
}