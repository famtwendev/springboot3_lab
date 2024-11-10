package vn.famtwen.spring.__RestController_Postman.rest;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.famtwen.spring.__RestController_Postman.entity.ErrorResponse;
import vn.famtwen.spring.__RestController_Postman.entity.SinhVien;
import vn.famtwen.spring.__RestController_Postman.exception.SinhVienException;

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
        SinhVien result = null;
        for (SinhVien sv: danhSach)
        {
            if(sv.getId() == id)
            return sv;
        }

        if(result == null)
        {
            throw new SinhVienException("Không tìm thấy sinh viên với index: " + id);
        }
        return result;
    }

    @GetMapping("/index/{index}")
    public SinhVien getSinhVien2( @PathVariable int index )
    {
        SinhVien sinhVien = null;
        try {
            sinhVien = danhSach.get(index);
        }catch (IndexOutOfBoundsException e)
        {
            throw new SinhVienException("Không tìm thấy sinh viên với id: " + index);
        }
        return sinhVien;
    }
}