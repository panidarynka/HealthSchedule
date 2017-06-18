package com.health.darynaosipenko.healthyschedule.create_schedule;

import android.util.Log;

import com.health.darynaosipenko.healthyschedule.R;
import com.health.darynaosipenko.healthyschedule.pojo.ActionType;
import com.health.darynaosipenko.healthyschedule.pojo.UserInfo;

/**
 * Created by darynaosipenko on 5/23/17.
 */

public class CreateSchedulePresenter {

    private ActionType.ActivityType currentActivity;

    public void setActiveAction(int viewId){
        switch (viewId){
            case R.id.sleep:
                currentActivity = ActionType.ActivityType.SLEEP;
                break;
            case R.id.eat:
                currentActivity = ActionType.ActivityType.EAT;
                break;
            case R.id.water:
                currentActivity = ActionType.ActivityType.WATER;
                break;
            case R.id.sport:
                currentActivity = ActionType.ActivityType.SPORT;
                break;
        }
    }

    public ActionType.ActivityType getCurrentActivity(){
        return currentActivity;
    }

    public void setNewAction(ActionType actionType){}

    public void removeNewAction(ActionType actionType){}

    public boolean checkSchedule(){
        return true;
    }

    public static int range(int num){
        if ( 12 <= num && num < 16)
            return 1;
        if ( 17 <= num && num <= 21)
            return 2;
        if (22 <= num && num < 55)
            return 3;
        return 4;
    }

    public static final int SCHOOLCHILD = 1;
    public static final int TEENAGER = 2;
    public static final int ADULT = 3;
    public static final int OLD = 4;

//    public static void main(String[]args){
//        int a = 110;
//        switch (range(a)){
//            case TEN_TWENTY:
//                System.out.println("10-20");
//                break;
//            case TWENTY_THIRTY:
//                System.out.println("20-30");
//                break;
//            default: break;
//        }
//    }


    public String getRecommendedSleep(UserInfo userInfo){
        switch (range(userInfo.getAge())){
            case SCHOOLCHILD:
                return "11-9";
            case TEENAGER:
                return "9-8";
            case ADULT:
                return "6-8";
            case OLD:
                return "5-7";
        }
        return "8";
    }

    public int getRecommendedEat(UserInfo userInfo){
        double kcal;
        double kcal1;
        double kcal2;
        double kcal3;
        if (userInfo.getGender() == UserInfo.Gender.FAMALE){
            if (getIMT(userInfo)<30){
                kcal1 = 665 +
                        9.6 * userInfo.getWeight() +
                        1.8 * userInfo.getHeight() -
                        4.7 * userInfo.getAge();
            } else {
                kcal1 = 2.4 * userInfo.getWeight() +
                        9 * userInfo.getHeight() -
                        4.7 * userInfo.getAge() -
                        45;
            }
//            kcal2 = 0.9 * userInfo.getWeight() * 24;
            kcal3 = 832 *
                    Math.pow(userInfo.getWeight(), 0.425) *
                    Math.pow(userInfo.getHeight(), 0.725) *
                    0.007184;
            kcal = (kcal1 + kcal3)/2;
        } else {
            if (getIMT(userInfo)<30){
                kcal1 = 66.5 +
                        13.7 * userInfo.getWeight() +
                        5 * userInfo.getHeight() -
                        6.8 * userInfo.getAge();
                Log.i("TEST", "getRecommendedEat: "+ userInfo.getWeight() + " "+ userInfo.getHeight() + " "+ userInfo.getAge());
                Log.i("TEST", "getRecommendedEat: <30"+ 13.7*userInfo.getWeight()+ " "+ 5*userInfo.getHeight() +" "+ 6.8* userInfo.getAge());
                Log.i("TEST", "getRecommendedEat: <30"+ kcal1);
            } else {
                kcal1 = 3.4 * userInfo.getWeight() +
                        15.3 * userInfo.getHeight() -
                        6.8 * userInfo.getAge() -
                        961;
                Log.i("TEST", "getRecommendedEat: >30"+ kcal1);
            }
//            kcal2 = userInfo.getWeight() * 24;
            kcal3 = 915 *
                    Math.pow(userInfo.getWeight(), 0.425) *
                    Math.pow(userInfo.getHeight(), 0.725) *
                    0.007184;
            Log.i("TEST", "getRecommendedEat:2 "+ kcal3);
            kcal = (kcal1 + kcal3)/2;

        }
        Log.i("TEST", "getRecommendedEat: activity "+UserInfo.PhysicalActivity.getNumberOfPhysicalActivity(userInfo.getPhysicalActivity()));
        return (int) ((int) kcal * UserInfo.PhysicalActivity.getNumberOfPhysicalActivity(userInfo.getPhysicalActivity()));
    }

    public double getRecommendedWater(UserInfo userInfo){
        return 0.001 * 35* userInfo.getWeight();
    }

    public int getRecommendedSport(UserInfo userInfo){
        return 0;
    }
    public double getIMT(UserInfo userInfo){
        return userInfo.getWeight()/Math.pow(userInfo.getWeight(),2);
    }
}
