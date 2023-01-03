package de.flightright.visitorcount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.flightright.visitorcount.pojo.Users;

public class Test {
	public static void main(String[] args) {
		String line = "";
		String splitBy = ",";
		try {

			if (args.length == 0) {
				System.out.println(
						"Please pass file name as argument.In eclipse select run configuration and choose arguments");
				return;
			}
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			List<Users> usersList = new ArrayList<>();
			line = br.readLine();
			while ((line = br.readLine()) != null) 
			{
				String[] employee = line.split(splitBy);
				if (!isNullOrEmpty(employee[0]) && !isNullOrEmpty(employee[1]) && !isNullOrEmpty(employee[2])) {
					Users users = new Users();
					users.setEmail(employee[0]);
					users.setPhone(employee[1]);
					users.setSource(employee[2]);
					if (!checkDuplicate(usersList, users))
						usersList.add(users);
				}
			}
			System.out.println(usersList.size());
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isNullOrEmpty(String data) {
		if (data == null || data.isEmpty() || data.trim().isEmpty())
			return true;
		else
			return false;
	}

	public static boolean checkDuplicate(List<Users> usersList, Users user) {
		boolean isExist = false;
		for (Users users : usersList) {
			if (users.getEmail().equals(user.getEmail()) && users.getPhone().equals(user.getPhone())) {
				isExist = true;
			}
		}
		return isExist;
	}
}
