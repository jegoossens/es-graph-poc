package be.jegoossens.es.graph.poc;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserLoader {


    private static final int NUMBER_OF_USERS = 1000;
    private List<String> hobbies;
    private List<String> genders = Arrays.asList("Male", "Female");
    private final UserRepository userRepository;

    @Autowired
    public UserLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initUser() throws URISyntaxException, IOException {
        hobbies = Files.lines(Paths.get(getClass().getResource("/hobbies.txt").toURI())).collect(Collectors.toList());
        List<User> toInsert = IntStream.range(0, NUMBER_OF_USERS).mapToObj(this::createUser).collect(Collectors.toList());
        userRepository.save(toInsert);
    }

    private User createUser(int index) {
        User user = new User();
        user.setId(String.valueOf(index));
        user.setAge(getRandomNumber(18, 61));
        user.setGender(genders.get(getRandomNumber(0, 10) % 2));
        int numberOfHobbies = getRandomNumber(1, hobbies.size());
        user.setHobbies(IntStream.range(0, numberOfHobbies).mapToObj(hobby -> getHobby()).collect(Collectors.toList()));
        user.setDate(DateTime.now().toString());
        return user;
    }

    private int getRandomNumber(int lowerInclusive, int upperExclusive){
        return ThreadLocalRandom.current().nextInt(lowerInclusive, upperExclusive);
    }

    private String getHobby(){
        return hobbies.get(getRandomNumber(0, hobbies.size()));
    }
}
