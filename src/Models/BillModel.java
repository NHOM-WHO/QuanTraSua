
package Models;

public class BillModel {
    private int id;
    private String nguoi_lap;
    private String ban_an;
    private String loai;
    private String trang_thai;
    private String ngaylap_bill;
    private String ngaythanh_toan;
    private int tong_cong;
    private int da_thanhtoan;
    private int giam_gia;

    public BillModel(
        int _id,
        String _nguoi_lap,
        String _ban_an,
        String _loai,
        String _trang_thai,
        String _ngaylap_bill,
        String _ngaythanh_toan,
        int _tong_cong,
        int _da_thanhtoan,
        int _giam_gia
    ){
        id=_id;
        nguoi_lap=_nguoi_lap;
        ban_an=_ban_an;
        loai=_loai;
        trang_thai=_trang_thai;
        ngaylap_bill=_ngaylap_bill;
        ngaythanh_toan=_ngaythanh_toan;
        tong_cong=_tong_cong;
        da_thanhtoan=_da_thanhtoan;
        giam_gia=_giam_gia;
    }

    public int getId(){return id;}
    public String getNguoiLap(){return nguoi_lap;}
    public String getBanAn(){return ban_an;}
    public String getLoai(){return loai;}
    public String getTrangThai(){return trang_thai;}
    public String getNgayLapBill(){return ngaylap_bill;}
    public String getNgayThanhToan(){return ngaythanh_toan;}
    public int getTongCong(){return tong_cong;}
    public int getThanhToan(){return da_thanhtoan;}
    public int getGiamGia(){return giam_gia;}
}