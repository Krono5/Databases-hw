package tableCreate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * author: Tyler Storr
 */

import assignment2.SpaceConnector;

public class CreateTables {
	private static SpaceConnector con = new SpaceConnector();
	private static Connection dbConnection = con.getConnection();

	public static void main(String[] args) {
		try {

			System.out.println(dbConnection.getMetaData());
		} catch (Exception e) {
			System.out.println("Oops something goofed!");
			e.printStackTrace();
		}

		
		createPlayerTable();
		createPlanetTable();
		createAdministratorTable();
		createCartelTable();
		createCurrentResearchTable();
		createFleetTable();
		createManagesTable();
		createMessagesTable();
		createOrdersTable();
		createShipTable();
		
		try {
			dbConnection.close();
			System.out.println("DB Closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void createAdministratorTable() {
		String command = new String("CREATE TABLE `ADMINISTRATOR` (\r\n" + "  `Username` varchar(24) NOT NULL,\r\n"
				+ "  `Password` varchar(48) NOT NULL,\r\n" + "  PRIMARY KEY (`Username`)\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		String tableName = new String("Administrator");
		executeQuery(command, tableName);
	}

	private static void createCartelTable() {
		String command = new String("CREATE TABLE `CARTEL` (\r\n" + "  `Name` varchar(24) NOT NULL,\r\n"
				+ "  `RawRessources` int(11) DEFAULT NULL,\r\n" + "  `MessageBoard` varchar(1000) DEFAULT NULL,\r\n"
				+ "  `Private_Chat` varchar(1000) DEFAULT NULL,\r\n"
				+ "  `Leader_Username` varchar(24) DEFAULT NULL,\r\n" + "  PRIMARY KEY (`Name`),\r\n"
				+ "  KEY `Leader_Username` (`Leader_Username`),\r\n"
				+ "  CONSTRAINT `CARTEL_ibfk_1` FOREIGN KEY (`Leader_Username`) REFERENCES `PLAYER` (`Username`) ON DELETE SET NULL ON UPDATE CASCADE\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		String tableName = new String("Cartel");
		executeQuery(command, tableName);
	}

	private static void createCurrentResearchTable() {
		String command = new String("CREATE TABLE `CURRENT_RESEARCH` (\r\n" + "  `Planet_ID` char(8) NOT NULL,\r\n"
				+ "  `Research_Type` varchar(24) NOT NULL,\r\n" + "  `Research_Progress` int(11) NOT NULL,\r\n"
				+ "  PRIMARY KEY (`Planet_ID`,`Research_Type`,`Research_Progress`),\r\n"
				+ "  CONSTRAINT `CURRENT_RESEARCH_ibfk_1` FOREIGN KEY (`Planet_ID`) REFERENCES `PLANET` (`Planet_ID`) ON UPDATE CASCADE\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		String tableName = new String("CurrentResearch");
		executeQuery(command, tableName);
	}

	private static void createFleetTable() {
		String command = new String("CREATE TABLE `FLEET` (\r\n" + "  `Fleet_ID` char(8) NOT NULL,\r\n"
				+ "  `P_Username` varchar(24) NOT NULL,\r\n" + "  `X_Position` decimal(3,0) NOT NULL,\r\n"
				+ "  `Y_Position` decimal(3,0) NOT NULL,\r\n" + "  `Z_Position` decimal(3,0) NOT NULL,\r\n"
				+ "  PRIMARY KEY (`Fleet_ID`),\r\n" + "  KEY `P_Username` (`P_Username`),\r\n"
				+ "  CONSTRAINT `FLEET_ibfk_1` FOREIGN KEY (`P_Username`) REFERENCES `PLAYER` (`Username`) ON UPDATE CASCADE\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		String tableName = new String("Fleet");
		executeQuery(command, tableName);
	}

	private static void createManagesTable() {
		String command = new String("CREATE TABLE `MANAGES` (\r\n" + "  `A_Username` varchar(24) NOT NULL,\r\n"
				+ "  `P_Username` varchar(24) NOT NULL,\r\n" + "  PRIMARY KEY (`A_Username`,`P_Username`),\r\n"
				+ "  KEY `P_Username` (`P_Username`),\r\n"
				+ "  CONSTRAINT `MANAGES_ibfk_1` FOREIGN KEY (`A_Username`) REFERENCES `ADMINISTRATOR` (`Username`) ON UPDATE CASCADE,\r\n"
				+ "  CONSTRAINT `MANAGES_ibfk_2` FOREIGN KEY (`P_Username`) REFERENCES `PLAYER` (`Username`) ON UPDATE CASCADE\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		String tableName = new String("Manages");
		executeQuery(command, tableName);
	}

	private static void createMessagesTable() {
		String command = new String("CREATE TABLE `MESSAGES` (\r\n" + "  `P_Username` varchar(24) NOT NULL,\r\n"
				+ "  `Message` varchar(255) NOT NULL,\r\n" + "  PRIMARY KEY (`P_Username`,`Message`),\r\n"
				+ "  CONSTRAINT `MESSAGES_ibfk_1` FOREIGN KEY (`P_Username`) REFERENCES `PLAYER` (`Username`) ON UPDATE CASCADE,\r\n"
				+ "  CONSTRAINT `MSG_USNM_FK` FOREIGN KEY (`P_Username`) REFERENCES `PLAYER` (`Username`) ON UPDATE CASCADE\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		String tableName = new String("Messages");
		executeQuery(command, tableName);
	}

	private static void createOrdersTable() {
		String command = new String("CREATE TABLE `ORDERS` (\r\n" + "  `Fleet_ID` varchar(24) NOT NULL,\r\n"
				+ "  `Move_Location` char(9) NOT NULL,\r\n" + "  `Load_Resources` int(4) NOT NULL,\r\n"
				+ "  PRIMARY KEY (`Fleet_ID`,`Move_Location`,`Load_Resources`),\r\n"
				+ "  CONSTRAINT `ORDERS_ibfk_1` FOREIGN KEY (`Fleet_ID`) REFERENCES `FLEET` (`Fleet_ID`) ON UPDATE CASCADE\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		String tableName = new String("Orders");
		executeQuery(command, tableName);
	}

	private static void createPlanetTable() {
		String command = new String("CREATE TABLE `PLANET` (\r\n" + "  `P_Username` varchar(24) NOT NULL,\r\n"
				+ "  `Planet_ID` char(8) NOT NULL,\r\n" + "  `Raw_resources` int(11) DEFAULT NULL,\r\n"
				+ "  `Base_Max_Resources` int(11) DEFAULT NULL,\r\n"
				+ "  `Total_Max_Resources` int(11) DEFAULT NULL,\r\n" + "  `Bauble` int(11) DEFAULT NULL,\r\n"
				+ "  `Factory_Technology` int(11) DEFAULT NULL,\r\n" + "  `Hull_Technology` int(11) NOT NULL,\r\n"
				+ "  `Mining_Technology` int(11) NOT NULL,\r\n" + "  `Engine_Technology` int(11) NOT NULL,\r\n"
				+ "  `Weapon_Technology` int(11) NOT NULL,\r\n" + "  `Num_Factories` int(11) DEFAULT NULL,\r\n"
				+ "  `Factory_init_cost` int(11) DEFAULT NULL,\r\n" + "  `Factory_maint_cost` int(11) DEFAULT NULL,\r\n"
				+ "  `Num_Mines` int(11) DEFAULT NULL,\r\n" + "  `Mine_init_cost` int(11) DEFAULT NULL,\r\n"
				+ "  `Mine_maint_cost` int(11) DEFAULT NULL,\r\n" + "  `Num_Shipyards` int(11) DEFAULT NULL,\r\n"
				+ "  `Shipyard_init_cost` int(11) DEFAULT NULL,\r\n"
				+ "  `Shipyard_maint_cost` int(11) DEFAULT NULL,\r\n" + "  `Num_Research` int(11) DEFAULT NULL,\r\n"
				+ "  `Research_init_cost` int(11) DEFAULT NULL,\r\n"
				+ "  `Research_maint_cost` int(11) DEFAULT NULL,\r\n" + "  `Cargo_cost` int(11) DEFAULT NULL,\r\n"
				+ "  `Crusier_cost` int(11) DEFAULT NULL,\r\n" + "  `Cargo_count` int(11) DEFAULT NULL,\r\n"
				+ "  `Crusier_count` int(11) DEFAULT NULL,\r\n" + "  PRIMARY KEY (`Planet_ID`),\r\n"
				+ "  KEY `P_Username` (`P_Username`),\r\n"
				+ "  CONSTRAINT `PLANET_ibfk_1` FOREIGN KEY (`P_Username`) REFERENCES `PLAYER` (`Username`)\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		String tableName = new String("Planet");
		executeQuery(command, tableName);
	}

	private static void createPlayerTable() {
		String command = new String("CREATE TABLE `PLAYER` (\r\n" + "  `Username` varchar(24) NOT NULL,\r\n"
				+ "  `Password` varchar(48) NOT NULL,\r\n" + "  `Money` int(11) DEFAULT NULL,\r\n"
				+ "  `IsBanned` tinyint(1) DEFAULT NULL,\r\n" + "  `CName` varchar(24) DEFAULT NULL,\r\n"
				+ "  PRIMARY KEY (`Username`)\r\n" + ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		String tableName = new String("Player");
		executeQuery(command, tableName);
	}

	private static void createShipTable() {
		String command = new String("CREATE TABLE `SHIP` (\r\n" + "  `Ship_ID` char(8) NOT NULL,\r\n"
				+ "  `Raw_Resources` int(11) DEFAULT NULL,\r\n" + "  `Cargo_Capacity` int(11) NOT NULL,\r\n"
				+ "  `Weapon_Capacity` int(11) NOT NULL,\r\n" + "  `Current_Health` int(11) NOT NULL,\r\n"
				+ "  `Planet_ID` char(8) DEFAULT NULL,\r\n" + "  `Fleet_ID` char(8) NOT NULL,\r\n"
				+ "  PRIMARY KEY (`Ship_ID`,`Fleet_ID`),\r\n" + "  KEY `Fleet_ID` (`Fleet_ID`),\r\n"
				+ "  CONSTRAINT `SHIP_ibfk_1` FOREIGN KEY (`Fleet_ID`) REFERENCES `FLEET` (`Fleet_ID`) ON UPDATE CASCADE\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		String tableName = new String("Ship");
		executeQuery(command, tableName);
	}

	private static void executeQuery(String command, String tableName) {
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();
			try {
				stmt.execute(command);
			} catch (Exception e) {
				System.err.println("Fail create of " + tableName + " table");
				//e.printStackTrace();
			}

			System.out.println("Successful create of " + tableName + " table");

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
