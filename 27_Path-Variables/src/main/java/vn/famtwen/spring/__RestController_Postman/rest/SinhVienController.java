package vn.famtwen.spring.__RestController_Postman.rest;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.famtwen.spring.__RestController_Postman.entity.SinhVien;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sinhvien")
public class SinhVienController {
    public List<SinhVien> danhSach;

    @PostConstruct
    public void createDanhSach(){
        danhSach = new ArrayList<SinhVien>();
        danhSach.add(new SinhVien(1, "Nguyen Van A", 18, "Cong Nghe Thong Tin", 9));
        danhSach.add(new SinhVien(2, "Le Ngoc B", 20, "Quan tri kinh doanh", 9.5));
        danhSach.add(new SinhVien(3, "Tran Anh C", 22, "Truyen Thong Da Phuong Tien", 8));
        danhSach.add(new SinhVien(4, "Pham Thi F", 19, "Ky Thuat Dien", 7));
        danhSach.add(new SinhVien(5, "Phan Quang D", 21, "Ke Toan", 8.5));
    }

    @GetMapping("/")
    public List<SinhVien> getDanhSach()
    {
        return danhSach;
    }

    @GetMapping("/{id}")
    public SinhVien getSinhVien(@PathVariable int id)
    {
        for (SinhVien sv: danhSach)
        {
            if(sv.getId() == id)
            return sv;
        }
        return null;
    }
    @GetMapping("/index/{index}")
    public SinhVien getSinhVien2(@PathVariable int index)
    {
        SinhVien sinhVien = danhSach.get(index);
        return sinhVien;
    }
}
