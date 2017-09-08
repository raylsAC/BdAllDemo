package com.example.zhangzhuokui.bdalldemo.bean;

import com.baidu.location.BDLocation;
import com.baidu.location.Poi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangzhuokui on 2017/8/27.
 */

public class BaseLocationBean implements Serializable{

    private String Time;    //获取定位时间 2017-08-27 11:11:11
    private String LocationID;    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
    private int LocType;    //获取定位类型
    private double Latitude;    //获取纬度信息 22.528815
    private double Longitude;    //获取经度信息 114.061466
    private float Radius;    //获取定位精准度 40
    private String AddrStr;    //获取地址信息
    private String Country;    //获取国家信息
    private String CountryCode;    //获取国家码
    private String City;    //获取城市信息
    private String CityCode;    //获取城市码
    private String District;    //获取区县信息 福田区
    private String Street;    //获取街道信息
    private String StreetNumber;    //获取街道码
    private String LocationDescribe;    //获取当前位置描述信息 在什么附近
    private List<Poi> PoiList;    //获取当前位置周边POI信息

    private String BuildingID;    //室内精准定位下，获取楼宇ID
    private String BuildingName;    //室内精准定位下，获取楼宇名称
    private String Floor;    //室内精准定位下，获取当前位置所处的楼层信息

    /*//当前为GPS定位结果，可获取以下信息
    private float getSpeed;    //获取当前速度，单位：公里每小时
    private int getSatelliteNumber;   //获取当前卫星数
    private double getAltitude;    //获取海拔高度信息，单位米
    private float getDirection;    //获取方向信息，单位度

    //当前为网络定位结果，可获取以下信息
    private int getOperators;    //获取运营商信息*/

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLocationID() {
        return LocationID;
    }

    public void setLocationID(String locationID) {
        LocationID = locationID;
    }

    public int getLocType() {
        return LocType;
    }

    public void setLocType(int locType) {
        LocType = locType;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public float getRadius() {
        return Radius;
    }

    public void setRadius(float radius) {
        Radius = radius;
    }

    public String getAddrStr() {
        return AddrStr;
    }

    public void setAddrStr(String addrStr) {
        AddrStr = addrStr;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getStreetNumber() {
        return StreetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        StreetNumber = streetNumber;
    }

    public String getLocationDescribe() {
        return LocationDescribe;
    }

    public void setLocationDescribe(String locationDescribe) {
        LocationDescribe = locationDescribe;
    }

    public List<Poi> getPoiList() {
        return PoiList;
    }

    public void setPoiList(List<Poi> poiList) {
        PoiList = poiList;
    }

    public String getBuildingID() {
        return BuildingID;
    }

    public void setBuildingID(String buildingID) {
        BuildingID = buildingID;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String buildingName) {
        BuildingName = buildingName;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }
}
