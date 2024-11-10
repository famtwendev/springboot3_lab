package vn.famtwen.spring.hibernate.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.famtwen.spring.hibernate.dao.SinhVienDAOImpl;
import vn.famtwen.spring.hibernate.entity.SinhVien;

import java.util.List;
import java.util.Scanner;

@Configuration
public class MyConfiguration {
    @Bean
    @Autowired
    public CommandLineRunner getRunner(SinhVienDAOImpl sinhVienDAOimpl)
    {
        return runner -> {
            Scanner scanner = new Scanner(System.in);
            while(true)
            {
                printMenu();
                int choose = scanner.nextInt();
                scanner.nextLine();
                if(choose == 0)
                {
                    System.exit(0);
                }
                else if(choose == 1)
                {
                    //Luu xuong CSDL
                    themSinhVien(sinhVienDAOimpl);
                }else if(choose==2)
                {
                    timSinhVien(sinhVienDAOimpl);
                }else if(choose == 3) {
                    timTatCaSinhVien(sinhVienDAOimpl);
                }else if(choose == 4) {
                    timSinhVienBangTen(sinhVienDAOimpl);
                }else if(choose == 5) {
                    capNhatSinhVienTheoId(sinhVienDAOimpl);
                }else if(choose == 6) {
                    capNhatTemTatCaSinhVien(sinhVienDAOimpl);
                }else if(choose == 7) {
                    xoaSinhVienTheoId(sinhVienDAOimpl);
                }else if(choose == 8) {
                    xoaSinhVienTheoTen(sinhVienDAOimpl);
                }
            }

        };
    }

    public void printMenu()
    {
        System.out.println("======================================================================");
        System.out.println("MENU: \n" +
                "0. Thoát\n"  +
                "1. Thêm sinh viên\n" +
                "2. Tìm sinh viên\n"  +
                "3. In tất cả sinh viên\n"  +
                "4. Tìm kiếm sinh viên\n"  +
                "5. Cập nhật sinh viên dựa trên id\n"  +
                "6. Cập nhật toàn bộ tên của sinh viên\n"  +
                "7. Xóa sinh viên bằng id nhập vào\n"  +
                "8. Xóa sinh viên bằng tên nhập vào\n"  +
                "LỰA CHỌN: " );
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

    public void timTatCaSinhVien(SinhVienDAOImpl sinhVienDAOImpl)
    {
        List<SinhVien> sinhVienList = sinhVienDAOImpl.getAlls();
        sinhVienList.forEach(
                sinhVien -> {
                    System.out.println(sinhVien);
                }
        );
        System.out.println("In tất cả sinh viên thành công!");

    }

    public void timSinhVienBangTen(SinhVienDAOImpl sinhVienDAOImpl)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Để tìm kiếm sinh viên, vui lòng nhập tên:");
        String ten = scanner.nextLine();

        List<SinhVien> sinhVienList = sinhVienDAOImpl.getByTen(ten);
        if(sinhVienList.size()==0)
        {
            System.out.println("Không tìm thấy sinh viên có tên đã nhập!");
        }else {
//            for (SinhVien s:sinhVienList)
//            {
//                System.out.println(s);
//            }
            sinhVienList.forEach(
                    sinhVien -> {
                        System.out.println(sinhVien);
                    }
            );
            System.out.println("Tìm kiếm sinh viên thành công!");
        }
    }


    private void capNhatSinhVienTheoId(SinhVienDAOImpl sinhVienDAOimpl) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Để tìm kiếm sinh viên, vui lòng nhập id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        SinhVien sv = sinhVienDAOimpl.getById(id);
        if(sv==null)
        {
            System.out.println("Không tìm thấy sinh viên!");
        }else {
            System.out.println("Nhập họ đệm:");
            String ho_dem = scanner.nextLine();
            System.out.println("Nhập tên:");
            String ten = scanner.nextLine();
            System.out.println("Nhập email:");
            String email = scanner.nextLine();

            sv.setTen(ten);
            sv.setHoDem(ho_dem);
            sv.setEmail(email);

            sinhVienDAOimpl.update(sv);
            System.out.println("Cập nhật sinh viên thành công!");
        }
    }

    private void capNhatTemTatCaSinhVien(SinhVienDAOImpl sinhVienDAOimpl) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên:");
        String ten = scanner.nextLine();
        int rows = sinhVienDAOimpl.updateAllTen(ten);

        System.out.println("Có "+rows+" dòng dữ liệu bị thay đổi!");
    }

    private void xoaSinhVienTheoId(SinhVienDAOImpl sinhVienDAOimpl) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Để tìm kiếm sinh viên, vui lòng nhập id:");
        Long id = scanner.nextLong();
        sinhVienDAOimpl.delete(id);
        System.out.println("Xóa sinh viên theo id="+ id +" thành công!");
    }

    private void xoaSinhVienTheoTen(SinhVienDAOImpl sinhVienDAOimpl) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên:");
        String ten = scanner.nextLine();
        sinhVienDAOimpl.deleteByTen(ten);
        System.out.println("Xóa sinh viên theo tên:"+ ten +" thành công!");
    }
}
