package com.springTest.dao;

import com.springTest.model.Satellite;

import java.util.List;

public interface SatelliteDAO {
    int addSatellite(Satellite satellite);
    List<Satellite> getAllSatellites();
    Satellite getSatelliteById(int id);
    List<Satellite> getSatellitesByName(String name);
    void removeSatellite(Satellite satellite);
    void updateSatellite(Satellite satellite);
    long getNumberOfSatellites();
}
