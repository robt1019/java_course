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
		testRecord.addField("Gromit ", 1);
		String testRecordField = testRecord.getField(1).getName();
		System.out.println(testRecordField);

		testRecord.addField("3 ", 2);
		testRecordField = testRecord.getField(2).getName();
		System.out.println(testRecordField);

		testRecord.addField("45 ", 3);
		testRecordField = testRecord.getField(3).getName();
		System.out.println(testRecordField);

		testTable.addRecord(testRecord);

		testTable.printTable();

		testTable.save();

		Database testDatabase = new Database();
		testDatabase.addTable(testTable);

		// testDatabase.saveTables();

	}
}
