package vn.famtwen.spring.hibernate.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.famtwen.spring.hibernate.dao.SinhVienDAOImpl;
import vn.famtwen.spring.hibernate.entity.SinhVien;

import java.util.Scanner;

@Configuration
public class MyConfiguration {
    @Bean
    @Autowired
    public CommandLineRunner getRunner(SinhVienDAOImpl sinhVienDAO)
    {
        return runner -> {
            Scanner scanner = new Scanner(System.in);
            while(true)
            {
                printMenu();
                int choose = scanner.nextInt();
                scanner.nextLine();
                if(choose == 1)
                {
                    //Luu xuong CSDL
                    themSinhVien(sinhVienDAO);
                    System.out.println("THÊM THÀNH CÔNG!");
                }
            }

        };
    }

    public void printMenu()
    {
        System.out.println("======================================================================");
        System.out.println("MENU: \n" +
                           "1. Thêm sinh viên\n" +
                           "2. Tìm sinh viên\n"  +
                           "3. Xóa sinh viên\n"  +
                           "LỰA CHỌN: \n" );
    }

    public void themSinhVien(SinhVienDAOImpl sinhVienDAOImpl)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập họ đệm:");
        String ho_dem = scanner.nextLine();
        System.out.println("Nhập tên:");
        String ten = scanner.nextLine();
        System.out.println("Nhập email:");
        String email = scanner.nextLine();

        SinhVien sv = new SinhVien(ho_dem,ten,email);

        sinhVienDAOImpl.save(sv);
    }
}
