package com.example.quanlythuvien.utilities.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusAction {
    ACTIVE(0, "Đang hoạt động"),
    INACTIVE(1, "Dừng hoạt động"),
    BOOK_DETAIL_DANGMUON(2, "Sách đang được mượn"),
    BOOK_DETAIL_TRONGKHO(3, "Sách còn tồn tại trong kho"),
    BOOK_DETAIL_HONG(4, "Sách bị rách,hư hỏng"),
    BOOK_DETAIL_THATLAC(5, "Sách bị mất,thất lạc"),
    USER_LOCK(6, "Khóa tài khoản"),
    LOANSLIP_DANGMUON(7, "Đang mượn"),
    LOANSLIP_CHUATRA(8, "Quá hạn nhưng chưa trả hoặc chưa trả đủ"),
    LOANSLIP_DATRA(9, "Đã trả hết"),
    LOANSLIP_TRAQUAHAN(10, "Trả quá hạn"),
    LOANSLIPDETAIL_DANGMUON(11, "Đang mượn"),
    LOANSLIPDETAIL_CHUATRA(12, "Quá hạn chưa trả"),
    LOANSLIPDETAIL_DATRA(13, "Đã trả"),

    LOANSLIPDETAIL_TRAMUON(14,"Trả muộn");

    private int value;
    private String value2;

    public static StatusAction valueOf(int x) {
        for (StatusAction e : values()) {
            if (e.value == x) {
                return e;
            }
        }
        return null;
    }
}
