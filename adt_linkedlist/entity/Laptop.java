package entity;

public class Laptop {
    private String merk;
    private String tipe;
    private int harga;

    public Laptop(String merk, String tipe, int harga) {
        this.merk = merk;
        this.tipe = tipe;
        this.harga = harga;
    }

    public String getMerk() {
        return merk;
    }

    public String getTipe() {
        return tipe;
    }

    public int getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        //return "Laptop [Merk=" + merk + ", Type=" + tipe + ", Price=Rp" + String.format("%,.0f", harga) + "]";
        return "Laptop [ Merk=" + merk + ", Type=" + tipe + ", Price=Rp" + harga + " ]";
    }


}