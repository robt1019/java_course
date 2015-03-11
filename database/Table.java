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
        System.out.println();
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
            System.exit(1);
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
                buffer.write("$\n");
                for(Record record : recordArray){
                    for(Field field : record.getFieldsArray()){
                        buffer.write(field.getName());
                    }
                    buffer.write("$\n");
                }
                buffer.close();
            }
            tableFile = newTableFile;
        }
        catch(IOException e){
            System.out.println("A write error has occured");
            System.exit(1);
        }
    }
    public void open(File tableFile){

    }
}