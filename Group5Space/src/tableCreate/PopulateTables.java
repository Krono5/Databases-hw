package tableCreate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import assignment2.SpaceConnector;

/**
 * @author Tyler Storr
 */

public class PopulateTables {
	private static SpaceConnector con = new SpaceConnector();
	private static Connection dbConnection = con.getConnection();
	private static Statement stmt;
	private static String sql;

	public static void main(String[] args) {
		try {
			stmt = dbConnection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		insertPlayerTable();
		insertPlanetTable();
		insertAdminTable();
		insertCartelTable();
		insertCurrentResTable();
		insertFleetTable();
		insertManagesTable();
		insertMessagesTable();
		insertOrdersTable();
		insertShipTable();

		int[] complete = null;
		try {
			complete = stmt.executeBatch();
		} catch (Exception e) {
			System.err.println("Failed to execute batch");
			e.printStackTrace();
		}
		for (int i = 0; i < complete.length; i++) {
			if (complete[i] == 1) {
				System.out.println("Success on line insert");
			}
			else {
				System.err.println("Fail on line insesrt");
			}
		}

		try {
			dbConnection.close();
			System.out.println("DB Closed");
		} catch (Exception e) {
			System.err.println("DB NOT Closed");
			e.printStackTrace();
		}

	}

	private static void insertAdminTable() {
		try {
			sql = "INSERT INTO `csc371_02`.`ADMINISTRATOR` (`Username`, `Password`) VALUES ('Admin1', 'password1');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`ADMINISTRATOR` (`Username`, `Password`) VALUES ('Admin2', 'password2');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`ADMINISTRATOR` (`Username`, `Password`) VALUES ('Admin3', 'password3');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`ADMINISTRATOR` (`Username`, `Password`) VALUES ('Admin4', 'password4');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`ADMINISTRATOR` (`Username`, `Password`) VALUES ('Admin5', 'password5');\r\n" + 
					"";
			stmt.addBatch(sql);
		} catch (Exception e) {
			System.err.println("Failed to add to batch on admin");
			e.printStackTrace();
		}
	}

	private static void insertCartelTable() {
		try {
			sql = "INSERT INTO `csc371_02`.`CARTEL` (`Name`, `RawRessources`, `MessageBoard`, `Private_Chat`, `Leader_Username`) VALUES ('C1', '12345', 'hello', 'testing', 'Player1');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`CARTEL` (`Name`, `RawRessources`, `MessageBoard`, `Private_Chat`, `Leader_Username`) VALUES ('C2', '123455', 'hello', 'testing', 'Player1');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`CARTEL` (`Name`, `RawRessources`, `MessageBoard`, `Private_Chat`, `Leader_Username`) VALUES ('C3', '1234', 'hello', 'testing', 'Player2');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`CARTEL` (`Name`, `RawRessources`, `MessageBoard`, `Private_Chat`, `Leader_Username`) VALUES ('C4', '123457', 'hello', 'testing', 'Player3');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`CARTEL` (`Name`, `RawRessources`, `MessageBoard`, `Private_Chat`, `Leader_Username`) VALUES ('C5', '123', 'hello', 'testing', 'Player4');\r\n" + 
					"";
			stmt.addBatch(sql);
		} catch (Exception e) {
			System.err.println("Failed to add to batch on cartel");
			e.printStackTrace();
		}
	}

	private static void insertCurrentResTable() {
		try {
			sql = "INSERT INTO `csc371_02`.`CURRENT_RESEARCH` (`Planet_ID`, `Research_Type`, `Research_Progress`) VALUES ('001', 'Mines', '64');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`CURRENT_RESEARCH` (`Planet_ID`, `Research_Type`, `Research_Progress`) VALUES ('002', 'Mines', '34');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`CURRENT_RESEARCH` (`Planet_ID`, `Research_Type`, `Research_Progress`) VALUES ('003', 'Factory', '23');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`CURRENT_RESEARCH` (`Planet_ID`, `Research_Type`, `Research_Progress`) VALUES ('004', 'Shipyard', '13');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`CURRENT_RESEARCH` (`Planet_ID`, `Research_Type`, `Research_Progress`) VALUES ('005', 'Shipyard', '45');\r\n" + 
					"";
			stmt.addBatch(sql);
		} catch (Exception e) {
			System.err.println("Failed to add to batch on current_resources");
			e.printStackTrace();
		}
	}

	private static void insertFleetTable() {
		try {
			sql = "INSERT INTO `csc371_02`.`FLEET` (`Fleet_ID`, `P_Username`, `X_Position`, `Y_Position`, `Z_Position`) VALUES ('001', 'Player1', '1', '1', '1');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`FLEET` (`Fleet_ID`, `P_Username`, `X_Position`, `Y_Position`, `Z_Position`) VALUES ('002', 'Player2', '1', '1', '4');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`FLEET` (`Fleet_ID`, `P_Username`, `X_Position`, `Y_Position`, `Z_Position`) VALUES ('003', 'Player3', '45', '11', '6');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`FLEET` (`Fleet_ID`, `P_Username`, `X_Position`, `Y_Position`, `Z_Position`) VALUES ('004', 'Player4', '34', '1', '23');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`FLEET` (`Fleet_ID`, `P_Username`, `X_Position`, `Y_Position`, `Z_Position`) VALUES ('005', 'Player5', '53', '1', '45');\r\n" + 
					"";
			stmt.addBatch(sql);
		} catch (Exception e) {
			System.err.println("Failed to add to batch on fleet");
			e.printStackTrace();
		}
	}

	private static void insertManagesTable() {
		try {
			sql = "INSERT INTO `csc371_02`.`MANAGES` (`A_Username`, `P_Username`) VALUES ('Admin1', 'player1');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`MANAGES` (`A_Username`, `P_Username`) VALUES ('Admin2', 'player1');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`MANAGES` (`A_Username`, `P_Username`) VALUES ('Admin3', 'player2');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`MANAGES` (`A_Username`, `P_Username`) VALUES ('Admin4', 'player3');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`MANAGES` (`A_Username`, `P_Username`) VALUES ('Admin5', 'player4');\r\n" + 
					"";
			stmt.addBatch(sql);
		} catch (Exception e) {
			System.err.println("Failed to add to batch on manages");
			e.printStackTrace();
		}
	}

	private static void insertMessagesTable() {
		try {
			sql = "INSERT INTO `csc371_02`.`MESSAGES` (`P_Username`, `Message`) VALUES ('player1', 'hello');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`MESSAGES` (`P_Username`, `Message`) VALUES ('player1', 'its me');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`MESSAGES` (`P_Username`, `Message`) VALUES ('player2', 'test');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`MESSAGES` (`P_Username`, `Message`) VALUES ('player3', 'testing');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`MESSAGES` (`P_Username`, `Message`) VALUES ('player4', 'test');\r\n" + 
					"";
			stmt.addBatch(sql);
		} catch (Exception e) {
			System.err.println("Failed to add to batch on messages");
			e.printStackTrace();
		}
	}

	private static void insertOrdersTable() {
		try {
			sql = "INSERT INTO `csc371_02`.`ORDERS` (`Fleet_ID`, `Move_Location`, `Load_Resources`) VALUES ('001', 'abc', '123');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`ORDERS` (`Fleet_ID`, `Move_Location`, `Load_Resources`) VALUES ('002', 'abc', '1234');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`ORDERS` (`Fleet_ID`, `Move_Location`, `Load_Resources`) VALUES ('005', 'asdf', '1234');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`ORDERS` (`Fleet_ID`, `Move_Location`, `Load_Resources`) VALUES ('003', 'cs', '345');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`ORDERS` (`Fleet_ID`, `Move_Location`, `Load_Resources`) VALUES ('004', 'a', '234');\r\n" + 
					"";
			stmt.addBatch(sql);
		} catch (Exception e) {
			System.err.println("Failed to add to batch on orders");
			e.printStackTrace();
		}
	}

	private static void insertPlayerTable() {
		try {
			sql = "INSERT INTO `csc371_02`.`PLAYER` (`Username`, `Password`, `Money`, `IsBanned`, `CName`) VALUES ('Player1', 'password1', '1', '0', 'Cartel1');";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`PLAYER` (`Username`, `Password`, `Money`, `IsBanned`, `CName`) VALUES ('Player2', 'password2', '9999', '0', 'Cartel1');";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`PLAYER` (`Username`, `Password`, `Money`, `IsBanned`, `CName`) VALUES ('Player3', 'password3', '1234', '1', 'Cartel2');";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`PLAYER` (`Username`, `Password`, `Money`, `IsBanned`, `CName`) VALUES ('Player4', 'password4', '1235432', '0', 'Cartel2');";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`PLAYER` (`Username`, `Password`, `Money`, `IsBanned`, `CName`) VALUES ('Player5', 'password5', '122222', '1', 'Cartel3');";
			stmt.addBatch(sql);
		} catch (Exception e) {
			System.err.println("Failed to add to batch on player");
			e.printStackTrace();
		}
	}

	private static void insertPlanetTable() {
		try {
			sql = "INSERT INTO `csc371_02`.`PLANET` (`P_Username`, `Planet_ID`, `Raw_resources`, `Base_Max_Resources`, `Total_Max_Resources`, `Bauble`, `Factory_Technology`, `Hull_Technology`, `Mining_Technology`, `Engine_Technology`, `Weapon_Technology`, `Num_Factories`, `Factory_init_cost`, `Factory_maint_cost`, `Num_Mines`, `Mine_init_cost`, `Mine_maint_cost`, `Num_Shipyards`, `Shipyard_init_cost`, `Shipyard_maint_cost`, `Num_Research`, `Research_init_cost`, `Research_maint_cost`, `Cargo_cost`, `Crusier_cost`, `Cargo_count`, `Crusier_count`) VALUES ('Player1', '001', '12345', '9999', '999999', '122345', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`PLANET` (`P_Username`, `Planet_ID`, `Raw_resources`, `Base_Max_Resources`, `Total_Max_Resources`, `Bauble`, `Factory_Technology`, `Hull_Technology`, `Mining_Technology`, `Engine_Technology`, `Weapon_Technology`, `Num_Factories`, `Factory_init_cost`, `Factory_maint_cost`, `Num_Mines`, `Mine_init_cost`, `Mine_maint_cost`, `Num_Shipyards`, `Shipyard_init_cost`, `Shipyard_maint_cost`, `Num_Research`, `Research_init_cost`, `Research_maint_cost`, `Cargo_cost`, `Crusier_cost`, `Cargo_count`, `Crusier_count`) VALUES ('Player2', '002', '123456', '9999', '999999', '2345', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`PLANET` (`P_Username`, `Planet_ID`, `Raw_resources`, `Base_Max_Resources`, `Total_Max_Resources`, `Bauble`, `Factory_Technology`, `Hull_Technology`, `Mining_Technology`, `Engine_Technology`, `Weapon_Technology`, `Num_Factories`, `Factory_init_cost`, `Factory_maint_cost`, `Num_Mines`, `Mine_init_cost`, `Mine_maint_cost`, `Num_Shipyards`, `Shipyard_init_cost`, `Shipyard_maint_cost`, `Num_Research`, `Research_init_cost`, `Research_maint_cost`, `Cargo_cost`, `Crusier_cost`, `Cargo_count`, `Crusier_count`) VALUES ('Player3', '003', '123457', '9999', '999999', '12245', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`PLANET` (`P_Username`, `Planet_ID`, `Raw_resources`, `Base_Max_Resources`, `Total_Max_Resources`, `Bauble`, `Factory_Technology`, `Hull_Technology`, `Mining_Technology`, `Engine_Technology`, `Weapon_Technology`, `Num_Factories`, `Factory_init_cost`, `Factory_maint_cost`, `Num_Mines`, `Mine_init_cost`, `Mine_maint_cost`, `Num_Shipyards`, `Shipyard_init_cost`, `Shipyard_maint_cost`, `Num_Research`, `Research_init_cost`, `Research_maint_cost`, `Cargo_cost`, `Crusier_cost`, `Cargo_count`, `Crusier_count`) VALUES ('Player4', '004', '123458', '9999', '999999', '12234', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`PLANET` (`P_Username`, `Planet_ID`, `Raw_resources`, `Base_Max_Resources`, `Total_Max_Resources`, `Bauble`, `Factory_Technology`, `Hull_Technology`, `Mining_Technology`, `Engine_Technology`, `Weapon_Technology`, `Num_Factories`, `Factory_init_cost`, `Factory_maint_cost`, `Num_Mines`, `Mine_init_cost`, `Mine_maint_cost`, `Num_Shipyards`, `Shipyard_init_cost`, `Shipyard_maint_cost`, `Num_Research`, `Research_init_cost`, `Research_maint_cost`, `Cargo_cost`, `Crusier_cost`, `Cargo_count`, `Crusier_count`) VALUES ('Player5', '005', '123458', '9999', '999999', '1223453', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');";
			stmt.addBatch(sql);
		} catch (Exception e) {
			System.err.println("Failed to add to batch on planet");
			e.printStackTrace();
		}
	}

	private static void insertShipTable() {
		try {
			sql = "INSERT INTO `csc371_02`.`SHIP` (`Ship_ID`, `Raw_Resources`, `Cargo_Capacity`, `Weapon_Capacity`, `Current_Health`, `Planet_ID`, `Fleet_ID`) VALUES ('001', '1234', '9999', '1', '1', '001', '001');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`SHIP` (`Ship_ID`, `Raw_Resources`, `Cargo_Capacity`, `Weapon_Capacity`, `Current_Health`, `Planet_ID`, `Fleet_ID`) VALUES ('002', '12342', '999999', '1', '1', '001', '001');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`SHIP` (`Ship_ID`, `Raw_Resources`, `Cargo_Capacity`, `Weapon_Capacity`, `Current_Health`, `Planet_ID`, `Fleet_ID`) VALUES ('003', '34234', '999999', '1', '1', '002', '002');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`SHIP` (`Ship_ID`, `Raw_Resources`, `Cargo_Capacity`, `Weapon_Capacity`, `Current_Health`, `Planet_ID`, `Fleet_ID`) VALUES ('004', '534', '999', '1', '1', '003', '003');\r\n" + 
					"";
			stmt.addBatch(sql);
			sql = "INSERT INTO `csc371_02`.`SHIP` (`Ship_ID`, `Raw_Resources`, `Cargo_Capacity`, `Weapon_Capacity`, `Current_Health`, `Planet_ID`, `Fleet_ID`) VALUES ('005', '1', '99999', '1', '1', '004', '004');\r\n" + 
					"";
			stmt.addBatch(sql);
		} catch (Exception e) {
			System.err.println("Failed to add to batch on ship");
			e.printStackTrace();
		}
	}

}
