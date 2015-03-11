import java.util.ArrayList;

class DB{
	public static void main(String[] args) {
		DB program = new DB();
		program.run();
	}

	public void run(){

		// Table testTable = new Table("Dogs", 3);
		// testTable.addColumn("Name", 1);
		// testTable.addColumn("Age", 2);
		// testTable.addColumn("Weight(pounds)", 3);

		// Record testRecord = new Record(5);
		// testRecord.addField("Gromit ", 1);
		// String testRecordField = testRecord.getField(1).getName();
		// System.out.println(testRecordField);

		// testRecord.addField("3 ", 2);
		// testRecordField = testRecord.getField(2).getName();
		// System.out.println(testRecordField);

		// testRecord.addField("45 ", 3);
		// testRecordField = testRecord.getField(3).getName();
		// System.out.println(testRecordField);

		// Record testRecord1 = new Record(5);
		// testRecord1.addField("Nobber ", 1);
		// String testRecord1Field = testRecord1.getField(1).getName();
		// System.out.println(testRecord1Field);

		// testRecord1.addField("5 ", 2);
		// testRecord1Field = testRecord1.getField(2).getName();
		// System.out.println(testRecord1Field);

		// testRecord1.addField("150 ", 3);
		// testRecord1Field = testRecord1.getField(3).getName();
		// System.out.println(testRecord1Field);

		// testTable.addRecord(testRecord);
		// testTable.addRecord(testRecord1);

		// testTable.printTable();

		// testTable.save("tables");

		Database testDatabase = new Database();

		testDatabase.loadDatabase("tables");
		Table testTable = null; 
		if(testDatabase.getTable("Dogs ") != null){
			testTable = testDatabase.getTable("Dogs ");
			testTable.printTable();
		}
		else{
			System.out.println("null table");
		}


		// testDatabase.saveDatabase();

	}
}
