package ch20.oracle.sec05.app2.dto;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private String bfilename;
	private String bfiledata;
}