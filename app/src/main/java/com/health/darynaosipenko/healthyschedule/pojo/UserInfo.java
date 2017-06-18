package com.health.darynaosipenko.healthyschedule.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darynaosipenko on 5/20/17.
 */

public class UserInfo implements Serializable {
    private int age;
    private Gender gender;

    private PhysicalActivity physicalActivity;
    private int weight;
    private int height;

    public enum Gender {MALE, FAMALE}

    public enum PhysicalActivity {
        //        Too small", "Small", "Middle", "Big", "Too big"
        TOOSMALL, SMALL, MIDDLE, BIG, TOOBIG;

        public static List<String> getValuesName() {
            List<String> names = new ArrayList<String>();
            String name;
            for (PhysicalActivity physicalActivity : PhysicalActivity.values()) {
                if (physicalActivity == TOOBIG) {
                    name = "TOO BIG";
                } else if (physicalActivity == TOOSMALL) {
                    name = "TOO SMALL";
                } else {
                    name = physicalActivity.name();
                }
                names.add(name);
            }
            return names;
        }

        public static PhysicalActivity getEnumByString(String name) {
            for (PhysicalActivity e : PhysicalActivity.values()) {
                if (name.equals(e.name())) return e;
            }
            return null;
        }
        public  static double getNumberOfPhysicalActivity(PhysicalActivity physicalActivity){
            switch (physicalActivity){
                case TOOSMALL:
                    return 1.2;
                case SMALL:
                    return 1.375;
                case MIDDLE:
                    return 1.55;
                case BIG:
                    return 1.725;
                case TOOBIG:
                    return 1.9;
            }
            return 1.2;
        }
    }

    public UserInfo(int age, Gender gender, PhysicalActivity physicalActivity, int weight, int height) {
        this.age = age;
        this.gender = gender;
        this.physicalActivity = physicalActivity;
        this.weight = weight;
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public PhysicalActivity getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(PhysicalActivity physicalActivity) {
        this.physicalActivity = physicalActivity;
    }

}
