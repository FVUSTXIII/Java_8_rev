import java.io.CharArrayWriter;
import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainClass implements Comparable {


    public static String rotateString(String normal_string, Integer roulette_value, String Orientation) {
        final Integer r_v = roulette_value;
        String resulting_string = "";
        if (Orientation.equals("Clockwise")) {
            resulting_string = Arrays.stream(normal_string.split("")).reduce("", (acumulator, string_) -> {
                string_ = normal_string.substring(r_v) + normal_string.substring(0, r_v);
                return string_;
            });
        } else if (Orientation.equals("Anticlockwise")) {
            resulting_string = Arrays.stream(normal_string.split("")).reduce("", (acumulator, string_) -> {
                string_ = normal_string.substring(normal_string.length()-r_v) + normal_string.substring(0, normal_string.length()-r_v);
                return string_;
            });
        }
        return resulting_string;
    }

    public static void first_exc () {
        String[] values = {
                "Mercury",
                "Venus",
                "Mars"
        };


        String resulting_string = Arrays.stream(values)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        System.out.println(resulting_string);

    }
    public static Boolean isVowel(Character x) {
        return (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u' ||
                x == 'A' || x == 'E' || x == 'I' || x == 'O' || x == 'U');
    }
    public static String identifyVowels (final String _s) {
        AtomicInteger j = new AtomicInteger(0);
        return IntStream.range(0, _s.toCharArray().length)
                .mapToObj(i -> _s.toCharArray()[i])
                .filter(i -> (i % 3 == 0) && (i > 0))
                .filter(c_ -> isVowel(c_))
                .collect(Collectors.toList()).stream().map(Object::toString).collect(Collectors.joining());
    }

    public static String reverseString (final String _s) {
        return IntStream
                .range(0, _s.length())
                .map(i -> _s.charAt(_s.length() - i - 1))
                .collect(StringBuilder::new, (s, c) -> s.append((char) c), StringBuilder::append).toString();
    }

    public static Boolean palindrome (final String palindrome) {
        String reversed = reverseString(palindrome);
        AtomicInteger i = new AtomicInteger(palindrome.length()-1);
        AtomicInteger j = new AtomicInteger(0);
        AtomicInteger k = new AtomicInteger(0);
        return Stream.of(reversed).allMatch(x -> {
            while (i.get() >= 0) {
                System.out.println(i.get());
                if (x.charAt(i.get()) == palindrome.charAt(palindrome.length() - k.get() - 1)) {
                    j.incrementAndGet();
                }
                i.decrementAndGet();
                k.incrementAndGet();
            }
            System.out.println(j.get());
            return ( j.get() == palindrome.length());
        });
    }

    public static void second_exc() throws IOException {
        List<LocalDateTime> a = RetrieveSiteLoginActivity.trackActivityFrequency();
         int d = 0;
        a.forEach( log -> {
            System.out.println("Tenemos: " + log);
        });
        String[] arreglo_elementos = {"America", "atlas", "Cruz Azul" ,"Mty"};
        Arrays.stream(arreglo_elementos).sequential().forEach((equipo_futbol) -> System.out.println(equipo_futbol));

    }

    public static void main(String[] args) {
        try {
            second_exc();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //first_exc();
        System.out.println(rotateString("Pablo", 2, "Clockwise"));
        System.out.println(rotateString("Pablo", 2, "Anticlockwise"));
        System.out.println(palindrome("anitalavalatina"));
        System.out.println(identifyVowels("Hooasjdhfuaiudnfiaundiunasidnauidfiuhadf"));
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
