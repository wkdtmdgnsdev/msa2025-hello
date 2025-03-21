package exam_dos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyDos {
	private static String path = "c:\\";
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		while (true) {
			System.out.print(path + ">");
			String str = scanner.nextLine();
			if ("exit".equals(str)) {
				// 프로그램 종료
				System.exit(0);
			}
			String[] arg = str.split(" ");
			switch (arg[0]) {
			case "cd":
				cd_cmd(arg);
				break;
			case "dir":
				dir_cmd();
				break;
			case "type":
				type_cmd(arg);
				break;
			case "rename":
				rename_cmd(arg);
				break;
			case "copy" :
				copy_cmd(arg);
				break;
			}
		}
	}

	private static void copy_cmd(String[] arg) throws Exception {
		if (arg.length != 3) {
			System.out.println("사용법 : copy file1 file2");
			return;
		}
		File file1 = new File(path + "\\" + arg[1]);
		File file2 = new File(path + "\\" + arg[2]);
		
		if(file1.isDirectory()) {
			copy_dir(file1, file2);
			return;
		}
		
		if(!file1.exists())
			System.out.println(arg[1] + " 해당 파일은 존재하지 않습니다");
		
		if(file2.exists()) {
			System.out.print(file2 + " 파일이 이미 존재합니다. 덮어쓰시겠습니까? (Y/N): ");
			if(scanner.nextLine().equals("N")) {
				System.out.println("파일 복사를 취소했습니다");
				return;
			}
		}
		
		copy_file(file1, file2);
	}

	public static void copy_file(File file1, File file2) throws Exception {
		FileInputStream fis = new FileInputStream(file1);
		FileOutputStream fos = new FileOutputStream(file2);
		byte[] buffer = new byte[4096];
		int byteRead;
		
         while (true) {
        	 byteRead = fis.read(buffer);
        	if(byteRead == -1) {
        		break;
        	}
        	fos.write(buffer, 0 ,byteRead);
         }
         System.out.println("성공적으로 복사되었습니다.");
	}

	private static void copy_dir(File file1, File file2) throws Exception {
		File files[] = file1.listFiles();
		
		if (!file2.exists()) {
			file2.mkdirs();
        }
		
		if(files == null) {
			return;
		}
		
		for(File file : files) {
			File copyFile = new File(file2, file.getName());
			if(file.isDirectory()) {
				copy_dir(file, copyFile);
			} else {
				copy_file(file, copyFile);
			}
		}
		System.out.println("성공 했습니다.");
	}

	private static void rename_cmd(String[] arg) {
		if (arg.length != 3) {
			System.out.println("사용법 : rename file1 file2");
			return;
		}
		File oldFile = new File(path + "\\" + arg[1]);
		File newFile = new File(path + "\\" + arg[2]);
		if(newFile.exists()) {
			System.out.println(arg[2] +"이름이 존재하여 이름을 변경 할 수 없습니다");
		} else if (oldFile.exists()) {
			oldFile.renameTo(newFile);
		} else {
			System.out.println(arg[1] + " 해당 파일은 존재하지 않습니다");
		}
	}

	private static void type_cmd(String[] arg) throws Exception {
		if (arg.length != 2) {
			System.out.println("사용법 : type 파일명");
			return;
		}

		File file = new File(path + "\\" + arg[1]);
		if (file.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (true) {
				String str = br.readLine();
				if (str == null)
					break;
				String[] arr = str.split(",");
				for (int i = 0; i < arr.length; i++) {
					System.out.print(arr[i]);
				}
				System.out.println();
			}

			br.close();
		} else {
			System.out.println(arg[1] + " 해당 파일은 존재하지 않습니다");
		}
	}

	private static void dir_cmd() {
		File temp = new File(path);
		File[] contents = temp.listFiles();
		long total = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		DecimalFormat df = new DecimalFormat("#,###");
		for (File file : contents) {
			System.out.printf("%-25s", sdf.format(new Date(file.lastModified())));
			if (file.isDirectory()) {
				System.out.printf("%-15s%-20s", "<DIR>", file.getName());
			} else {
				System.out.printf("%-15s%-20s", df.format(file.length()), file.getName());
				total += file.length();
			}
			System.out.println();
		}
		System.out.printf("       %d개 파일   %s 바이트 \n", contents.length, df.format(total));

	}

	private static void cd_cmd(String[] arg) {
		if (arg.length != 2) {
			System.out.println("사용법 : cd 이동할 경로");
			return;
		}
		File file = new File(arg[1]);
		if (file.exists() && file.isDirectory()) {
			path = arg[1];
		} else {
			System.out.println(arg[1] + " 해당 폴더는 존재하지 않습니다");
		}
	}
}