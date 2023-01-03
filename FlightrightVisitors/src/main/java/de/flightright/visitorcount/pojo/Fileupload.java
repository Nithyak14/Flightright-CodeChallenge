package de.flightright.visitorcount.pojo;

import org.springframework.web.multipart.MultipartFile;

public class Fileupload {
private MultipartFile visitorfile;

public MultipartFile getVisitorfile() {
	return visitorfile;
}

public void setVisitorfile(MultipartFile visitorfile) {
	this.visitorfile = visitorfile;
}



}
