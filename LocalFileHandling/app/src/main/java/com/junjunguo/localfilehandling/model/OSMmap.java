package com.junjunguo.localfilehandling.model;

import android.content.Context;

import com.junjunguo.localfilehandling.localdb.DAOlocation;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

/**
 * This file is part of LocalFileHandling
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 08/03/15.
 * <p/>
 * Responsible for this file: GuoJunjun
 */
public class OSMmap {
    /**
     * @param context
     * @return a markers Overlay Item Array
     */
    public ArrayList<OverlayItem> getCoworkerMarkersOverlay(Context context) {
        ArrayList<OverlayItem> markersOverlayItemArray = new ArrayList();
        DAOlocation daOlocation = new DAOlocation(context);
        List<LocationReport> locationReports = daOlocation.getCoWorkerLocations(UserInfo.getUSERID());

        for (LocationReport lr : locationReports) {
            markersOverlayItemArray.add(new OverlayItem(lr.getUserid(), lr.getUserid(),
                    new GeoPoint(lr.getLatitude(), lr.getLongitude())));
        }
        daOlocation.close();
        return markersOverlayItemArray;
    }

}
