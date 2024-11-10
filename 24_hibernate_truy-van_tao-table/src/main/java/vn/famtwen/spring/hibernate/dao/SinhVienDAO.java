package vn.famtwen.spring.hibernate.dao;

import vn.famtwen.spring.hibernate.entity.SinhVien;

import java.util.List;

public interface SinhVienDAO {
    public void save(SinhVien sinhVien);

    public SinhVien getById(int id);

    public List<SinhVien> getAlls();

    public List<SinhVien> getByTen(String ten);

    public SinhVien update(SinhVien sinhVien);

    // Ví dụ như thay đổi toàn bộ điểm, giảng viên của lớp thành tên khác ,...
    public int updateAllTen(String ten);

    public void delete(Long id);

    public void deleteByTen(String ten);
}
