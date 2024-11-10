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
                }else if(choose==2)
                {
                    timSinhVien(sinhVienDAO);
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

        System.out.println("Thêm sinh viên thành công!");
    }

    public void timSinhVien(SinhVienDAOImpl sinhVienDAOImpl)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Để tìm kiếm sinh viên, vui lòng nhập id:");
        int id = scanner.nextInt();

        SinhVien sv = sinhVienDAOImpl.getById(id);
        if(sv==null)
        {
            System.out.println("Không tìm thấy sinh viên!");
        }else {
            System.out.println(sv);
            System.out.println("Tìm kiếm sinh viên thành công!");
        }
    }
}
