package com.company;

import com.company.google.GoogleDriveServiceImp;
import com.company.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CaravanLiteApplicationTests {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LanguageService languageService;
    @Autowired
    private GuideService guideService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private MainService mainService;

    @Autowired
    private TripService tripService;
    @Autowired
    private GoogleDriveServiceImp googleDriveServiceImp;

    @Test
    void contextLoads() {
/*
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setDescription("Location manzil");
        locationDTO.setDistrict("Chorsu");
        locationDTO.setProvence("Tashkent");

        //  locationService.create(locationDTO);

        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setLevel(LanguageLevel.INTERMEDIATE);
        languageDTO.setName(LanguageName.ENGLISH);

        //   LanguageEntity entity = languageService.create(languageDTO);

        PriceDTO price = new PriceDTO();
        price.setCost(156600L);
        price.setCurrency(Currency.RUB);
        price.setType(TourType.DAY);

        List<LanguageDTO> language = new LinkedList<>();
        language.add(languageDTO);

        List<LocationDTO> locations = new LinkedList<>();
        locations.add(locationDTO);

        GuideDTO dto = new GuideDTO();
        dto.setBiography("ozim haqimda malumot");
        dto.setLanguages(language);
        dto.setTravelLocations(locations);
        dto.setSecondPhoneNumber("+998888877777");
        dto.setPrice(price);
        dto.setProfileId("ff80818180fb692f0180fb6d73470002");



     //   guideService.getTop10(0,2);
//        LoginResponse loginResponse = profileService.get("8a8a82cc810aef2701810aef3faf0000");
//        System.out.println(loginResponse);

//        String pattern = "dd.MM.yyyy";
//        String pattern = "yyyy-MM-dd";
//        LocalDate date = LocalDate.parse("31.12.2003", DateTimeFormatter.ofPattern(pattern));
//        System.out.println(date);

//        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
//        String strDate= formatter.format(LocalDate.now());
//        System.out.println(strDate);

        LocalDate today = LocalDate. now();
        String formattedDate = today.format(DateTimeFormatter. ofPattern("dd.MM.yyyy"));
        System. out. println(formattedDate);
//        dto.setBirthDate(date.toString());
    }
 */

    }

    @Test
    void tests() {
        //guideService.getTop10(0,10);

      //  mainService.getMainPage(0,4);

       /* ReviewDTO dto = new ReviewDTO();
        dto.setRate(5);

      //  dto.setGuideId("ff80818181241e380181242fc0900007");
        dto.setProfileId("ff808181811f3ba701811f3f15c70005");
        dto.setTripId("8a8a81a3812474fb018124755bc20001");
        dto.setType(ReviewType.TRIP);
        dto.setContent("vapshe zor guide ekan omad");
        System.out.println(reviewService.create(dto));*/

  /*      TripFirstDTO dto = new TripFirstDTO();
        dto.setMaxPeople(10);
        dto.setMinPeople(3);
        dto.setName("Samarqand");
        dto.setDesc("zor sayohat boladi");
        LocationDTO location = new LocationDTO();
        location.setDescription("zor");
        location.setDistrict("tashkent");
        location.setProvence("Toshkent");
        List<LocationDTO> list = new LinkedList<>();
        list.add(location);
        dto.setTravelLocations(list);

        ActionDTO actionDTO = tripService.create(dto, "ff808181811f0f1801811f0f628a0006");

        System.out.println(actionDTO);
*/
    }


}
