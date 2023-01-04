package de.flightright.visitorcount.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import de.flightright.visitorcount.pojo.Users;

@Service
public class CountService {

	public int readCount(MultipartFile visitorfile) {
		String line = "";
		String splitBy = ",";
		List<Users> usersList = new ArrayList<>();
		try {
			InputStream inputStream = visitorfile.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usersList.size();
	}

	//Alternate method
	public int readCount(File visitorfile) {
		String line = "";
		String splitBy = ",";
		List<Users> usersList = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(visitorfile));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usersList.size();
	}

	public boolean isNullOrEmpty(String data) {
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
