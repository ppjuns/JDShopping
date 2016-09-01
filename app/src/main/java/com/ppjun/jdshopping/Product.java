package com.ppjun.jdshopping;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Package :com.ppjun.jdshopping
 * @Description :
 * @Author :Rc3
 * @Created at :2016/8/25 16:09.
 */
public class Product implements Parcelable {

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    String productName;
    int productId;
    double productPrice;
    String productDesc;

    protected Product(Parcel in) {
        productId = in.readInt();
        productName = in.readString();
        productPrice = in.readDouble();
        productDesc = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productDesc);
        dest.writeDouble(productPrice);
        dest.writeInt(productId);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productId=" + productId +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                '}';
    }

    public Product(String productName, int productId, double productPrice, String productDesc) {
        this.productName = productName;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
    }
}
