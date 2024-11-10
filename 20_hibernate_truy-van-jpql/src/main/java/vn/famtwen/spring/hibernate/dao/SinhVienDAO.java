package vn.famtwen.spring.hibernate.dao;

import vn.famtwen.spring.hibernate.entity.SinhVien;

import java.util.List;

public interface SinhVienDAO {
    public void save(SinhVien sinhVien);

    public SinhVien getById(int id);

    public List<SinhVien> getAlls();

    public List<SinhVien> getByTen(String ten);

}
