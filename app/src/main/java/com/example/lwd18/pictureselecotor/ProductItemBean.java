package com.example.lwd18.pictureselecotor;

import android.os.Parcel;
import android.os.Parcelable;
import java.math.BigDecimal;

/**
 * Created by x on 2016/10/17.
 */

public class ProductItemBean implements Parcelable {
    
    private int id;

    private String pname;

    private String pfrom;

    private String pfromId;

    private String fromUrl;

    private String detailUrl;

    private String detailAppUrl;

    private String image;

    private String htmlUrl;

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    private String pfromImage;

    private int type;

    private String typeVal;

    private BigDecimal price;

    private String symbol;

    private String description;

    private String remarkCount;

    private int goodCount;

    private int brandId;

    private int familyId;

    private int categoryId;

    private int amountType;

    private double amountMoney;

    public int getAmountType() {
        return amountType;
    }

    public void setAmountType(int amountType) {
        this.amountType = amountType;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailAppUrl() {
        return detailAppUrl;
    }

    public void setDetailAppUrl(String detailAppUrl) {
        this.detailAppUrl = detailAppUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPfrom() {
        return pfrom;
    }

    public void setPfrom(String pfrom) {
        this.pfrom = pfrom;
    }

    public String getPfromId() {
        return pfromId;
    }

    public void setPfromId(String pfromId) {
        this.pfromId = pfromId;
    }

    public String getPfromImage() {
        return pfromImage;
    }

    public void setPfromImage(String pfromImage) {
        this.pfromImage = pfromImage;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemarkCount() {
        return remarkCount;
    }

    public void setRemarkCount(String remarkCount) {
        this.remarkCount = remarkCount;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeVal() {
        return typeVal;
    }

    public void setTypeVal(String typeVal) {
        this.typeVal = typeVal;
    }



    @Override
    public String toString() {
        return "ProductItemBean{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", pfrom='" + pfrom + '\'' +
                ", pfromId='" + pfromId + '\'' +
                ", fromUrl='" + fromUrl + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", detailAppUrl='" + detailAppUrl + '\'' +
                ", image='" + image + '\'' +
                ", pfromImage='" + pfromImage + '\'' +
                ", type=" + type +
                ", typeVal='" + typeVal + '\'' +
                ", price=" + price +
                ", symbol='" + symbol + '\'' +
                ", description='" + description + '\'' +
                ", remarkCount=" + remarkCount +
                ", goodCount=" + goodCount +
                ", brandId=" + brandId +
                ", familyId=" + familyId +
                ", categoryId=" + categoryId +
                '}';
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.pname);
        dest.writeString(this.pfrom);
        dest.writeString(this.pfromId);
        dest.writeString(this.fromUrl);
        dest.writeString(this.detailUrl);
        dest.writeString(this.detailAppUrl);
        dest.writeString(this.image);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.pfromImage);
        dest.writeInt(this.type);
        dest.writeString(this.typeVal);
        dest.writeSerializable(this.price);
        dest.writeString(this.symbol);
        dest.writeString(this.description);
        dest.writeString(this.remarkCount);
        dest.writeInt(this.goodCount);
        dest.writeInt(this.brandId);
        dest.writeInt(this.familyId);
        dest.writeInt(this.categoryId);
        dest.writeInt(this.amountType);
        dest.writeDouble(this.amountMoney);
    }

    public ProductItemBean() {
    }

    protected ProductItemBean(Parcel in) {
        this.id = in.readInt();
        this.pname = in.readString();
        this.pfrom = in.readString();
        this.pfromId = in.readString();
        this.fromUrl = in.readString();
        this.detailUrl = in.readString();
        this.detailAppUrl = in.readString();
        this.image = in.readString();
        this.htmlUrl = in.readString();
        this.pfromImage = in.readString();
        this.type = in.readInt();
        this.typeVal = in.readString();
        this.price = (BigDecimal) in.readSerializable();
        this.symbol = in.readString();
        this.description = in.readString();
        this.remarkCount = in.readString();
        this.goodCount = in.readInt();
        this.brandId = in.readInt();
        this.familyId = in.readInt();
        this.categoryId = in.readInt();
        this.amountType = in.readInt();
        this.amountMoney = in.readDouble();
    }

    public static final Parcelable.Creator<ProductItemBean> CREATOR =
        new Parcelable.Creator<ProductItemBean>() {
            @Override public ProductItemBean createFromParcel(Parcel source) {
                return new ProductItemBean(source);
            }

            @Override public ProductItemBean[] newArray(int size) {
                return new ProductItemBean[size];
            }
        };
}
