package com.sinem.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    INTERNAL_SERVER_ERROR(1000, "Sunucuda bilinmeyen bir hata olustu.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001, "Istek formati hatali", HttpStatus.BAD_REQUEST),
    REGISTER_KULLANICIADI_KAYITLI(1001, "Kullanici adi kayitli", HttpStatus.BAD_REQUEST),
    REGISTER_PASSWORDS_NOT_MATCH(1002, "Girmiş olduğunuz şifreler uyuşmuyor.", HttpStatus.BAD_REQUEST),
    DOLOGIN_INVALID_USERNAME_PASSWORD(1005, "Kullanici adi ya da şifre hatalı", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_FOUND(1006, "Böyle bir müşteri bulunamadı.", HttpStatus.BAD_REQUEST),
    USER_NOT_ACTIVATED(1007, "Kullanıcı aktif edilmemiş. Login başarısız.", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1008, "Bu ID'ye sahip ürün bulunamadı.", HttpStatus.BAD_REQUEST),
    RESTAURANT_NOT_FOUND(1008, "Bu ID'ye sahip restaurant bulunamadı.", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(1011, "Bu ID'ye sahip sipariş bulunamadı.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_ORDER(1012, "Bu ID'ye sahip müşteri bu siparişte işlem yapamaz.", HttpStatus.BAD_REQUEST),
    INCONSISTENT_ORDER_INFORMATION(1013, "Girilen restaurant ve sipariş bilgileri uyuşmamaktadır.", HttpStatus.BAD_REQUEST),
    CODE_DOES_NOT_MATCH(1014, "Girilen aktivasyon kodu kabul edilmedi. Lütfen tekrar deneyin.", HttpStatus.BAD_REQUEST),
    PRODUCT_AND_RESTAURANT_NOT_FOUND(1008, "Girilen ID'lere sahip ürün ve restaurant bulunamadı.", HttpStatus.BAD_REQUEST),
    CUSTOMER_AND_RESTAURANT_NOT_FOUND(1009, "Girilen ID'lere sahip müşteri ve restaurant bulunamadı.", HttpStatus.BAD_REQUEST),
    ORDER_LOGIN_ERROR(1010, "Sipariş vermek için önce müşteri girişi yapılmalıdır.", HttpStatus.BAD_REQUEST),
    NAME_IS_NULL(1001, "Name alani bos gecilemez", HttpStatus.BAD_REQUEST),
    ID_NOT_FOUND(1001, "Aradığınız id'ye ait kayit bulunamamıştır", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1002, "Geçersiz token", HttpStatus.BAD_REQUEST);
    int code;
    String message;
    HttpStatus httpStatus;
}
