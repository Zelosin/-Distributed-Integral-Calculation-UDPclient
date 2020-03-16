package zelosin.pack.Data;

import java.io.Serializable;

public class GFGCell implements Serializable {

    private float UpBorder, DownBorder, Accuracy;
    private Float Result;

    public GFGCell(float upBorder, float downBorder, float accuracy) {
        UpBorder = upBorder;
        DownBorder = downBorder;
        Accuracy = accuracy;
        Result = (float)0;
    }

    public float getUpBorder() {
        return UpBorder;
    }

    public void setUpBorder(float upBorder) {
        UpBorder = upBorder;
    }

    public float getDownBorder() {
        return DownBorder;
    }

    public void setDownBorder(float downBorder) {
        DownBorder = downBorder;
    }

    public float getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(float accuracy) {
        Accuracy = accuracy;
    }

    public Float getResult() {
        return Result;
    }

    public void setResult(Float result) {
        Result = result;
    }

    public void plusResult(Float pValue){
        this.Result += pValue;
    }

    @Override
    public String toString() {
        return "GFGCell{" +
                "UpBorder=" + UpBorder +
                ", DownBorder=" + DownBorder +
                ", Accuracy=" + Accuracy +
                ", Result=" + Result +
                '}';
    }
}
