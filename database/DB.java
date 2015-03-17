import java.util.ArrayList;

class DB{
	public static void main(String[] args) {
		DB program = new DB();
		program.run();
	}

	public void run(){

		Table testTable = new Table("Dogs", 3);
		testTable.addColumn("Name", 1);
		testTable.addColumn("Age", 2);
		testTable.addColumn("Weight(pounds)", 3);

		Record testRecord = new Record(5);
		testRecord.addField("Gromit", 1);
		String testRecordField = testRecord.getField(1).getName();

		testRecord.addField("3", 2);
		testRecordField = testRecord.getField(2).getName();

		testRecord.addField("45", 3);
		testRecordField = testRecord.getField(3).getName();

		Record testRecord1 = new Record(5);
			
		testRecord1.addField("Nibbler ", 1);
		String testRecord1Field = testRecord1.getField(1).getName();

		testRecord1.addField("5", 2);
		testRecord1Field = testRecord1.getField(2).getName();

		testRecord1.addField("150", 3);
		testRecord1Field = testRecord1.getField(3).getName();

		testTable.addRecord(testRecord);
		testTable.addRecord(testRecord1);

		testTable.printTable();

		testTable.save("tables");

		Database testDatabase2 = new Database();

		// testDatabase2.createDirectory("database2");

		// Table database2Table1 = new Table("Ducks", 5);
		// database2Table1.addColumn("Name", 1);
		// database2Table1.addColumn("Age", 2);
		// database2Table1.addColumn("favouriteColour", 3);
		// database2Table1.addColumn("Father", 4);
		// database2Table1.addColumn("Birthday", 5);

		// Record testDatabase2Record1 = new Record(5);
		// testDatabase2Record1.addField("Geoffrey", 1);
		// testDatabase2Record1.addField("34", 2);
		// testDatabase2Record1.addField("red", 3);
		// testDatabase2Record1.addField("Randalph", 4);
		// testDatabase2Record1.addField("15/08/80", 5);

		// Record testDatabase2Record2 = new Record(5);
		// testDatabase2Record2.addField("Donald", 1);
		// testDatabase2Record2.addField("3", 2);
		// testDatabase2Record2.addField("blue", 3);
		// testDatabase2Record2.addField("Randalph", 4);
		// testDatabase2Record2.addField("15/08/12", 5);

		// Record testDatabase2Record3 = new Record(5);
		// testDatabase2Record3.addField("Daffy", 1);
		// testDatabase2Record3.addField("34", 2);
		// testDatabase2Record3.addField("orange", 3);
		// testDatabase2Record3.addField("Gandalf", 4);
		// testDatabase2Record3.addField("15/08/80", 5);

		// database2Table1.addRecord(testDatabase2Record1);
		// database2Table1.addRecord(testDatabase2Record2);
		// database2Table1.addRecord(testDatabase2Record3);

		// testDatabase2.addTable(database2Table1);

		// testDatabase2.saveDatabase();

		testDatabase2.loadDatabase("database2");
		
		Table database2TestTable = null; 
		if(testDatabase2.getTable("Ducks") != null){
			testTable = testDatabase2.getTable("Ducks");
			testTable.printTable();
		}
		else{
			System.out.println("null table");
		}

		// testDatabase.saveDatabase();

	}
}
