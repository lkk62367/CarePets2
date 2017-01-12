package will.tw.carepets.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by william on 2016/12/21.
 */

public class Results {
    @SerializedName("Name")
    public String Name;
    @SerializedName("Sex")
    public String Sex;
    @SerializedName("Type")
    public String Type;
    @SerializedName("Build")
    public String Build;
    @SerializedName("Age")
    public String Age;
    @SerializedName("Variety")
    public String Variety;
    @SerializedName("Reason")
    public String Reason;
    @SerializedName("AcceptNum")
    public String AcceptNum;
    @SerializedName("ChipNum")
    public String ChipNum;
    @SerializedName("IsSterilization")
    public String IsSterilization;
    @SerializedName("HairType")
    public String HairType;
    @SerializedName("Note")
    public String Note;
    @SerializedName("Resettlement")
    public String Resettlement;
    @SerializedName("Phone")
    public String Phone;
    @SerializedName("Email")
    public String Email;
    @SerializedName("ChildreAnlong")
    public String ChildreAnlong;
    @SerializedName("AnimalAnlong")
    public String AnimalAnlong;
    @SerializedName("Bodyweight")
    public String Bodyweight;
    @SerializedName("ImageName")
    public String ImageName;

    public String tostring(){
        return Name;
    }

    public String getName() {
        return Name;
    }

    public String getSex() {
        return Sex;
    }

    public String getType() {
        return Type;
    }

    public String getBuild() {
        return Build;
    }

    public String getAge() {
        return Age;
    }

    public String getVariety() {
        return Variety;
    }

    public String getReason() {
        return Reason;
    }

    public String getAcceptNum() {
        return AcceptNum;
    }

    public String getChipNum() {
        return ChipNum;
    }

    public String getIsSterilization() {
        return IsSterilization;
    }

    public String getHairType() {
        return HairType;
    }

    public String getNote() {
        return Note;
    }

    public String getResettlement() {
        return Resettlement;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getChildreAnlong() {
        return ChildreAnlong;
    }

    public String getAnimalAnlong() {
        return AnimalAnlong;
    }

    public String getBodyweight() {
        return Bodyweight;
    }

    public String getImageName() {
        return ImageName;
    }
}
