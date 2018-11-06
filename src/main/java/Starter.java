import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Starter {
    public static final String FILE = "/home/user/Downloads/Telegram Desktop/еьуз/place-happy-contour.imageset";
    public static final String FILE_BLUE = "/home/user/Downloads/Telegram Desktop/еьуз/place-default-contour.imageset";
    public static final String FILE_LOCATION = "/home/user/Downloads/purlu_pngs/";
/*
    public static void main(String[] args) {
        try {
            Thumbnails.of(new File(FILE_LOCATION).listFiles())
                    //.size(100, 200)
                    .outputFormat("png")
                    .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
            System.out.println("was resized");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public static final String DEFAULT_TIMEZONE = "America/Chicago";
    public static final String FILE_NAME = "short.csv";
    public static final String URL_PREFIX_HTTP = "http://";
    public static final String URL_PREFIX_HTTPS = "https://";

    public static void main(String[] args) throws IOException {
        LatLng origin = new LatLng(36.3111, -94.1277);
        LatLng destination = new LatLng(36.3347, -94.1999);
        String urlTest = getUrl(origin, destination);

        log(getUrl(origin, destination));
        String etalon = " https://maps.googleapis.com/maps/api/directions/json?origin=36.3111,-94.1277&destination=36.3347,-94.1999&sensor=false&key=AIzaSyDMU7d4nLnxi5WGNRo5Z7peITHEOv0ZASU";
    }

    /*public String getUrl(LatLng origin, LatLng dest) {


        String strOrigin = String.format(Locale.ENGLISH, "origin=%f,%f", origin.latitude, origin.longitude);

        String strDest = String.format(Locale.ENGLISH, "destination=%f,%f", dest.latitude, dest.longitude);
        String googleRequestPattern = "https://maps.googleapis.com/maps/api/directions/%s?%s";

        String strParameters = String.format("%s&%s&%s", strOrigin, strDest, "sensor=false");

        String result = String.format(googleRequestPattern, "json", strParameters);

        LogUtils.logDebugObj(this, result);

        return result;
    }*/

    public static String getUrl(LatLng origin, LatLng dest) {
        String strOrigin = String.format(Locale.ENGLISH, "origin=%f,%f", origin.getLatitude(), origin.getLongitude());
        String strDest = String.format(Locale.ENGLISH, "destination=%f,%f", dest.getLatitude(), dest.getLongitude());
        String googleRequestPattern = "https://maps.googleapis.com/maps/api/directions/%s?%s";
        String apiKey = "AIzaSyDMU7d4nLnxi5WGNRo5Z7peITHEOv0ZASU";


        String strParameters = String.format("%s&%s&%s&key=%s", strOrigin, strDest, "sensor=false", apiKey);

        return String.format(googleRequestPattern, "json", strParameters);
    }

    /*   Observer<String> observer = new DisposableObserver<String>() {
               public void onNext(String s) {
                   System.out.println(s + ", callback from observer");
               }

               public void onError(Throwable throwable) {

               }

               public void onComplete() {

               }
           };

           Consumer<String> stringConsumer = s -> System.out.println(s + ", hello from consumer!");

           Observable<String> observable = Observable.just("Hello", "World");
           observable.subscribe(observer);


           Observer<String> observer2 = new Observer<String>() {
               @Override
               public void onSubscribe(Disposable disposable) {

               }

               @Override
               public void onNext(String s) {
                   System.out.println("Hello from observer2");
               }

               @Override
               public void onError(Throwable throwable) {

               }

               @Override
               public void onComplete() {

               }
           };

           observable.subscribe(observer2);
           observable.subscribe(stringConsumer);*/
    public static int getDayOfWeekMondayFirst(Calendar calendar) {
        int currentDOW = calendar.get(Calendar.DAY_OF_WEEK);
        if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY) {
            currentDOW -= 1;
            if (currentDOW < 1) {
                currentDOW = 7;
            }
        }

        return currentDOW;
    }

    public static Map<String, String> getCitiesMap(String filePath) throws IOException {
        Map<String, String> cities = new HashMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader((filePath)));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parsedLine = line.split(",");
                if (parsedLine.length != 0) {
                    cities.put(parsedLine[0], parsedLine[1]);
                }
            }
        } catch (IOException e) {

        } finally {
            reader.close();
        }
        return cities;
    }

    public static void log(Object message) {
        System.out.println(String.valueOf(message));
    }

    public static boolean isUrlValid(String url) {
        return url != null && (url.startsWith(URL_PREFIX_HTTP) || url.startsWith(URL_PREFIX_HTTPS));
    }
}
