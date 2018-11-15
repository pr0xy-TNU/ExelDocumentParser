import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    List<Integer> list = new ArrayList<>();
    IntStream.range(0, 10).forEach(item -> list.add(new Random().nextInt(100)));
    //list.forEach(System.out::println);

    getUserList(10)
        .map(instance -> {
          System.out.println(instance);
          return instance.getName();
        })
        .filter(item -> item.endsWith("5"))
        .forEach(System.out::println);

    System.out.println("from Rx");

    getUserListRx(10)
        .map(User::getName)
        .filter(pred -> pred.endsWith("5"))
        .subscribe(System.out::println).dispose();

  }

  private static Observable<User> getUserListRx(int size) {
    return Observable.range(0, size)
        .flatMap(item -> {
          User tempInst = new User();
          tempInst.setAge(new Random().nextInt(70));
          tempInst.setName(String.format("Name-%d", new Random().nextInt(70)));
          tempInst.setSuername(String.format("Surname-%d", new Random().nextInt(70)));
          return Observable.fromArray(tempInst);
        });
  }


  private static Stream<User> getUserList(int size) {
    List<User> list = new ArrayList<>();
    IntStream.range(0, size)
        .forEach(item -> {
          User tempInst = new User();
          tempInst.setAge(new Random().nextInt(70));
          tempInst.setName(String.format("Name-%d", new Random().nextInt(70)));
          tempInst.setSuername(String.format("Surname-%d", new Random().nextInt(70)));
          list.add(tempInst);
        });
    return list.stream();
  }

  public static class User {

    private String name;
    private String suername;
    private int age;

    public User() {
      //default
    }

    public User(String name, String suername, int age) {
      this.name = name;
      this.suername = suername;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public String getSuername() {
      return suername;
    }

    public int getAge() {
      return age;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setSuername(String suername) {
      this.suername = suername;
    }

    public void setAge(int age) {
      this.age = age;
    }

    @Override
    public String toString() {
      return "User[" +
          "name='" + name + '\'' +
          ", suername='" + suername + '\'' +
          ", age=" + age +
          ']';
    }
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
    String strOrigin = String
        .format(Locale.ENGLISH, "origin=%f,%f", origin.getLatitude(), origin.getLongitude());
    String strDest = String
        .format(Locale.ENGLISH, "destination=%f,%f", dest.getLatitude(), dest.getLongitude());
    String googleRequestPattern = "https://maps.googleapis.com/maps/api/directions/%s?%s";
    String apiKey = "AIzaSyCW40XcfD1HuB0RdL7iXSjzsTedmzSedoY";

    String strParameters = String
        .format("%s&%s&%s&key=%s", strOrigin, strDest, "sensor=false", apiKey);

    return String.format(googleRequestPattern, "json", strParameters);
  }

  public static String getUrlDemo(LatLng origin, LatLng dest) {
    String strOrigin = String
        .format(Locale.ENGLISH, "origin=%f,%f", origin.getLatitude(), origin.getLongitude());
    String strDest = String
        .format(Locale.ENGLISH, "destination=%f,%f", dest.getLatitude(), dest.getLongitude());

    String googleRequestPattern = "https://maps.googleapis.com/maps/api/directions/%s?%s";
    String strParameters = String.format("%s&%s&%s&key=%s&mode=%s", strOrigin, strDest,
        "sensor=false",
        "AIzaSyDMU7d4nLnxi5WGNRo5Z7peITHEOv0ZASU",
        "driving"
    );

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
