package com.example.enerjizeit.serviceodometer;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class OdometerService extends Service {
    private static double distanceInMeters;
    private static Location lastLocation = null;

    private final IBinder binder = new OdometerBinder();
    /* Когда активность связывается со службой через соединение, объект соединения вызывает метод onBind(), который
возвращает объект OdometerBinder. Когда активность получает OdometerBinder от соединения, она использует метод
getOdometer() для получения объекта OdometerService. */

    public class OdometerBinder extends Binder {
        OdometerService getOdometer() {
            return OdometerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
/* Чтобы получить информацию о местонахождении своего устройства, воспользуйтесь службой позиционирования Android.
Служба использует информацию, полученную от системы GPS, а также имена и уровни сигналов ближайших сетей WiFi
для определения местонахождения устройства на поверхности земли. Начните с создания объекта LocationListener.
Этот объект используется для получения оповещений об изменении позиции устройства. Объект LocationListener
создается следующим образом: */
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
/* Этот метод вызывается каждый раз, когда LocationListener получает информацию об изменении местонахождения
устройства. Параметр Location описывает текущую позицию. */
                if (lastLocation == null) {
                    lastLocation = location;
                }
                distanceInMeters += location.distanceTo(lastLocation);
                lastLocation = location;
            }


            /* Эти методы тоже должны переопределяться, но их можно оставить пустыми. Они вызываются при включении/отключении модуля GPS
            или изменении его статуса. Нашему приложению не нужно реагировать на эти события. */
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                                                          != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                                                 != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Your device did not provide location!", Toast.LENGTH_SHORT).show();
        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
    }

    public double getMeters(){
        return this.distanceInMeters;
    }
}
